package com.example.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Instructor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String code;

    private int deleted;

    @ManyToOne
    private Subject subject;

    @OneToOne
    private User user;

    public Instructor() {
    }

    public Instructor(int id, String code, int deleted, Subject subject) {
        this.id = id;
        this.code = code;
        this.deleted = deleted;
        this.subject = subject;
    }
}
