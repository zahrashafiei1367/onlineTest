package maktab.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Classification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Pattern(regexp = "^[a-zA-Z]+$" , message="value should be filled.")
    String value;
    @OneToMany(mappedBy = "classification")
    List<Course> courses;
    public Classification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
