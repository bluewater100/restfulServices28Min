package com.in28minutes.rest.webservices.restfulwebservices.person
;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class PersonController {
	
	private static final Logger logger = Logger.getLogger(PersonController.class);

    @Autowired
    private PersonDao personDao;

    @GetMapping("/persons")
    public List<Person> retrieveAllPersons() {
    	    	
        return personDao.findAll();
    }
    

    @GetMapping("/persons/{id}")
    public Person retrievePerson(@PathVariable int id) {
    	
        return personDao.findById(id);              
    }
    
    
    @GetMapping("/persons/{name}/{location}")
    public Person retrievePersonByNameAndLocation(@PathVariable String name, @PathVariable String location) {
    	
        return personDao.findByNameAndLocation(name, location);              
    }

    
    @DeleteMapping("/persons/{id}")
    public int deletePerson(@PathVariable int id) {
    	
        return personDao.deleteById(id);
    }
    

    // the location gets returned in the response header
    // This is a loose example because we are passing in the ID and it would probably be auto generated
    @PutMapping("/persons")
    public ResponseEntity<Object> createPerson(@Valid @RequestBody Person person) {
        int numRowsInserted = personDao.insert(person);
        	logger.info("Number of rows inserted " + numRowsInserted);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    
    @PostMapping("/persons")
    public ResponseEntity<Object> updatePerson(@Valid @RequestBody Person person) {
        int numRowsInserted = personDao.update(person);
        	logger.info("Number of rows updated " + numRowsInserted);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
