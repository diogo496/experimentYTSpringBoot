package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

// first type is student, and the second type is the data type for the student id
// mongo repository has a lot of methods .. findall, insert, etc...
public interface StudentRepository extends MongoRepository<Student, String> {

}
