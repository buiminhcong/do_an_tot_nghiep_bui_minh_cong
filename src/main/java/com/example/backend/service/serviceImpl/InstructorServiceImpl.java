package com.example.backend.service.serviceImpl;

import com.example.backend.dto.TeacherRequest;
import com.example.backend.entity.*;
import com.example.backend.exception.NotFoundException;
import com.example.backend.repository.*;
import com.example.backend.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private InstructorCourseRepository instructorCourseRepository;

    @Autowired
    private NameRepository nameRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private CourseRepository courseRepository;

//    public InstructorServiceImpl(InstructorCourseRepository instructorCourseRepository){
//        this.instructorCourseRepository = instructorCourseRepository;
//    }


    @Override
    public List<Instructor> getListInstructor() {
        List<Instructor> list = instructorRepository.findAllInstructor();
        return list;
    }

    @Override
    public Instructor createInstructor(TeacherRequest request) throws NotFoundException {

        Optional<Instructor> op1 =
                Optional.ofNullable(instructorRepository.findInstructorByCode(request.getCode()));

        if (!op1.isPresent()) {

            Optional<Subject> op2 =
                    Optional.ofNullable(subjectRepository.findSubjectById(request.getId_subject()));

            if (op2.isPresent()) {

                Optional<User> op3 = Optional.ofNullable(userRepository.findUserByUserName(request.getUsername()));

                if(!op3.isPresent()){
                    // get subject by id
                    Subject subject = op2.get();
                    int dep_id = subject.getDepartment().getId();

                    // create Name
                    Name name = new Name();
                    name.setFirstName(request.getFirstName());
                    name.setLastName(request.getLastName());
                    name.setMiddleName(request.getMiddleName());
                    name.setDeleted(0);
                    nameRepository.save(name);

                    //create address
                    Address address = new Address();
                    address.setDeleted(0);
                    address.setEmail(request.getEmail());
                    address.setPhone(request.getPhone());
                    address.setCity(request.getCity());
                    addressRepository.save(address);

                    // create user
                    User user = new User();
                    user.setDeleted(0);
                    user.setUserName(request.getUsername());
                    user.setPassword(request.getPassword());
                    user.setAddress(address);
                    user.setName(name);
                    userRepository.save(user);

                    //create teacher
                    Instructor instructor = new Instructor();
                    instructor.setDeleted(0);
                    instructor.setSubject(subject);
                    instructor.setCode(request.getCode());
                    instructor.setUser(user);
                    instructorRepository.save(instructor);

                    //create role
                    UserRole userRole = new UserRole();
                    userRole.setUser(user);
                    userRole.setDeleted(0);
                    userRole.setRole(roleRepository.getRoleByName("INSTRUCTER"));
                    userRoleRepository.save(userRole);

                    //get course by id subject
                    List<Course> list = courseRepository.getListCourseByIdDepartment(dep_id);
                    for(Course c : list){
                        InstructorCourse i = new InstructorCourse();
                        i.setDeleted(0);
                        i.setInstructor(instructor);
                        i.setCourse(c);
                        instructorCourseRepository.save(i);
                    }

                    return instructor;
                }else {
                    throw new NotFoundException("Username is exits: " + request.getUsername());
                }

            } else {
                throw new NotFoundException("Subject not found with id: " + request.getId_subject());
            }
        } else {
            throw new NotFoundException("Teacher code is exits");
        }
    }

    @Override
    public Instructor updateInstructor(int id, TeacherRequest request) throws NotFoundException {

        Optional<Instructor> op = Optional.ofNullable(instructorRepository.findInstructorByID(id));
        Instructor instructor = op.get();
        if(op.isPresent()){

            Optional<Instructor> op1 =
                    Optional.ofNullable(instructorRepository.findInstructorByCode(request.getCode()));

            if ( op1.isEmpty() ) {

                Optional<Subject> op2 =
                        Optional.ofNullable(subjectRepository.findSubjectById(request.getId_subject()));

                if (op2.isPresent()) {

                    Optional<User> op3 = Optional.ofNullable(userRepository.findUserByUserName(request.getUsername()));

                    if(!op3.isPresent() || op3.get().getUserName().equals(request.getUsername())){
                        // get subject by id
                        Subject subject = op2.get();
                        int dep_id = subject.getDepartment().getId();

                        // update Name
                        Name name = instructor.getUser().getName();
                        name.setFirstName(request.getFirstName());
                        name.setLastName(request.getLastName());
                        name.setMiddleName(request.getMiddleName());
                        nameRepository.save(name);

                        //update address
                        Address address = instructor.getUser().getAddress();
                        address.setEmail(request.getEmail());
                        address.setPhone(request.getPhone());
                        address.setCity(request.getCity());
                        addressRepository.save(address);

                        // update user
                        User user = instructor.getUser();
                        user.setUserName(request.getUsername());
                        user.setPassword(request.getPassword());
                        user.setAddress(address);
                        user.setName(name);
                        userRepository.save(user);

                        //get subject old
                        Subject oldSubject = instructor.getSubject();

                        //update teacher
                        instructor.setSubject(subject);
                        instructor.setCode(request.getCode());
                        instructor.setUser(user);
                        instructorRepository.save(instructor);

                        if(oldSubject.getId() != subject.getId()){
                            List<InstructorCourse> l1 =
                                    instructorCourseRepository.getListCourseByIdTeacher(id);
                            for(InstructorCourse c: l1){
                                c.setDeleted(1);
                            }
                            //get course by id subject
                            List<Course> list = courseRepository.getListCourseByIdDepartment(dep_id);
                            for(Course c : list){
                                InstructorCourse i = new InstructorCourse();
                                i.setDeleted(0);
                                i.setInstructor(instructor);
                                i.setCourse(c);
                                instructorCourseRepository.save(i);
                            }
                            return instructor;
                        }else {
                            return instructor;
                        }


                    }else {
                        throw new NotFoundException("Username is exits: " + request.getUsername());
                    }

                } else {
                    throw new NotFoundException("Subject not found with id: " + request.getId_subject());
                }
            } else {
                Instructor i1 = op.get();
                System.out.println("ok  "+ i1.getCode());
                if(i1.getCode().equals(request.getCode().trim())){
                    Optional<Subject> op2 =
                            Optional.ofNullable(subjectRepository.findSubjectById(request.getId_subject()));

                    if (op2.isPresent()) {

                        Optional<User> op3 = Optional.ofNullable(userRepository.findUserByUserName(request.getUsername()));

                        if(!op3.isPresent() || op3.get().getUserName().equals(request.getUsername())){
                            // get subject by id
                            Subject subject = op2.get();
                            int dep_id = subject.getDepartment().getId();

                            // update Name
                            Name name = instructor.getUser().getName();
                            name.setFirstName(request.getFirstName());
                            name.setLastName(request.getLastName());
                            name.setMiddleName(request.getMiddleName());
                            nameRepository.save(name);

                            //update address
                            Address address = instructor.getUser().getAddress();
                            address.setEmail(request.getEmail());
                            address.setPhone(request.getPhone());
                            address.setCity(request.getCity());
                            addressRepository.save(address);

                            // update user
                            User user = instructor.getUser();
                            user.setUserName(request.getUsername());
                            user.setPassword(request.getPassword());
                            user.setAddress(address);
                            user.setName(name);
                            userRepository.save(user);

                            //get subject old
                            Subject oldSubject = instructor.getSubject();

                            //update teacher
                            instructor.setSubject(subject);
                            instructor.setCode(request.getCode());
                            instructor.setUser(user);
                            instructorRepository.save(instructor);

                            if(oldSubject.getId() != subject.getId()){
                                List<InstructorCourse> l1 =
                                        instructorCourseRepository.getListCourseByIdTeacher(id);
                                for(InstructorCourse c: l1){
                                    c.setDeleted(1);
                                }
                                //get course by id subject
                                List<Course> list = courseRepository.getListCourseByIdDepartment(dep_id);
                                for(Course c : list){
                                    InstructorCourse i = new InstructorCourse();
                                    i.setDeleted(0);
                                    i.setInstructor(instructor);
                                    i.setCourse(c);
                                    instructorCourseRepository.save(i);
                                }
                                return instructor;
                            }else {
                                return instructor;
                            }


                        }else {
                            throw new NotFoundException("Username is exits: " + request.getUsername());
                        }

                    } else {
                        throw new NotFoundException("Subject not found with id: " + request.getId_subject());
                    }
                }else {
                    throw new NotFoundException("Teacher code is exits");
                }
//                throw new NotFoundException("Teacher code is exits");
            }

        }else {



            throw new NotFoundException("Not found teacher with id : "+ id);
        }

    }

    @Override
    public Instructor findInstructorByCode(String code) throws NotFoundException {

        Instructor i = instructorRepository.findInstructorByCode(code);
        if (i != null) {
            return i;
        } else {
            throw new NotFoundException("Not found Instructor with code : " + code);
        }
    }

    @Override
    public Instructor removeInstructorById(int id) throws NotFoundException {

        Optional<Instructor> i = instructorRepository.findById(id);
        if (i.isPresent()) {
            return i.get();
        } else {
            throw new NotFoundException("Not found Instructor with code : " + id);
        }
    }

    @Override
    public List<Instructor> getListInstructorByIdCourse(int id_course) throws NotFoundException {

        List<Instructor> instructors = new ArrayList<>();
        List<InstructorCourse> list = instructorCourseRepository.getInstructorCourseByIdCourse(id_course);

        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                instructors.add(list.get(i).getInstructor());
            }
            return instructors;
        }
        throw new NotFoundException("Not found instructor in course with id " + id_course);
    }

    @Override
    public List<Instructor> getListInstructorByIdSubject(int id_subject) {
        return instructorRepository.findListInstructorBySubject(id_subject);
    }
}
