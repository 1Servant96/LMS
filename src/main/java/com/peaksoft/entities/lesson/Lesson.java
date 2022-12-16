package com.peaksoft.entities.lesson;

import com.peaksoft.entities.course.Course;
import com.peaksoft.entities.task.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lessonName;
    @OneToOne
    private Course course;
    @OneToMany
    private List<Task> tasks = new ArrayList<>();


    public void addTaskToLesson(Task task){
        if(tasks.stream().noneMatch(x -> Objects.equals(x.getId(), task.getId()))){
        tasks.add(task);
        }
    }
}
