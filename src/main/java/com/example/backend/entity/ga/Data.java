package com.example.backend.entity.ga;

import com.example.backend.entity.*;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {

    private List<Room> rooms;

    private List<Instructor> instructors;

    private List<Course> courses;

    private List<Department> departments;

    private List<MeetingTime> meetingTimes;

    private int numberOfClass = 0;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MeetingTimeService meetingTimeService;

    @Autowired
    private InstructorCourseService instructorCourseService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DepartmentService departmentService;

    public Data() {
        initialize();
    }

    public Data(List<Room> rooms, List<Course> courses, List<Department> departments, List<MeetingTime> meetingTimes) {
        this.rooms = rooms;
        this.courses = courses;
        this.departments = departments;
        this.meetingTimes = meetingTimes;
    }

    //Create data
    public Data initialize(){

        // add room
        System.out.println("-----------");
        System.out.println("Size " +roomService.getListRoom().size());
       rooms =  roomService.getListRoom();

       // add meetting time
        meetingTimes = meetingTimeService.getListMeetingTime();

        //get course
        courses =  courseService.getListCourse();

        //get depart ment
        departments = departmentService.getListDepartment();

        return this;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public int getNumberOfClass() {
        return numberOfClass;
    }

}
