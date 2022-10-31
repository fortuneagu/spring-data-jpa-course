package com.dailycodebuffer.springdatajpatutorial.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    // The way this class is set up now, before you can create a course material there must be an
    // existing course in the db and if there isn't one then you must save the course before you save the course material.
    // but with the use of the cascading type, if this course property does not exist in the db already, then it creates it before saving the
    // course material. Example is in CourseMaterialRepositoryTest.java
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            // fetch defines whether we want to fetch the Course object when we
            // fetch the course material. When we define it as lazy it does not fetch the course object
            // but when we define it as EAGER it fetches the course object as well.
            // you also have to exclude the toString method when fetching as lazy because
            // the course object is not being returned.
            optional = false
            //optional false means that whenever we create a course we must create a course material
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
