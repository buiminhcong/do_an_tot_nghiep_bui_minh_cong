package com.example.backend.entity.ga;

import com.example.backend.entity.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Data {

    private ArrayList<Room> rooms;

    private ArrayList<Instructor> instructors;

    private ArrayList<Course> courses;

    private ArrayList<Department> departments;

    private ArrayList<MeetingTime> meetingTimes;

    private int numberOfClass = 0;

    public Data() {
        initialize();
    }

    //Create data
    public Data initialize(){
//        //Add room
//        Room room1 = new Room("P101", 25);
//        rooms = new ArrayList<Room>(Arrays.asList(room1));
//
//        //Add meeting
//        MeetingTime meetingTime1 = new MeetingTime("t2-k1", "Thu 2 07:00 - 08:50");
//        meetingTimes = new ArrayList<MeetingTime>
//                (Arrays.asList(meetingTime1));
//
//        //Add Teacher
//        Instructor instructor1 = new Instructor("T1", "Nguyen Trong Khanh");
//        Instructor instructor2 = new Instructor("T2", "Nguyen Manh Son");
//        Instructor instructor3 = new Instructor("T3", "Nguyen Van Hoan");
//        Instructor instructor4 = new Instructor("T4", "T.T.V.Anh");
//        Instructor instructor5 = new Instructor("T5", "Tran Dinh Que");
//        Instructor instructor6 = new Instructor("T6", "Nguyen Manh Hung");
//        Instructor instructor7 = new Instructor("T7", "Dang Ngoc Hung");
//        Instructor instructor8 = new Instructor("T8", "Bui Minh Cong");
//        Instructor instructor9 = new Instructor("T9", "Bui Minh Chien");
//        Instructor instructor10 = new Instructor("T10", "Bui Tieng Anh");
//        Instructor instructor11 = new Instructor("T11", "Ngo Thi Ly");
//        Instructor instructor12 = new Instructor("T12", "Nguyen Ngoc Phuong");
//        Instructor instructor13 = new Instructor("T13", "Do Xuan Cho");
//        Instructor instructor14 = new Instructor("T14", "Trinh Van Quyet");
//        Instructor instructor15 = new Instructor("T15", "Ngo Thi Mai");
//        instructors = new ArrayList<Instructor>(Arrays.asList(instructor1, instructor2,
//                instructor3, instructor4, instructor5, instructor6, instructor7, instructor8,
//                instructor9, instructor10, instructor11, instructor12, instructor13, instructor14, instructor15));
//
//        //Add Course môn học
//        Course course1 = new Course("C1", "HDV",
//                25,  new ArrayList<Instructor>(Arrays.asList(instructor1, instructor7)));
//        Course course2 = new Course("C2", "LTM",
//                35,  new ArrayList<Instructor>(Arrays.asList(instructor1, instructor6, instructor7)));
//        Course course3 = new Course("C3", "LTW",
//                25,  new ArrayList<Instructor>(Arrays.asList(instructor3, instructor2)));
//        Course course4 = new Course("C4", "ANDROID",
//                30,  new ArrayList<Instructor>(Arrays.asList(instructor4, instructor8, instructor9)));
//        Course course5 = new Course("C5", "IOS",
//                35,  new ArrayList<Instructor>(Arrays.asList(instructor4, instructor9)));
//        Course course6 = new Course("C6", "Dai so",
//                45,  new ArrayList<Instructor>(Arrays.asList(instructor12, instructor14)));
//        Course course7 = new Course("C7", "Giai tich 1",
//                45,  new ArrayList<Instructor>(Arrays.asList(instructor12, instructor14, instructor15)));
//        Course course8 = new Course("C8", "Giai tich 2",
//                50,  new ArrayList<Instructor>(Arrays.asList(instructor12, instructor14, instructor15)));
//        Course course9 = new Course("C9", "Tieng Anh 1",
//                60,  new ArrayList<Instructor>(Arrays.asList(instructor10)));
//        Course course10 = new Course("C10", "Triet 1",
//                45,  new ArrayList<Instructor>(Arrays.asList(instructor11, instructor13)));
//        courses = new ArrayList<Course>(Arrays.asList(course1, course2, course3, course4,
//                course5, course6, course7, course8, course9, course10));
//        // Khoa
//        Department dept1 = new Department("Mon co so", new ArrayList<Course>(Arrays.asList(course6, course7
//                ,course8, course9, course10)));
//        Department dept2 = new Department("CNPM", new ArrayList<Course>(Arrays.asList(course4, course5,
//                course1, course3, course3)));
//        Department dept3 = new Department("HTTT", new ArrayList<Course>(Arrays.asList(course2)));
//
//        departments = new ArrayList<Department>(Arrays.asList(dept1, dept2, dept3));
//        // số lớp bằng size departments.getCourse
//        departments.forEach(x -> numberOfClass+=x.getCourses().size());
//
        return this;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public int getNumberOfClass() {
        return numberOfClass;
    }

}
