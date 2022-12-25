package com.peaksoft.entities.course;

import com.peaksoft.entities.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CompanyService companyService;
    private final CourseService courseService;
      @Autowired
    public CourseController(CompanyService companyService, CourseService courseService) {
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
    public String create(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()){
            return "/course/newCourse";}
        courseService.saveCourse(id, course);
        return "redirect:/courses";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "course/editCourse";
    }

    @PatchMapping("/{id}")
    public String updateCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, @PathVariable("id") Long id) {
        if(bindingResult.hasErrors()){
            return "/course/editCourse";}
        courseService.updateCourse(id, course);
        return "redirect:/courses/course/"+id;

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }
}
