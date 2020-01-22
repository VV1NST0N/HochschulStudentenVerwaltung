package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_studiengang", schema = "informationssystem")
public class StudentStudiengangEntity {
    private int studentStudiengangId;

    private StudentEntity studentEntities;
    private StudiengangEntity studiengangEntities;
    private Boolean aktivesStudium;
    private int semeser;

    @Id
    @Column(name = "student_studiengang_id")
    public int getStudentStudiengangId() {
        return studentStudiengangId;
    }

    public void setStudentStudiengangId(int studentStudiengangId) {
        this.studentStudiengangId = studentStudiengangId;
    }


    @Basic
    @Column(name = "aktives_studium")
    public Boolean getAktivesStudium() {
        return aktivesStudium;
    }

    public void setAktivesStudium(Boolean aktivesStudium) {
        this.aktivesStudium = aktivesStudium;
    }

    @Basic
    @Column(name = "semeser")
    public int getSemeser() {
        return semeser;
    }

    public void setSemeser(int semeser) {
        this.semeser = semeser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentStudiengangEntity that = (StudentStudiengangEntity) o;
        return studentStudiengangId == that.studentStudiengangId &&
                aktivesStudium == that.aktivesStudium &&
                semeser == that.semeser;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentStudiengangId, aktivesStudium, semeser);
    }


    @ManyToOne(optional = false)
    @JoinColumn(name = "studiengang_id", referencedColumnName = "studiengang_id", nullable = false)
    public StudiengangEntity getStudiengangEntities() {
        return studiengangEntities;
    }

    public void setStudiengangEntities(StudiengangEntity studiengangEntities) {
        this.studiengangEntities = studiengangEntities;
    }


    @ManyToOne(optional = false)
    @JoinColumn(name = "mat_nr", referencedColumnName = "mat_nr", nullable = false)
    public StudentEntity getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(StudentEntity studentEntities) {
        this.studentEntities = studentEntities;
    }
}
