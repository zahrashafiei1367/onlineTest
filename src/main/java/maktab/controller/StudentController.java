package maktab.controller;

import maktab.model.entity.Question;
import maktab.model.entity.Result;
import maktab.model.entity.Student;
import maktab.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller
public class StudentController {
//    public StudentService studentService;
//
//    @Autowired
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public String showRegister(Model model) {
//        model.addAttribute("student", new Student());
//        return "register";
//    }
//
//    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
//    public String addUser(@ModelAttribute("student") Student student,
//                          HttpServletRequest request,
//                          Model model,
//                          RedirectAttributes redirectAttributes) {
//        try {
//            studentService.registerNewStudent(student);
//            model.addAttribute("firstName", student.getName());
//            return "welcome";
//        } catch (Exception e) {
//            /*request.setAttribute("errMsg", e.getMessage());
//            return "forward:error1";
//            */
//
//            //return new ModelAndView("error", "errorMsg", e.getMessage());
//
//            redirectAttributes.addAttribute("errMsg", e.getMessage());
//            redirectAttributes.addAttribute("errorAdditinalDescription", e.getMessage());
//            return "redirect:/error2";
//        }
//    }
//
//    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
//    public String showSignIn(Model model) {
//        model.addAttribute("student", new Student());
//        return "signIn";
//    }
//
//    @RequestMapping(value = "/signInProcess", method = RequestMethod.POST)
//    public String signIn(@ModelAttribute("student") Student student,
//                          HttpServletRequest request,
//                          Model model,
//                          RedirectAttributes redirectAttributes) {
//        try {
//           // studentService.findByStudentName(student);
//            model.addAttribute("firstName", student.getName());
//            return "welcome";
//        } catch (Exception e) {
//            /*request.setAttribute("errMsg", e.getMessage());
//            return "forward:error1";
//            */
//
//            //return new ModelAndView("error", "errorMsg", e.getMessage());
//
//            redirectAttributes.addAttribute("errMsg", e.getMessage());
//            redirectAttributes.addAttribute("student name or family or number is incorrect", e.getMessage());
//            return "redirect:/error2";
//        }
//    }
//
//    @RequestMapping(value = "/error1", method = RequestMethod.POST)
//    public String showError1(Model model, HttpServletRequest request) {
//        model.addAttribute("errorMsg", request.getAttribute("errMsg"));
//        return "error";
//    }
//
//    @RequestMapping(value = "/error2", method = RequestMethod.GET)
//    public String showError2(Model model, @RequestParam("errMsg") String errorMessage) {
//        model.addAttribute("errorMsg", errorMessage);
//        return "error";
//    }
//
//    @RequestMapping(value = "/show_all", method = RequestMethod.GET)
//    public ModelAndView getAllStudents(HttpServletRequest request, HttpServletResponse response) {
//        List<Student> students = studentService.showAllStudent();
//        ModelAndView mav = new ModelAndView("allStudent");
//        mav.addObject("studentList", students);
//        return mav;
//    }
//
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String showSearchPage(Model model) {
//        model.addAttribute("student", new Student());
//        return "search";
//    }
//
//    @RequestMapping(value = "/searchProcess", method = RequestMethod.POST)
//    public ModelAndView searchStudent(@ModelAttribute("student") Student student) {
//        Page<Student> students = studentService.findMaxMatch(student.getName(), student.getFamily(),
//                 0, 100);
//        return new ModelAndView("search", "studentList", students.getContent());
//    }
//    @RequestMapping(value = "/beginTest", method = RequestMethod.POST)
//    public String beginTest(@ModelAttribute("student") Student student,
//                         HttpServletRequest request,
//                         Model model,
//                         RedirectAttributes redirectAttributes) {
//            Result result=new Result();
//            Question question=new Question();
////            result.setCorrectAnswer(0);
////            question.setQuestion("2+3=");
////            question.setAnswers(Arrays.asList(new String[]{"1", "6", "5", "3"}));
//            model.addAttribute("result",result);
//            model.addAttribute("question", question);
//            model.addAttribute("questionCounter", 1);
//            return "begineTest";
//    }
//    @RequestMapping(value = "/testProcess", method = RequestMethod.POST)
//    public String test(@ModelAttribute("student") Student student
//                         ,@ModelAttribute("result") Result result
//                         ,@ModelAttribute("question") Question question
//                         ,@ModelAttribute("questionCounter") Integer questionCounter,
//                         HttpServletRequest request,
//                         Model model,
//                         RedirectAttributes redirectAttributes) {
//        try {
//
//
//            return "begineTest";
//        } catch (Exception e) {
//            /*request.setAttribute("errMsg", e.getMessage());
//            return "forward:error1";
//            */
//
//            //return new ModelAndView("error", "errorMsg", e.getMessage());
//
//            redirectAttributes.addAttribute("errMsg", e.getMessage());
//            redirectAttributes.addAttribute("student name or family or number is incorrect", e.getMessage());
//            return "redirect:/error2";
//        }
//    }


}