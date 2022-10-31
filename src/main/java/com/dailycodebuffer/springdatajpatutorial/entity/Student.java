package com.dailycodebuffer.springdatajpatutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//This is the name this table will have in the database.
//If the application was already run before this property was added,
//the database will create another table called tbl_student alongside the already existing table student
@Table(
        name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {

    //This defines that this studentId field is the primary key
    @Id
    //Sequence generator defines how the id will be stored and the allocation size
    //which is like the increment of the id that will be stored
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    //generated value applies what has been defined in the sequence generator
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;
    private String firstName;
    private String lastName;
    //This was change the name of the emailId property to email_address
    @Column(
            name="email_address",
            nullable = false
    )
    private String emailId;

    @Embedded
    private Guardian guardian;
}
