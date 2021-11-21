package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    CommandLineRunner runner(
            StudentRepository repository,
            MongoTemplate mongoTemplate){
        return args -> {
            Address address = new Address(
                "Portugal",
            "Seixal",
            "2855-723"
            );

            String email = "diogoreis494@gmail.com";

            Student student = new Student(
                    "Diogo",
                    "Reis",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Computer Science"),
                    BigDecimal.TEN,
                    LocalDateTime.now()

            );

            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            List<Student> students = mongoTemplate.find(query, Student.class);

            if( students.size() > 0){
                throw new IllegalStateException("found students with same email" + email);
            }

            if( students.isEmpty()){
                System.out.println("Inserting student "+student);
                repository.insert(student);
            }

        };
    }

}
