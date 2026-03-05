Repository Name

skill9-global-exception-handling

Project Structure
skill9-global-exception-handling
 ├─ src/main/java/com/demo
 │   ├─ controller
 │   │   └─ StudentController.java
 │   ├─ exception
 │   │   ├─ StudentNotFoundException.java
 │   │   └─ GlobalExceptionHandler.java
 │   └─ SpringbootApplication.java
 └─ README.md
1️⃣ Custom Exception
package com.demo.exception;

public class StudentNotFoundException extends RuntimeException{

    public StudentNotFoundException(String message){
        super(message);
    }

}
2️⃣ Global Exception Handler
package com.demo.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFoundException ex){

        return new ResponseEntity<>(
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){

        return new ResponseEntity<>(
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
3️⃣ Controller
package com.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.demo.exception.StudentNotFoundException;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping("/{id}")
    public String getStudent(@PathVariable int id){

        if(id != 1){
            throw new StudentNotFoundException("Student not found with id " + id);
        }

        return "Student Found";
    }
}
4️⃣ Main Application
package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
