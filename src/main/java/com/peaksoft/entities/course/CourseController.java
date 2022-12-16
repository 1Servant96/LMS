package com.peaksoft.entities.course;

import com.peaksoft.entities.company.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CompanyServiceImpl companyService;
    private final CourseServiceImpl courseService;
      @Autowired
    public CourseController(CompanyServiceImpl companyService, CourseServiceImpl courseService) {
          this.companyService = companyService;
          this.courseService = courseService;
    }
    @GetMapping("/{id}")
    public String getCourses(Model model, @PathVariable ("id") Long id) {
        model.addAttribute("courses", courseService.getAllCourses(id));
        return "course/courses";
    }

    @GetMapping("/course/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "course/show";
    }

    @GetMapping("/new")
    public String newCompany(@ModelAttribute("course") Course course) {
//        model.addAttribute("person", new Person());
        return "course/newCourse";
    }

    @PostMapping("/{id}")
    public String create(@ModelAttribute("course") /*@Valid*/ Course course, @PathVariable("id") Long id /*BindingResult bindingResult*/) {
//        if (bindingResult.hasErrors()){
//            return "/allCompanies/new";}
        courseService.saveCourse(id, course);
        return "redirect:/courses";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "course/editCourse";
    }

    @PatchMapping("/{id}")
    public String updateCourse(@ModelAttribute("course") /*@Valid*/ Course course,/* BindingResult bindingResult,*/ @PathVariable("id") Long id) {
//        if(bindingResult.hasErrors()){
//            return "/allCompanies/edit";}
        courseService.updateCourse(id, course);
        return "redirect:/courses/course/"+id;

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }
}
