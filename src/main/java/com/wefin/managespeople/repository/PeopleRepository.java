package com.wefin.managespeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wefin.managespeople.model.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

}
