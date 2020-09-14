package maktab.model.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course1 = (Course) o;
        return getId() == course1.getId() &&
                getNumber() == course1.getNumber() &&
                getTitle().equals(course1.getTitle()) &&
                getCaption().equals(course1.getCaption()) &&
                getTheBeginning().equals(course1.getTheBeginning()) &&
                getTheEnd().equals(course1.getTheEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getId(), getNumber(), getCaption(), getAdmin(), getTheBeginning(), getTheEnd());
    }


}
