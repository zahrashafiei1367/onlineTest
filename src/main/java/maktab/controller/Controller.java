package maktab.controller;

import maktab.model.entity.*;
import maktab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassificationService classificationService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ExamService examService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String showSignInUser(Model model) {
        model.addAttribute("user", new User());
        return "signIn";
    }

    @RequestMapping(value = "/editProcess", method = RequestMethod.GET)
    public String edit(@ModelAttribute("user") User user,
                       Model model) {
        try {
            userService.update(user);
            User user1 = userService.findById(user.getId());
            Admin admin = userService.getAdmin(user1);
            model.addAttribute("admin", admin);
            model.addAttribute("students", studentService.showAllStudent(admin));
            model.addAttribute("teachers", teacherService.showAllTeachers(admin));
            return ("welcome");
        } catch (Exception e) {
            model.addAttribute("errorMsg", "user is:" + user.toString());
            return ("error");
        }
    }

    @RequestMapping(value = "/signInProcess", method = RequestMethod.POST)
    public String signInUser(@ModelAttribute("user") User user,
                             Model model) {
        try {
            //Page<Student> students = studentService.showAllStudent(0,2);

            Admin admin = adminService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            model.addAttribute("students", studentService.showAllStudent(admin));
            model.addAttribute("teachers", teacherService.showAllTeachers(admin));
            model.addAttribute("admin", admin);
            return ("welcome");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }
    }

    @RequestMapping(value = "/signIn2", method = RequestMethod.GET)
    public String showSignInStudentOrTeacher(Model model) {
        model.addAttribute("user", new User());
        return "signIn2";
    }

    @RequestMapping(value = "/signInProcess2", method = RequestMethod.POST)
    public String signInStudentOrTeacher(@ModelAttribute("user") User user,
                                         Model model) {
        try {
            if (user.getAuthority().equals("teacher")) {
                Teacher teacher = teacherService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
                model.addAttribute("teacher", teacher);
                model.addAttribute("firstName", teacher.getName());
            } else if (user.getAuthority().equals("student")) {
                Student student = studentService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
                model.addAttribute("student", student);
                model.addAttribute("firstName", student.getName());
            }
            return ("welcome2");
        } catch (Exception e) {
            model.addAttribute("errorMsg", "username or password is incorrect.");
            return ("error");

        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterStudentOrTeacher(Model model) {
        model.addAttribute("admin", new Admin());
        return ("register");
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public String RegisterStudentOrTeacher(@Valid @ModelAttribute("admin") Admin admin, BindingResult br,
                                           Model model) {
        if (br.hasErrors())
            return "register";
        try {
            if (admin.getAuthority().equals("admin")) {
                admin.setEnabled(true);
                adminService.registerNewAdmin(admin);
                List<Student> students = new ArrayList<>();
                List<Teacher> teachers = new ArrayList<>();
                model.addAttribute("admin", admin);
                model.addAttribute("students", students);
                model.addAttribute("teachers", teachers);
                return "welcome";
            } else if (admin.getAuthority().equals("teacher")) {
                Teacher teacher = makeTeacherObjectByUser(admin);
                teacherService.registerNewTeacher(teacher);
            } else {
                Student student = makeStudentObjectByUser(admin);
                studentService.registerNewStudent(student);
            }
            model.addAttribute("name", admin.getName());
            return ("welcome2");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }
    }

//    private Admin makeAdminObjectByUser(User user) {
//        Admin admin = new Admin();
//        admin.setEnabled(true);
//        admin.setUsername(user.getUsername());
//        admin.setFamily(user.getFamily());
//        admin.setPassword(user.getPassword());
//        admin.setName(user.getName());
//        return admin;
//    }


    private Student makeStudentObjectByUser(Admin user) throws Exception {
        Student student = new Student();
        student.setAdmin(adminService.findBySchoolName(user.getSchoolName()));
        student.setUsername(user.getUsername());
        student.setFamily(user.getFamily());
        student.setPassword(user.getPassword());
        student.setName(user.getName());
        return student;
    }

    private Teacher makeTeacherObjectByUser(Admin admin) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setAdmin(adminService.findBySchoolName(admin.getSchoolName()));
        teacher.setUsername(admin.getUsername());
        teacher.setFamily(admin.getFamily());
        teacher.setPassword(admin.getPassword());
        teacher.setName(admin.getName());
        return teacher;
    }

    @RequestMapping(value = "/addNewStudent", method = RequestMethod.GET)
    public String showAddNewStudent(Model model, @RequestParam("id") int id) {
        try {
            model.addAttribute("admin", userService.findById(id));
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
        model.addAttribute("student", new Student());
        return ("addNewStudent");
    }

    @RequestMapping(value = "/addNewStudentProcess", method = RequestMethod.POST)
    public String addNewStudent(@ModelAttribute("student") Student student, @ModelAttribute("admin") Admin adminUser,
                                Model model) {
        try {
            student.setAdmin(adminUser);
            studentService.registerNewStudent(student);
            model.addAttribute("students", studentService.showAllStudent(adminUser));
            model.addAttribute("teachers", teacherService.showAllTeachers(adminUser));
            return ("welcome");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }

    }

    @RequestMapping(value = "/addNewTeacher", method = RequestMethod.GET)
    public String showAddNewTeacher(Model model, @RequestParam("id") int id) {
        try {
            model.addAttribute("admin", userService.findById(id));
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
        model.addAttribute("teacher", new Teacher());
        return ("addNewTeacher");
    }

    @RequestMapping(value = "/addNewTeacherProcess", method = RequestMethod.POST)
    public String addNewTeacher(@ModelAttribute("teacher") Teacher teacher, @ModelAttribute("admin") Admin adminUser,
                                Model model) {
        try {
            teacher.setEnabled(true);
            teacher.setAdmin(adminUser);
            teacherService.registerNewTeacher(teacher);
            model.addAttribute("students", studentService.showAllStudent(adminUser));
            model.addAttribute("teachers", teacherService.showAllTeachers(adminUser));
            return ("welcome");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }

    }

    @RequestMapping(value = "/deleteByUsername", method = RequestMethod.GET)
    public String deleteByUsername(Model model, @RequestParam("id") int id) {
        try {
            User user = userService.findById(id);
            Admin admin = userService.getAdmin(user);
            userService.deleteById(id);
            model.addAttribute("students", studentService.showAllStudent(admin));
            model.addAttribute("teachers", teacherService.showAllTeachers(admin));
            return ("welcome");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", new User());
        try {
            model.addAttribute("admin", adminService.findById(id));
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
        return ("search");

    }


    @RequestMapping(value = "/searchProcess", method = RequestMethod.POST)
    public String showSearchResult(@ModelAttribute("user") User user, @RequestParam("adminId") int adminId,
                                   Model model) {
        try {
            Admin admin = adminService.findById(adminId);
            List<Student> students = studentService.findMaxMatch(user.getName(), user.getFamily(), user.getUsername(), admin);
            List<Teacher> teachers = teacherService.findMaxMatch(user.getName(), user.getFamily(), user.getUsername(), admin);
            List<User> users = new ArrayList<>();
            for (int i = 0; i < students.size(); i++) {
                User studentUser = students.get(i);
                users.add(studentUser);
            }
            for (int i = 0; i < teachers.size(); i++) {
                User teacherUser = teachers.get(i);
                users.add(teacherUser);
            }
            model.addAttribute("users", users);
            model.addAttribute("admin", admin);
            return ("usersResultSearch");

        } catch (Exception e) {
            model.addAttribute("errorMsg", "admin id did not found");
            return "error";
        }


    }


    @RequestMapping(value = "/addNewCourse", method = RequestMethod.GET)
    public String showAddNewCourse(Model model, @RequestParam("id") int id) {
        try {
            model.addAttribute("course", new Course());
            model.addAttribute("classifications", classificationService.findAll());
            model.addAttribute("adminId", id);
            return ("addNewCourse");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }

    }

    @RequestMapping(value = "/addNewCourseProcess", method = RequestMethod.POST)
    public String addNewCourse(@Valid @ModelAttribute("course") Course course, BindingResult br, @RequestParam("id") int id,
                               Model model) {

        if (br.hasErrors()) {
            model.addAttribute("course", course);
            model.addAttribute("classifications", classificationService.findAll());
            model.addAttribute("adminId", id);
            return "addNewCourse";
        }

        try {

            Admin admin = adminService.findById(id);
            model.addAttribute("admin", admin);
            Classification classification = classificationService.findByValue(course.getEmbeddableClassification());
            course.setAdmin(admin);
            course.setClassification(classification);
            courseService.addNewCourse(course);
            model.addAttribute("courses", courseService.showAllCourses(admin));
            return ("course");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") int id) {
        try {
            User user = userService.findById(id);
            model.addAttribute("user", user);
            return "editUser";
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String backToWelcome(@RequestParam("id") int id,
                                Model model) {
        try {
            Admin adminUser = adminService.findById(id);
            model.addAttribute("admin", adminUser);
            model.addAttribute("students", studentService.showAllStudent(adminUser));
            model.addAttribute("teachers", teacherService.showAllTeachers(adminUser));
            return ("welcome");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }

    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String showCourses(@RequestParam("id") int id, @RequestParam("number") int number, @RequestParam("status") int status,
                              Model model) {
        try {
            Admin adminUser = adminService.findById(id);
            model.addAttribute("adminId", id);
            model.addAttribute("courses", courseService.showAllCourses(adminUser));
            if (status != 0) {
                Course course = courseService.findByNumber(number);
                if (status == 1) {
                    model.addAttribute("students", studentService.showAllStudentsByCourse(course));
                } else if (status == 2) {
                    model.addAttribute("teachers", teacherService.showAllTeachersByCourse(course));
                } else if (status == 3) {
                    model.addAttribute("exams", examService.showAllByCourse(course));
                }
            }
            return ("course");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }

    }

    @RequestMapping(value = "/classifications", method = RequestMethod.GET)
    public String showClassifications(@RequestParam("id") int id,
                                      Model model) {
        model.addAttribute("adminId", id);
        model.addAttribute("classifications", classificationService.findAll());
        model.addAttribute("classification", new Classification());
        return ("classifications");
    }

    @RequestMapping(value = "/addClassificationsProcess", method = RequestMethod.POST)
    public String addClassifications(@RequestParam("id") int id, @Valid@ModelAttribute Classification classification, BindingResult br,Model model) {
        try {
            model.addAttribute("adminId", id);
            if(br.hasErrors()){
                model.addAttribute("classifications", classificationService.findAll());
                return ("classifications");
            }
            model.addAttribute("classification", new Classification());
            classification.setId(0);
            classificationService.addNewClassification(classification);
            model.addAttribute("classifications", classificationService.findAll());
            return ("classifications");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }

    }

    @RequestMapping(value = "/editClassificationsProcess", method = RequestMethod.GET)
    public String editClassifications(@RequestParam("id") int id, @RequestParam("number") int number, @RequestParam("value") String value, Model model) {
        try {
            model.addAttribute("adminId", id);
            classificationService.update(number,value);
            model.addAttribute("classifications", classificationService.findAll());
            model.addAttribute("classification", new Classification());
            return ("classifications");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }

    }

    @RequestMapping(value = "/addStudentOrTeacherToCourse", method = RequestMethod.GET)
    public String showAddNewPeopleToCourse(Model model, @RequestParam("id") int id,@RequestParam("number") int number) {
        try {
            model.addAttribute("adminId", id);
            Admin admin=adminService.findById(id);
            model.addAttribute("students",studentService.showAllStudent(admin));
            model.addAttribute("teachers",teacherService.showAllTeachers(admin));
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
        return ("addNewPeopleToCourse");
    }
}
