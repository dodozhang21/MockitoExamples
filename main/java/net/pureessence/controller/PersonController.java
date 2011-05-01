package net.pureessence.controller;

import net.pureessence.dao.PersonDao;
import net.pureessence.domain.Person;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private Log log;

    @Autowired
    private PersonForm personForm;

    @RequestMapping(method = RequestMethod.GET)
    public String init() {
        List<Person> searchResults = personDao.getPersons();
        personForm.setSearchResults(searchResults);
        log.info(String.format("found %s persons", searchResults.size()));
        return "persons";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Person person, BindingResult result) {
        if (result.hasErrors()) {
            log.info("validation error occurred");
            return "persons/add";
        }
        personDao.save(person);
        log.info("person saved");
        log.info("redirecting to persons");
        return "redirect:/persons";
    }

    @RequestMapping(value = "delete/{personId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long personId) {
        personDao.delete(personId);
        log.info(String.format("deleted person with id '%s'", personId));
        log.info("redirecting to persons");
        return "redirect:/persons";
    }
}
