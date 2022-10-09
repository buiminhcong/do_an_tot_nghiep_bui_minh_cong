package com.example.backend.entity.ga;

import com.example.backend.entity.Class;
import com.example.backend.entity.Department;
import com.example.backend.entity.Instructor;
import com.example.backend.repository.InstructorCourseRepository;
import com.example.backend.service.InstructorService;
import com.example.backend.service.serviceImpl.InstructorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class Schedule {

    private Integer id;

    private List<Class> classes;

    private int classNumb = 0; // số lớp

    private boolean isFitnessChanged = true; // thể trạng thay đổi

    private double fitness = -1; // thể trạng

    private int numberOfConflicts = 0; // số  lượng xung đột

    private Data data; // Dữ liệu đầu vào

    @Autowired
    private InstructorService instructorService ;


    public Data getData() {
        return data;
    }

    //Hàm tạo & lấy ra số lớp trong 1 thời khóa biểu
    public Schedule(Data data) {
        this.data = data;
        classes = new ArrayList<Class>(data.getNumberOfClass()); // lấy ra số lớp cần tạo trong tkb
    }

    public Schedule initialize() { // -----1-----
        List<Department> list = data.getDepartments();
        list.forEach(dept -> { // loop các phòng ban
            dept.getCourses().forEach(course -> { // duyệt các course trong department
                Class newClass = new Class(classNumb++, dept, course); // tạo lớp
                newClass.setMeetingTime(data.getMeetingTimes().get(       // set slot ngẫu nhiên trong list meetting time
                        (int) (data.getMeetingTimes().size() * Math.random())));

                newClass.setRoom(data.getRooms().get( //set room ngẫu nhiên trong list room
                        (int) (data.getRooms().size() * Math.random())));

                //set ngẫu nhiên giáo viên vào trong từng khóa học

                System.out.println("oooooooooookokokoko");
                List<Instructor> listInstructorOfCourse =
                        this.instructorService.getListInstructorByIdCourse(course.getId());
                System.out.println(listInstructorOfCourse.size());
                newClass.setInstructor(listInstructorOfCourse.get(
                        (int) (listInstructorOfCourse.size() * Math.random())));

                classes.add(newClass); // thêm class mới vào list class
            });
        });
        return this; // trả về schedule
    }

    //lấy ra số xung đột
    public int getNumberOfConflicts() {
        return numberOfConflicts;
    }


    public List<Class> getClasses() {
        isFitnessChanged = true;
        return classes;
    }

    public double getFitness() {
        if (isFitnessChanged == true) { // nếu trạng thái true thì tiến hành đánh giá
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    // tính toán đánh giá thể trạng => b2 đánh giá năng lực độ thích nghi của từng cá thể
    public double calculateFitness() {
        numberOfConflicts = 0; // conflict = 0
        classes.forEach(x -> {
            // sức chứa phòng < số lượng sv trong course => conflict
            if (x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumbOfStudents()) numberOfConflicts++;
            // lọc
            classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> {
                if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()) { // kíp học trùng !ID
                    if (x.getRoom() == y.getRoom()) numberOfConflicts++; //  cùng phòng
                    if (x.getInstructor() == y.getInstructor()) numberOfConflicts++; // cùng giảng viên
                }
            });
        });

        return 1 / (double) (numberOfConflicts + 1); // đưa ra tỉ lệ năng lực bằng 1/(conf + 1)*100%
    }

//    @Override
//    public String toString() {
//        String result = new String();
//        for (int x = 0; x < classes.size() - 1; x++) {
//            result += classes.get(x) + ", ";
//        }
//        result += classes.get(classes.size() - 1);
//        return result;
//    }

}
