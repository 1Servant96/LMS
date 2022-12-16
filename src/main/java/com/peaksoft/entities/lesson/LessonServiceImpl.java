package com.peaksoft.entities.lesson;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class LessonServiceImpl implements LessonService {
    private LessonRepositoryImpl lessonRepository;

    @Override
    public List<Lesson> getAllLessons(Long courseId) {
        return lessonRepository.getAllLessons(courseId);
    }

    @Override
    public void saveLesson(Long id, Lesson lesson) {
        lessonRepository.saveLesson(id, lesson);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.getLessonById(id);
    }

    @Override
    public void updateLesson(Lesson lesson, Long id) {
        lessonRepository.updateLesson(lesson, id);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteLessonById(id);
    }
}
