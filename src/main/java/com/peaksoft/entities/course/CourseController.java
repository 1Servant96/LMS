package com.peaksoft.entities.course;

import com.peaksoft.entities.group.Group;
import com.peaksoft.entities.group.GroupService;
import com.peaksoft.entities.instructor.Instructor;
import com.peaksoft.entities.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class CourseController {
    private final GroupService groupService;
    private final CourseService courseService;
    private final InstructorService instructorService;

    @Autowired
    public CourseController(GroupService groupService, CourseService courseService, InstructorService instructorService) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    @GetMapping("/courses/{id}")
    public String getAllCourses(@PathVariable("id") Long id,Model model,
                                @ModelAttribute("group") Group group,
                                @ModelAttribute("instructor") Instructor instructor) {
        model.addAttribute("courses", courseService.getAllCourses(id));
        model.addAttribute("groups", groupService.getFullGroups());
        model.addAttribute("instructors", instructorService.getInstructorList());
        model.addAttribute("companyId", id);
        return "/courses/courses";
    }
    @GetMapping("/courses/{id}/newCourse")
    public String newCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId", id);
        return "/courses/newCourse";
    }

    @PostMapping("/{id}/saveCourse")
    public String create(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "/courses/newCourse";
        }
        courseService.saveCourse(id, course);
        return "redirect:/courses/" + id;
    }

    @GetMapping("/editCourse/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "/courses/editCourse";
    }
    @PostMapping("/{companyId}/{courseId}/saveUpdateCourse")
    public String saveUpdateCourse(@PathVariable("companyId") Long companyId,
                                   @PathVariable("courseId") Long courseId,
                                   @ModelAttribute("course") Course course) {
        courseService.updateCourse(courseId, course);
        return "redirect:/courses/" + companyId;
    }
    @PostMapping("/{courseId}/assignGroup")
    private String assignGroup(@PathVariable("courseId") Long courseId,
                               @ModelAttribute("group") Group group) throws Exception {
        Long id = group.getId();
        groupService.assignGroup(courseId, id);
        return "redirect:/groups/" + courseId;
    }

    @PostMapping("/{courseId}/assignInstructor")
    private String assignInstructor(@PathVariable("courseId") Long courseId,
                                    @ModelAttribute("instructor") Instructor instructor) throws IOException {
        Long id = instructor.getId();
        instructorService.assignInstructor(courseId, id);
        return "redirect:/instructors/" + courseId;
    }

    @RequestMapping("/{companyId}/deleteCourse/{courseId}")
    public String deleteCourse(@PathVariable("companyId") Long companyId
            , @PathVariable("courseId") Long courseId) {
        courseService.deleteCourseById(courseId);
        return "redirect:/courses/" + companyId;
    }
}

