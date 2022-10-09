package com.example.backend.entity;

import com.example.backend.entity.ga.Schedule;
import com.example.backend.entity.ga.ScheduleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Department dept;
    @OneToOne
    private Course course;
    @OneToOne
    private Instructor instructor;
    @OneToOne
    private MeetingTime meetingTime;
    @OneToOne
    private Room room;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    @JsonIgnore
    private ScheduleEntity schedule;

    private int deleted;

    public Class(int id, Department dept, Course course) {
        this.id = id;
        this.dept = dept;
        this.course = course;
    }

    public Class() {
    }

    public int isDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

//    @Override
//    public String toString() {
//        return "[" + dept.getName() + "," + course.getNumber() + "," + room.getNumber() + "," + instructor.getId() + "," + meetingTime.getId() + "]";
//    }

}
