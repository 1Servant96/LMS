package com.peaksoft.entities.task;

import com.peaksoft.entities.lesson.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    private Long id;
    @NotEmpty(message = "you need to write something")
    private String taskName;
    @NotEmpty(message = "you need to make the deadline")
    private LocalDate deadline;
    @OneToOne(cascade = CascadeType.ALL)
    private Lesson lesson;

    public void indicator(Lesson lesson){
        this.lesson = lesson;
    }
}





