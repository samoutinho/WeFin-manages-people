package com.wefin.managespeople.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wefin.managespeople.model.People;
import com.wefin.managespeople.repository.PeopleRepository;
import com.wefin.managespeople.service.impl.PeopleServiceImpl;

@SpringBootTest
@WebAppConfiguration
public class PeopleServiceImplTest {
    @Autowired
    private PeopleServiceImpl serviceImpl;

    @Autowired
    private PeopleRepository peopleRepository;

    private static final Long ID = 1L;
    private static final String NAME = "Antonio";
    private static final String IDENTIFIER = "02476586955";
    private static final String IDENTIFIER_TYPE = "CPF";

    @Test
    public void getByIdTest(){
        People person = serviceImpl.getById(ID);

        assertEquals(NAME, person.getName());
        assertEquals(IDENTIFIER, person.getIdentifier());
        assertEquals(IDENTIFIER_TYPE, person.getIdentifierType());
    }

    @Test
    public void delete(){
        serviceImpl.delete(ID);
        Optional<People> result = peopleRepository.findById(ID);
        assertFalse(result.isPresent());
    }
}
