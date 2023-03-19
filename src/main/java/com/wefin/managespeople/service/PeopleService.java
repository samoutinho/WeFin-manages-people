package com.wefin.managespeople.service;

import java.util.List;

import com.wefin.managespeople.model.People;

public interface PeopleService {

    List<People> list();

    People create(People people);

    People update(Long id, People people);

    void delete(Long id);

    People getById(Long id);
}
