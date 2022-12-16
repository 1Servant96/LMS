package com.peaksoft.entities.course;


import java.util.List;

public interface CourseRepository {
    void saveCourse(Long id, Course course);
    void deleteCourseById(Long id);
    Course getCourseById(Long id);
    List<Course> getAllCourses(Long id);
    void updateCourse(Long id, Course course);

}
