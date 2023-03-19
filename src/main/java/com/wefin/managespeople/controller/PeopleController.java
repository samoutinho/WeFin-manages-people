package com.wefin.managespeople.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wefin.managespeople.dtos.PeopleDTO;
import com.wefin.managespeople.model.People;
import com.wefin.managespeople.service.PeopleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/people")
@AllArgsConstructor
public class PeopleController {

	@Autowired
	private ModelMapper modelMapper;

	private PeopleService peopleService;

	@GetMapping("/v1")
	public List<PeopleDTO> getAll() {
		return peopleService.list().stream().map(person -> modelMapper.map(person, PeopleDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/v1/{id}")
	public ResponseEntity<PeopleDTO> getById(@PathVariable(name = "id") Long id) {
		People person = peopleService.getById(id);

		return ResponseEntity.ok().body(modelMapper.map(person, PeopleDTO.class));
	}

	@PostMapping("/v1")
	public ResponseEntity<PeopleDTO> createPost(@RequestBody PeopleDTO personDto) {

		People peopleRequest = modelMapper.map(personDto, People.class);

		People people = peopleService.create(peopleRequest);

		return new ResponseEntity<>(modelMapper.map(people, PeopleDTO.class), HttpStatus.CREATED);
	}

	@PutMapping("/v1/{id}")
	public ResponseEntity<PeopleDTO> update(@PathVariable long id, @RequestBody PeopleDTO personDto) {
		People peopleRequest = modelMapper.map(personDto, People.class);
		People people = peopleService.update(id, peopleRequest);
		return ResponseEntity.ok().body(modelMapper.map(people, PeopleDTO.class));
	}

	@DeleteMapping("/v1/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Long id) {
		peopleService.delete(id);
		return ResponseEntity.ok().build();
	}

}
