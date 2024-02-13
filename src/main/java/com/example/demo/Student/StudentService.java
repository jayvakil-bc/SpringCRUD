package com.example.demo.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) throws IllegalAccessException {
        Optional<Student> studentByEmail = studentRepository.
                findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalAccessException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentID){
        boolean exists = studentRepository.existsById(studentID);

        if(!exists){
            throw new IllegalStateException("Student With Id " + studentID);
        }

        studentRepository.deleteById(studentID);
    }

    @Transactional
    public void updateStudent(Long studentID, String name, String email){
        Student student = studentRepository.findById(studentID)
                .orElseThrow(()->new IllegalStateException(
                        "Student With ID " + studentID + " does not exist"
                ));

        if(name != null &&
            name.length() > 0 &&
                !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email != null &&
            email.length() > 0 &&
                !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional = studentRepository
                    .findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email Taken");
            }
            student.setEmail(email);
        }

    }
}
