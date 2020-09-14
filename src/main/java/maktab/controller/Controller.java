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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    @Autowired
    private QuestionService questionService;
    private List<String> answers = new ArrayList<>();

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
                if (!teacher.getEnabled()) {
                    model.addAttribute("errorMsg", "your account has not verified yet.");
                    return "error";
                }
                model.addAttribute("id", teacher.getId());
                model.addAttribute("firstName", teacher.getName());
                List<Course> courses = teacher.getCourses();
                model.addAttribute("courses", courses);
                return ("welcomeTeacher");

            } else if (user.getAuthority().equals("student")) {
                Student student = studentService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
                if (!student.getEnabled()) {
                    model.addAttribute("errorMsg", "your account has not verified yet.");
                    return "error";
                }
                model.addAttribute("id", student.getId());
                model.addAttribute("firstName", student.getName());

                return "welcomeStudent";
            } else {
                model.addAttribute("errorMsg", "please choose student or teacher");
                return "error";
            }
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
                model.addAttribute("errorMsg", "your account has not verified yet.");
                return "error";
            } else {
                model.addAttribute("errorMsg", "your account has not verified yet.");
                return "error";
            }

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
        try {
            model.addAttribute("errorMsg", "");
            if (br.hasErrors()) {
                model.addAttribute("course", course);
                model.addAttribute("classifications", classificationService.findAll());
                model.addAttribute("adminId", id);
                return "addNewCourse";
            }

            if (!compareDates(course.getTheBeginning(), course.getTheEnd())) {
                model.addAttribute("course", course);
                model.addAttribute("classifications", classificationService.findAll());
                model.addAttribute("adminId", id);
                model.addAttribute("errorMsg", "the date of starting the class should be before ending.");
                return "addNewCourse";
            }

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

    private boolean compareDates(String startDate, String endDate) {

        try {
            Date start = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
                    .parse(startDate);
            Date end = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
                    .parse(endDate);

            if (start.compareTo(end) > 0) {
                return false;
            } else if (start.compareTo(end) < 0) {
                return true;
            } else if (start.compareTo(end) == 0) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
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
                    model.addAttribute("students", studentService.showAllStudentsHasCourse(adminUser, course));
                } else if (status == 2) {
                    model.addAttribute("teachers", teacherService.showAllTeachersHasCourse(adminUser, course));
                } else if (status == 3) {
                    model.addAttribute("exams", examService.showAllCourseExams(course));
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
        try {
            model.addAttribute("adminId", id);
            model.addAttribute("classifications", classificationService.findAll());
            model.addAttribute("classification", new Classification());
            return ("classifications");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/addClassificationsProcess", method = RequestMethod.POST)
    public String addClassifications(@RequestParam("id") int id, @Valid @ModelAttribute Classification classification, BindingResult br, Model model) {
        try {
            model.addAttribute("adminId", id);
            if (br.hasErrors()) {
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


    @RequestMapping(value = "/addStudentOrTeacherToCourse", method = RequestMethod.GET)
    public String showAddNewPeopleToCourse(Model model, @RequestParam("id") int id, @RequestParam("number") int number) {
        try {
            Course course = courseService.findByNumber(number);
            model.addAttribute("courseNumber", number);
            model.addAttribute("adminId", id);
            Admin admin = adminService.findById(id);
            List<Student> students = showAllStudentsIfHasCourseMakeHasCourseTrue(admin, course);
            model.addAttribute("students", students);
            List<Teacher> teachers = showAllTeachersIfHasCourseMakeHasCourseTrue(admin, course);
            model.addAttribute("teachers", teachers);
            return ("addNewPeopleToCourse");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }

    }

    private List<Teacher> showAllTeachersIfHasCourseMakeHasCourseTrue(Admin admin, Course course) {
        List<Teacher> teachers = teacherService.showAllTeachers(admin);
        for (Teacher teacher : teachers) {
            if (teacher.getCourses().contains(course)) {
                teacher.setHasCourse(true);
            } else teacher.setHasCourse(false);
        }
        return teachers;
    }

    private List<Student> showAllStudentsIfHasCourseMakeHasCourseTrue(Admin admin, Course course) {
        List<Student> students = studentService.showAllStudent(admin);
        for (Student student : students) {
            if (student.getCourses().contains(course))
                student.setHasCourse(true);
            else student.setHasCourse(false);
        }
        return students;
    }

    @RequestMapping(value = "/changeSt", method = RequestMethod.GET)
    public String changeStatusOfStudentCourse(Model model, @RequestParam("id") int userId,
                                              @RequestParam("adminId") int adminId, @RequestParam("courseNumber") int courseNumber) {
        try {
            model.addAttribute("adminId", adminId);
            Course course = courseService.findByNumber(courseNumber);
            model.addAttribute("courseNumber", courseNumber);
            Admin admin = adminService.findById(adminId);
            User user = userService.findById(userId);
            if ((user instanceof Student))
                studentService.addOrRemoveCourse(userId, course);
            else if (user instanceof Teacher)
                teacherService.addOrRemoveCourse(userId, course);
            else
                throw new Exception("usernot student oe teacher");
            List<Student> students = showAllStudentsIfHasCourseMakeHasCourseTrue(admin, course);
            List<Teacher> teachers = showAllTeachersIfHasCourseMakeHasCourseTrue(admin, course);
            model.addAttribute("students", students);
            model.addAttribute("teachers", teachers);
            return ("addNewPeopleToCourse");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }

    }

    @RequestMapping(value = "/exams", method = RequestMethod.GET)
    public String showCourseExams(Model model, @RequestParam("id") int teacherId, @RequestParam("courseId") int courseNumber) {
        try {
            Teacher teacher = teacherService.findById(teacherId);
            List<Course> courses = teacher.getCourses();
            Course course = courseService.findByNumber(courseNumber);
            List<Exam> exams = examService.showAllCourseExams(course);
            model.addAttribute("exams", exams);
            model.addAttribute("id", teacherId);
            model.addAttribute("courseNumber", courseNumber);
            model.addAttribute("courses", courses);
            return ("welcomeTeacher");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String showCourseStudents(Model model, @RequestParam("id") int teacherId, @RequestParam("courseId") int courseNumber) {
        try {
            Teacher teacher = teacherService.findById(teacherId);
            List<Course> courses = teacher.getCourses();
            Course course = courseService.findByNumber(courseNumber);
            List<Student> students = studentService.showAllStudentsHasCourse(teacher.getAdmin(), course);
            model.addAttribute("students", students);
            model.addAttribute("id", teacherId);
            model.addAttribute("courseNumber", courseNumber);
            model.addAttribute("courses", courses);
            return ("welcomeTeacher");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String showCourseTeachers(Model model, @RequestParam("id") int teacherId, @RequestParam("courseId") int courseNumber) {
        try {
            Teacher teacher = teacherService.findById(teacherId);
            List<Course> courses = teacher.getCourses();
            Course course = courseService.findByNumber(courseNumber);
            List<Teacher> teachers = teacherService.showAllTeachersHasCourse(teacher.getAdmin(), course);
            model.addAttribute("students", teachers);
            model.addAttribute("id", teacherId);
            model.addAttribute("courseNumber", courseNumber);
            model.addAttribute("courses", courses);
            return ("welcomeTeacher");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/backTWelcome", method = RequestMethod.GET)
    public String showwelcomeTeacher(Model model, @RequestParam("id") int teacherId) {
        try {
            Teacher teacher = teacherService.findById(teacherId);
            List<Course> courses = teacher.getCourses();
            model.addAttribute("id", teacherId);
            model.addAttribute("courses", courses);
            return ("welcomeTeacher");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/myExams", method = RequestMethod.GET)
    public String showCourseExams(Model model, @RequestParam("id") int teacherId) {
        try {
            Teacher teacher = teacherService.findById(teacherId);
            List<Exam> exams = examService.showAllTeacherExams(teacher);
            model.addAttribute("exams", exams);
            model.addAttribute("id", teacherId);
            return ("myExams");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }


    @RequestMapping(value = "/addANewExam", method = RequestMethod.GET)
    public String showAddNewExam(Model model, @RequestParam("id") int teacherId, @RequestParam("courseId") int courseId) {
        try {
            Course course = courseService.findByNumber(courseId);
            Exam exam = new Exam();
            model.addAttribute("course", course);
            model.addAttribute("exam", exam);
            model.addAttribute("id", teacherId);
            return ("addNewExam");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/addNewExamProcess", method = RequestMethod.POST)
    public String addNewExam(@Valid @ModelAttribute("exam") Exam exam, BindingResult br, @RequestParam("id") int id,
                             @RequestParam("courseNumber") int courseNumber, Model model) {
        try {
            model.addAttribute("errorMsg", "");
            Course course = courseService.findByNumber(courseNumber);
            if (br.hasErrors()) {
                model.addAttribute("course", course);
                model.addAttribute("exam", exam);
                model.addAttribute("id", id);
                return ("addNewExam");
            }

            if (!compareDates(exam.getTheBeginning(), exam.getTheEnd())) {
                model.addAttribute("course", course);
                model.addAttribute("exam", exam);
                model.addAttribute("id", id);
                model.addAttribute("errorMsg", "the date of starting the exam should be before ending.");
                return "addNewCourse";
            }

            Teacher teacher = teacherService.findById(id);
            exam.setTeacher(teacher);
            exam.setCourse(course);
            examService.addNewExam(exam);
            List<Exam> exams = examService.showAllTeacherExams(teacher);
            model.addAttribute("exams", exams);
            model.addAttribute("id", id);
            return ("myExams");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }
    }

    @RequestMapping(value = "/examQuestion", method = RequestMethod.GET)
    public String showExamQuestions(Model model, @RequestParam("id") int teacherId, @RequestParam("examId") int examId) {
        try {
            Exam exam = examService.findById(examId);
            Teacher teacher = teacherService.findById(teacherId);
            List<Exam> exams = examService.showAllTeacherExams(teacher);
            List<Question> questions = questionService.findByExam(exam);
            model.addAttribute("questions", questions);
            model.addAttribute("exams", exams);
            model.addAttribute("id", teacherId);
            return ("myExams");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/addAQuestionChoose", method = RequestMethod.GET)
    public String showAddNewQuestionChoose(Model model, @RequestParam("id") int teacherId, @RequestParam("examId") int examId) {
        model.addAttribute("id", teacherId);
        model.addAttribute("examId", examId);
        return "choose";
    }

    @RequestMapping(value = "/addAnswer", method = RequestMethod.GET)
    public String addNewAnswerProcess(Model model, @RequestParam("id") int teacherId, @RequestParam("examId") int examId, @RequestParam("ans") String answer) {

        answers.add(answer);
        Question question = new Question();
        question.setQuestionType(QuestionType.TEST);
        model.addAttribute("question", question);
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("examId", examId);
        model.addAttribute("question", question);
        model.addAttribute("classifications", classificationService.findAll());
        return "addNewQuestion";
    }

    @RequestMapping(value = "/addAQuestion", method = RequestMethod.GET)
    public String showAddNewQuestion(Model model, @RequestParam("id") int teacherId, @RequestParam("examId") int examId, @RequestParam("qt") String qt) {
        try {
            Question question = new Question();
            if (qt.equals("ex"))
                question.setQuestionType(QuestionType.DESCRIPTIVE);
            else {
                question.setQuestionType(QuestionType.TEST);
                question.setAnswers(answers);
            }
            model.addAttribute("teacherId", teacherId);
            model.addAttribute("examId", examId);
            model.addAttribute("question", question);
            model.addAttribute("classifications", classificationService.findAll());
            return ("addNewQuestion");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/addNewQuestionProcess", method = RequestMethod.POST)
    public String addNewExam(@Valid @ModelAttribute("question") Question question, BindingResult br, @RequestParam("id") int teacherId,
                             @RequestParam("examId") int examId, Model model) {
        try {
            if (br.hasErrors()) {
                model.addAttribute("question", question);
                model.addAttribute("teacherId", teacherId);
                model.addAttribute("examId", examId);
                model.addAttribute("classifications", classificationService.findAll());
                return ("addNewQuestion");
            }
            Teacher teacher = teacherService.findById(teacherId);
            Exam exam = examService.findById(examId);
            question.setExam(exam);
            Classification classification = classificationService.findByValue(question.getEmbCl());
            question.setClassification(classification);
            question.setTeacher(teacher);
            questionService.addAQuestion(question);
            List<Exam> exams = examService.showAllTeacherExams(teacher);
            question.setEmbCl("expm");
            model.addAttribute("exams", exams);
            model.addAttribute("id", teacherId);
            return ("myExams");
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return ("error");
        }
    }
//    Teacher teacher=teacherService.findById(teacherId);
//    Course course = courseService.findByNumber(courseId);
//    Exam exam=examService.findById(examId);
    //examStudent?id=${id}&examId=${crs.id}
    //examResult?id=${id}&examId=${crs.id}
    //examEdit?id=${id}&examId=${crs.id}
    //examDelete?id=${id}&courseId=${crs.id}
}
