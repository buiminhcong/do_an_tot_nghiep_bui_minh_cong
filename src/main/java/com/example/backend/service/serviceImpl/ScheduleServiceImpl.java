package com.example.backend.service.serviceImpl;

import com.example.backend.entity.*;
import com.example.backend.entity.Class;
import com.example.backend.entity.ga.Data;
import com.example.backend.entity.ga.GeneticAlgorithm;
import com.example.backend.entity.ga.Population;
import com.example.backend.entity.ga.Schedule;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

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

    public static final int POPULATION_SIZE = 9; // Size của quần thể khởi tạo 1 thế hệ có 9 quần thể
    public static final double MUTATION_RATE = 0.1; // tỷ lệ đột biến
    public static final double CROSSOVER_RATE = 0.9; // tỷ lệ lai ghép
    // số lượng cá thể có trong quần thể cạnh tranh tournamentPopulation
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMBER_OF_ELITE_SCHEDULE = 1; // số cá thể ưu tú
    private Data data; // Dữ liệu đầu vào
    private int scheduleNumb = 0;
    private int classNumb = 1;


    @Override
    public Schedule gaSchedule() {

        List<Room> rooms =  roomService.getListRoom();

        // add meetting time
        List<MeetingTime> meetingTimes = meetingTimeService.getListMeetingTime();

        //get course
        List<Course>  courses =  courseService.getListCourse();

        //get depart ment
        List<Department> departments = departmentService.getListDepartment();

        Data data = new Data(rooms, courses, departments,meetingTimes);
//        Data data = new Data();
        this.data = data;
        int generationNumber = 0; // số lần sinh

        System.out.println("> Generation # " + generationNumber);
        System.out.print("  Schedule # |              ");
        System.out.printf("Classes [department, class, room, instructor, meeting-time]");
        System.out.printf("        |      Fitness     |      Conflicts   " );
        System.out.print("---------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------");
        // Khởi tạo thuật toán
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(data);
        // Khởi tạo quần thể và sort quần thể theo sức mạnh quần thể quá trình đánh giá
        Population population = new Population(POPULATION_SIZE, data).sortByFitness();
        // lặp các thế hệ
        population.getSchedules().forEach(schedule -> System.out.println("      " + scheduleNumb++ +
                "    | " + schedule + " | " +
                String.format("%.5f", schedule.getFitness()) +
                " |  " + schedule.getNumberOfConflicts()
        ));
        // In ra cá thể tốt nhất của từng quần thể qua các thế hệ

        classNumb = 1;
        // nếu chưa có cá thể tốt chạy tiếp thuật toán

        List<Population> result  = new ArrayList<>();

        while (population.getSchedules().get(0).getFitness() != 1){
            System.out.println(">Generation # " + ++generationNumber);
            System.out.print("  Schedule # |              ");
            System.out.printf("Classes [department, class, room, instructor, meeting-time]");
            System.out.printf("        |      Fitness     |      Conflicts   " );
            System.out.print("---------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------");
            population = geneticAlgorithm.evolve(population).sortByFitness();
            scheduleNumb = 0;
            population.getSchedules().forEach(schedule -> System.out.println("      " + scheduleNumb++ +
                    "    | " + schedule + " | " +
                    String.format("%.5f", schedule.getFitness()) +
                    " |  " + schedule.getNumberOfConflicts()
            ));
            classNumb = 1;
//            printScheduleTable(population.getSchedules().get(0), generationNumber);
            population.getSchedules().get(0).setGenaration(generationNumber);
            result.add(population);
        }
        Population p = result.get(result.size()-1);
        return (Schedule) p.getSchedules().get(0);
    }

    private void printScheduleTable(Schedule schedule, int generation){
        List<Class> classes = schedule.getClasses();
        System.out.print("\n    ");
        System.out.println("Class #  |     Dept     | Course (Numb, max # of students) | Room (Capacity) |  Instructor(ID)   | MeetingTime(ID)");
        System.out.print("      ");
        System.out.print("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
        classes.forEach(x->{
            int majorIndex = data.getDepartments().indexOf(x.getDept());
            int courseIndex =data.getCourses().indexOf(x.getCourse());
            int roomsIndex = data.getRooms().indexOf(x.getRoom());
//            int instructorsIndex = data.getInstructors().indexOf(x.getInstructor());
            int meetingTimeIndex = data.getMeetingTimes().indexOf(x.getMeetingTime());
            System.out.print("      ");
            System.out.print(String.format("  %1$02d ", classNumb)+" | ");
            System.out.print(String.format("%1$4s", data.getDepartments().get(majorIndex).getName() + "  | "));
            System.out.print(String.format("%1$21s", data.getCourses().get(courseIndex).getNumber()+", "+
                    data.getCourses().get(courseIndex).getName()+", "+
                    x.getCourse().getMaxNumbOfStudents()+"("+x.getCourse().getMaxNumbOfStudents())+")           | ");
            System.out.print(String.format("%1$10s", data.getRooms().get(roomsIndex).getNumber() +
                    " ("+x.getRoom().getSeatingCapacity()) +")     | " );
//            System.out.print(String.format("%1$15s", data.getInstructors().get(instructorsIndex).getUser().getName() +
//                    " ("+data.getInstructors().get(instructorsIndex).getId()+")") +"   |  ");
            System.out.println(data.getMeetingTimes().get(meetingTimeIndex).getName()+
                    " ("+data.getMeetingTimes().get(meetingTimeIndex).getId()+")");
            System.out.println("---");
            classNumb++;
        });
        if(schedule.getFitness() == 1){
            System.out.println("> solution found in "+(generation+1)+" generations");
            System.out.print("----------------------------------------------------");
            System.out.println("----------------------------------------------------");
        }
    }
}
