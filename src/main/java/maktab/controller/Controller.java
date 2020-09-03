package maktab.controller;

import maktab.model.entity.*;
import maktab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Controller() {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage() {
        return "home";
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    public String showSignInUser(Model model) {
        model.addAttribute("user", new User());
        return "signIn";
    }

    @RequestMapping(value = "/editByUsername", method = RequestMethod.GET)
    public String showEditUserPage(Model model) {
        model.addAttribute("user", new User());

        return ("editUser");
    }

    @RequestMapping(value = "/editProcess?", method = RequestMethod.GET)
    public String edit(@ModelAttribute("oldUser") User oldUser, @ModelAttribute("user") User user,
                       Model model) {
        String username = oldUser.getUsername();
        if (username.equals("")) {
            model.addAttribute("errorMsg", "username should be filled.");
            return ("error");
        }


        try {
            userService.editByUsername(user, username);
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }
        return ("welcome");


    }

    @RequestMapping(value = "/signInProcess", method = RequestMethod.POST)
    public String signInUser(@ModelAttribute("user") User user,
                             Model model) {
        try {
            //Page<Student> students = studentService.showAllStudent(0,2);

            Admin admin = adminService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            model.addAttribute("students", studentService.showAllStudent(admin));
            model.addAttribute("teachers", teacherService.showAllTeachers(admin));
            model.addAttribute("admin",admin);
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
    public String showAddNewStudent(Model model) {
        model.addAttribute("student", new Student());
        return ("addNewStudent");
    }

    @RequestMapping(value = "/addNewStudentProcess", method = RequestMethod.POST)
    public String addNewStudent(@ModelAttribute("student") Student student,
                                Model model) {
        try {
            studentService.registerNewStudent(student);
            return ("welcome");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }

    }

    @RequestMapping(value = "/addNewTeacher", method = RequestMethod.GET)
    public String showAddNewTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return ("addNewTeacher");
    }

    @RequestMapping(value = "/addNewTeacherProcess", method = RequestMethod.POST)
    public String addNewTeacher(@ModelAttribute("teacher") Teacher teacher,
                                Model model) {
        try {
            teacher.setEnabled(true);
            teacherService.registerNewTeacher(teacher);
            Admin admin = (Admin) model.getAttribute("admin");
            model.addAttribute("students", studentService.showAllStudent(admin));
            model.addAttribute("teachers", teacherService.showAllTeachers(admin));
            return ("welcome");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }

    }

    @RequestMapping(value = "/deleteByUsername/{input}", method = RequestMethod.GET)
    public String deleteByUsername(Model model, @PathVariable String input) {
        try {
            int index=input.indexOf('%');
            int id=Integer.parseInt(input.substring(0,index));
            int id2=Integer.parseInt(input.substring(index+1));
            Admin admin=adminService.findById(id2);
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
    public String search(Model model) {
        model.addAttribute("user", new User());
        return ("search");
    }


    @RequestMapping(value = "/searchProcess", method = RequestMethod.POST)
    public String search(@ModelAttribute("user") User user,
                         Model model) {
        if (user.getAuthority().equals("student")) {
            List<Student> students = studentService.findMaxMatch(user.getName(), user.getFamily(), user.getUsername());
            model.addAttribute("students", students);
            return ("studentsResultSearch");
        } else if (user.getAuthority().equals("teacher")) {
            List<Teacher> teachers = teacherService.findMaxMatch(user.getName(), user.getFamily(), user.getUsername());
            model.addAttribute("teachers", teachers);
            return ("teacherResultSearch");
        } else {
            List<Student> students = studentService.findMaxMatch(user.getName(), user.getFamily(), user.getUsername());
            List<Teacher> teachers = teacherService.findMaxMatch(user.getName(), user.getFamily(), user.getUsername());
            model.addAttribute("students", students);
            model.addAttribute("teachers", teachers);
            return ("usersResultSearch");
        }
    }


    @RequestMapping(value = "/addNewCourse", method = RequestMethod.GET)
    public String showAddNewCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("classifications", classificationService.findAll());
        return ("addNewCourse");
    }

    @RequestMapping(value = "/addNewCourseProcess", method = RequestMethod.POST)
    public String addNewCourse(@ModelAttribute("course") Course course,
                               Model model) {
        String ermsg = "";
        if (course.getNumber() == 0) {
            ermsg = "number should be filled.";
        } else if (Objects.equals("", course.getTitle())) {
            ermsg = "title should be filled";
        } else {
            try {
                Classification classification = new Classification();
                classification.setValue(course.getEmbeddableClassification());
                classificationService.addNewClassification(classification);
                course.setClassification(classificationService.findByValue(course.getEmbeddableClassification()));
                courseService.addNewCourse(course);
                Admin admin = (Admin) model.getAttribute("admin");
                model.addAttribute("students", studentService.showAllStudent(admin));
                model.addAttribute("teachers", teacherService.showAllTeachers(admin));
                return ("welcome");
            } catch (Exception e) {
                model.addAttribute("errorMsg", e.getMessage());
                return ("error");
            }
        }
        model.addAttribute("errorMsg", ermsg);
        return ("error");
    }
}
