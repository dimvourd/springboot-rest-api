package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {
    // http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        return new Student(1, "Dimitris", "Vourdoukas");
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Dim","Vourd"));
        students.add(new Student(2,"Vas","Thomou"));
        students.add(new Student(3,"Zois","Thomou"));
        students.add(new Student(4,"Agge","Thomou"));
        return ResponseEntity.ok(students);
    }
    // Spring Boot REST API with Path Variable
    // {id} - URI template variable

    // http://localhost:8080/students/1
    @GetMapping("{id}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId){
        Student student = new Student(studentId, "Dim", "Vourd");
        //        return new ResponseEntity<>(new Student(studentId, "Dim", "Vourd"),HttpStatus.OK);
//        return ResponseEntity.ok(new Student(studentId, "Dim", "Vourd"));
        return ResponseEntity.ok()
                .header("headerName","headerValue").body(student);
    }

    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentCompletePathVariable(@PathVariable("id") int studentId,
                                               @PathVariable("first-name") String lastName,
                                               @PathVariable("last-name") String firstName)
    {
        return ResponseEntity.ok(new Student(studentId, firstName, lastName));
    }

    // Spring Boot REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=Dimitris&lastName=Vourdoukas

    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam("id")  int id,
                                          @RequestParam("firstName") String firstName,
                                          @RequestParam("lastName") String lastName){
        return ResponseEntity.ok(new Student(id , firstName, lastName));
    }
    // Spring Boot REST API that handles HTTP POST Request - creates new resource
    // @PostMapping and @RequestBody

    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("{id}/update")
    //@ResponseStatus()

    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // Spring Boot REST API that handles HTTP DELETE Request - deleting existing resource
    //
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student Deleted!");
    }

}
