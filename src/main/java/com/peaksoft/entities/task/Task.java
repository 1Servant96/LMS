package com.peaksoft.entities.task;

import com.peaksoft.entities.lesson.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskName;
    private LocalDate deadline;
    @OneToOne(cascade = CascadeType.ALL)
    private Lesson lesson;

    public void indicator(Lesson lesson){
        this.lesson = lesson;
    }
}





