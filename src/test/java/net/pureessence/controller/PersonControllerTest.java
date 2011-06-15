package net.pureessence.controller;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

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

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
    public void init() {
        Person person = new Person();
        person.setFirstName("Misty");
        person.setLastName("Smith");
        when(personDao.getPersons()).thenReturn(Arrays.asList(person));
        ArgumentCaptor<List> personsCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        String view = controller.init();

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
        BindingResult result = mock(BindingResult.class);
        Person person = mock(Person.class);
        when(result.hasErrors()).thenReturn(true);
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        String view = controller.add(person, result);

        verify(log).info(logCaptor.capture());
        verifyZeroInteractions(personDao);

        assertEquals("validation error occurred", logCaptor.getValue());
        assertEquals("persons/add", view);
    }

    @Test
    public void delete() {
        ArgumentCaptor<String> logCaptor = ArgumentCaptor.forClass(String.class);

        String view = controller.delete(1L);

        verify(personDao).get(1L);
        verify(personDao).delete((Person) any());
        verify(log, times(2)).info(logCaptor.capture());

        assertEquals("deleted person with id '1'", logCaptor.getAllValues().get(0));
        assertEquals("redirecting to persons", logCaptor.getAllValues().get(1));
        assertEquals("redirect:/persons", view);
    }
}
