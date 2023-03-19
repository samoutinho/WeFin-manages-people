package com.wefin.managespeople;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.wefin.managespeople.model.People;
import com.wefin.managespeople.repository.PeopleRepository;

@SpringBootApplication
public class ManagesPeopleApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ManagesPeopleApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(PeopleRepository peopleRepository){
		return args -> {
			peopleRepository.deleteAll();

			People p = new People();

			p.setName("Antonio");
			p.setIdentifier("02476586955");
			p.setIdentifierType("CPF");
			
			peopleRepository.save(p);

		};
	}

}
