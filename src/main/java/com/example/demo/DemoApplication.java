package com.example.demo;

//import jdk.incubator.foreign.Addressable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String [] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

//
//	@Bean
//	CommandLineRunner runner(StudentRepository repository){
//		return args->{
//			Address address = new Address(
//					"Endland",
//					"london",
//					"1219");
//			Student student = new Student(
//					"zunaed",
//					"farabe",
//					"far.be@gmail.com",
//					Gender.MALE,
//					address,
//					List.of("CSE","Math"),
//					LocalDateTime.now()
//
//			);
//
//			repository.insert(student);
//
//		};
//	}


}
