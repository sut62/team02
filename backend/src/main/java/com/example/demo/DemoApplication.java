package com.example.demo;

import com.example.demo.repository.LibrarianRepository;
import com.example.demo.repository.TypeRepository;
import com.example.demo.repository.YearRepository;
import com.example.demo.entity.Type;
import com.example.demo.entity.Year;
import com.example.demo.entity.Librarian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init(TypeRepository typeRepository, YearRepository yearRepository, LibrarianRepository librarianRepository) {
		return args -> {
			Stream.of("AAA").forEach(type -> {
				Type types = new Type();
				types.setType(type);
				typeRepository.save(types);
			});

			Stream.of("2000").forEach(year -> {
				Year years = new Year();
				years.setYear(year);
				yearRepository.save(years);
			});

			
		};
	}
}
