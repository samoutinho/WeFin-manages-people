package com.wefin.managespeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wefin.managespeople.model.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
