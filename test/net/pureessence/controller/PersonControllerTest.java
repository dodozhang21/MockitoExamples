package net.pureessence.controller;

import static junit.framework.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import net.pureessence.dao.PersonDao;
import net.pureessence.domain.Person;

import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {
    @Mock
    private PersonDao personDao;

    @Mock
    private Log log;

    @Mock
    private PersonForm personForm;

    @InjectMocks
    private PersonController controller = new PersonController();

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void init() {
    	// arrange
        Person person = new Person();
        person.setFirstName("Misty");
        person.setLastName("Smith");
        when(personDao.getPersons()).thenReturn(Arrays.asList(person));
        ArgumentCaptor<List> personsCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        // act
        String view = controller.init();

        // assert
        verify(personForm).setSearchResults(personsCaptor.capture());
        verify(log).info(logCaptor.capture());

        assertEquals(1, personsCaptor.getValue().size());
        Person personCaptured = (Person) personsCaptor.getValue().get(0);
        assertEquals("Misty", personCaptured.getFirstName());
        assertEquals("Smith", personCaptured.getLastName());
        assertEquals("found 1 persons", logCaptor.getValue());
        assertEquals("persons", view);
    }

    @Test
    public void saveError() {
    	// arrange
        BindingResult result = mock(BindingResult.class);
        Person person = mock(Person.class);
        when(result.hasErrors()).thenReturn(true);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        // act
        String view = controller.add(person, result);

        // assert
        verify(log).info(logCaptor.capture());
//        verify(log, times(2)).info(logCaptor.capture());
        verifyZeroInteractions(personDao);

        assertEquals("validation error occurred", logCaptor.getValue());
//        assertEquals(2, logCaptor.getAllValues().size());
//        String value1 = logCaptor.getAllValues().get(0);
//        String value2 = logCaptor.getAllValues().get(1);
//        assertEquals("validation error occurred", value1);
//        assertEquals("validation error occurred 2", value2);
        assertEquals("persons/add", view);
    }

    @Test
    public void delete() {
    	// arrange
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Person> personCaptor = ArgumentCaptor.forClass(Person.class);
        Long personId = 124L;
        Person personToDelete = new Person(); // use a test stub instead of a mock
		when(personDao.get(personId)).thenReturn(personToDelete);

        // act
        String view = controller.delete(personId);

        // assert
        verify(personDao).get(personId);
        verify(personDao).save(personCaptor.capture());
        verify(personDao, never()).delete(any(Person.class));
        verify(log, times(2)).info(logCaptor.capture());

        assertEquals("verify the private deletePerson method has updated the deleted flag on person", 
        		true, 
        		personCaptor.getValue().isDeleted());
        assertEquals("redirecting to persons", logCaptor.getAllValues().get(1));
        assertEquals("redirect:/persons", view);
    }
}
