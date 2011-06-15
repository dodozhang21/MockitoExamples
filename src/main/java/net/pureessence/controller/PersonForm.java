package net.pureessence.controller;

import net.pureessence.domain.Person;

import java.util.List;

public class PersonForm {
    private Person person;
    private List<Person> searchResults;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Person> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Person> searchResults) {
        this.searchResults = searchResults;
    }
}
