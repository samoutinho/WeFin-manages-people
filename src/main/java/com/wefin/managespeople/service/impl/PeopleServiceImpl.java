package com.wefin.managespeople.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.wefin.managespeople.model.People;
import com.wefin.managespeople.repository.PeopleRepository;
import com.wefin.managespeople.service.PeopleService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    private static final int CPF_LEN = 11;
    private static final int CNPJ_LEN = 14;

    private static final String CPF_STR = "CPF";
    private static final String CNPJ_STR = "CNPJ";
    private static final String ERROR_NOT_FOUND = "People with id [%d] was not found!";

    @Override
    public List<People> list() {
        return peopleRepository.findAll();
    }

    @Override
    public People create(People peopleRequest) {
        peopleRequest.setIdentifierType(this.getIdentifierType(peopleRequest.getIdentifier()));

        return peopleRepository.save(peopleRequest);
    }

    @Override
    public People update(Long id, People peopleRequest) {
        People people = peopleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format(ERROR_NOT_FOUND, id)));

        people.setIdentifierType(this.getIdentifierType(peopleRequest.getIdentifier()));
        people.setName(peopleRequest.getName());
        people.setIdentifier(peopleRequest.getIdentifier());

        return peopleRepository.save(people);
    }

    private String getIdentifierType(String identifier) {
        if (identifier.length() == CPF_LEN) {
            return CPF_STR;
        } else if (identifier.length() == CNPJ_LEN) {
            return CNPJ_STR;
        }
        throw new IllegalArgumentException("The person Identifier must be 11 or 14 characters long!");
    }

    @Override
    public void delete(Long id) {
        People post = peopleRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(ERROR_NOT_FOUND, id)));

        peopleRepository.delete(post);
    }

    @Override
    public People getById(Long id) {
        Optional<People> result = peopleRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(String.format(ERROR_NOT_FOUND, id));
        }
    }

}
