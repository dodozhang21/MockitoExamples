package net.pureessence.dao;

import net.pureessence.domain.Person;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao extends JdbcDaoSupport {
    public void save(Person person) {

    }
    public Person get(Long id) {
        return null;
    }
    public void delete(Long id) {

    }
    public List<Person> getPersons() {
        return null;
    }
}
