
package com.example.studentapp.controller;

import com.example.studentapp.entity.Student;
import com.example.studentapp.exception.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
	@GetMapping("/")
	public String test() {
        return "Student API works!";
    }
	public String home() {
	    return "Student API Running Successfully!";
	}

 @GetMapping("/{id}")
 public Student getStudent(@PathVariable String id){

  int studentId;

  try{
   studentId=Integer.parseInt(id);
  }catch(NumberFormatException e){
   throw new InvalidInputException("Student ID must be a number.");
  }

  if(studentId!=1){
   throw new StudentNotFoundException("Student with ID "+studentId+" not found.");
  }

  return new Student(1,"Rahul","Computer Science");
 }
}
