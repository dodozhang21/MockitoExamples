package net.pureessence.dao;

import java.util.List;

import net.pureessence.domain.Person;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDao extends JdbcDaoSupport {
    public void save(Person person) {
    	// implementation is not required for Mockito to work
    }

    public Person get(Long id) {
    	// implementation is not required for Mockito to work
        return null;
    }

    public void delete(Person person) {
    	// implementation is not required for Mockito to work
    }

    public List<Person> getPersons() {
    	// implementation is not required for Mockito to work
        return null;
    }
}
