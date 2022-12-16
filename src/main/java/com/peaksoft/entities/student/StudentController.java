package com.peaksoft.entities.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/{id}")
    public String index(Model model, @PathVariable("id") Long id) {
        model.addAttribute("people", studentService.getAllStudents(id));
        return "student/students";
    }

    @GetMapping("student/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", studentService.getStudentById(id));
        return "student/show";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student") Student student) {
//        model.addAttribute("person", new Person());
        return "student/newStudent";
    }

    @PostMapping
    public String create(@ModelAttribute("student") /*@Valid*/ Student student, Long id/* BindingResult bindingResult*/) {
//        if (bindingResult.hasErrors()){
//            return "/people/new";}
        studentService.saveStudent(student, id);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "student/editStudent";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("student") /*@Valid*/ Student student
            /*BindingResult bindingResult*/, @PathVariable("id") Long id) {
//        if(bindingResult.hasErrors()){
//            return "/student/editStudent";}
        studentService.updateStudent(student, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
