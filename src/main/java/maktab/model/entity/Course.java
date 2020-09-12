package maktab.model.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
@Entity
public class Course {

    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message="*number should be filled.only with digits")
    private int number;
    @Pattern(regexp = "^[a-zA-Z]+$" , message="*caption should be filled.only with a to z and At o Z")
    private String caption;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classification_id")
    private Classification classification;
    @Transient
    private String embeddableClassification;
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;
    @ManyToMany(mappedBy = "courses")
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "course")
    private List<Exam> exams;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @Pattern(regexp = "^[0-2]{1}[0-9]{3}/(0?[1-9]|1[0-2])/([0]?[1-9]|[1-2]?[1-9]|3?[0-1])$",message = "*please match the format:yyyy/MM/dd")
    private String theBeginning;
    @Pattern(regexp = "^[0-2]{1}[0-9]{3}/(0?[1-9]|1[0-2])/([0]?[1-9]|[1-2]?[1-9]|3?[0-1])$",message = "*please match the format:yyyy/MM/dd")
    private String theEnd;

    public String getEmbeddableClassification() {
        return embeddableClassification;
    }

    public void setEmbeddableClassification(String embeddableClassification) {
        this.embeddableClassification = embeddableClassification;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getTheBeginning() {
        return theBeginning;
    }

    public void setTheBeginning(String theBeginning) {
        this.theBeginning = theBeginning;
    }

    public String getTheEnd() {
        return theEnd;
    }

    public void setTheEnd(String theEnd) {
        this.theEnd = theEnd;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", number=" + number +
                ", caption='" + caption + '\'' +
                ", classification=" + classification +
                ", embeddableClassification='" + embeddableClassification + '\'' +
                ", students=" + students +
                ", teachers=" + teachers +
                ", exams=" + exams +
                ", admin=" + admin +
                ", theBeginning='" + theBeginning + '\'' +
                ", theEnd='" + theEnd + '\'' +
                '}';
    }
}
