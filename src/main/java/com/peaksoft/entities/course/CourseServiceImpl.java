package com.peaksoft.entities.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses(Long id) {
        return courseRepository.getAllCourses(id);
    }

    @Override
    public void updateCourse(Long id, Course course) {
        courseRepository.updateCourse(id, course);
    }

    @Override
    public void saveCourse(Long id, Course course) {
        courseRepository.saveCourse(id, course);
    }

    @Override
    public void deleteCourseById(Long id) {
    courseRepository.deleteCourseById(id);
    }

    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }
}
