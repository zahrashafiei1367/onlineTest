package maktab.controller;

import maktab.model.entity.Student;
import maktab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentRestController {

    public StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public Student getStudent1(@RequestParam("studentNumber") String studentNumber){
//        return studentService.findByStudentNumber(studentNumber);
//    }

    //@RequestMapping(value = "", method = RequestMethod.GET)
//    @GetMapping(value = "/{studentNumber}")
//    public Student getStudent2(@PathVariable("studentNumber") String studentNumber){
//        return studentService.findByStudentNumber(studentNumber);
//    }

//    @PostMapping(value = "/", consumes = "application/json", produces = "text/html")
//    public ResponseEntity saveNewStudent(@RequestBody Student student){
//        try {
//            studentService.registerNewStudent(student);
//            return ResponseEntity.ok()
//                    .body("welcome " + student.getName());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest()
//                    .body("error " + e.getMessage());
//        }
//    }

}
