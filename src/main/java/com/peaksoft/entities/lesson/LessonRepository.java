package com.peaksoft.entities.lesson;

import java.util.List;

public interface LessonRepository {
    List<Lesson> getAllLessons(Long courseId);

    void saveLesson(Long id, Lesson lesson);

    Lesson getLessonById(Long id);

    void updateLesson(Lesson lesson, Long id);

    void deleteLessonById(Long id);
}
