package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_studiengang", schema = "informationssystem", catalog = "")
@IdClass(StudentStudiengangEntityPK.class)
public class StudentStudiengangEntity {
    private Integer matNr;
    private Integer studiengangId;
    private Boolean aktivesStudium;
    private StudentEntity studentByMatNr;
    private StudiengangEntity studiengangByStudiengangId;
    private Integer semeser;

    @Id
    @Column(name = "mat_nr", nullable = false)
    public Integer getMatNr() {
        return matNr;
    }

    public void setMatNr(Integer matNr) {
        this.matNr = matNr;
    }

    @Id
    @Column(name = "studiengang_id", nullable = false)
    public Integer getStudiengangId() {
        return studiengangId;
    }

    public void setStudiengangId(Integer studiengangId) {
        this.studiengangId = studiengangId;
    }

    @Basic
    @Column(name = "aktives_studium", nullable = false)
    public Boolean getAktivesStudium() {
        return aktivesStudium;
    }

    public void setAktivesStudium(Boolean aktivesStudium) {
        this.aktivesStudium = aktivesStudium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentStudiengangEntity that = (StudentStudiengangEntity) o;
        return Objects.equals(matNr, that.matNr) &&
                Objects.equals(studiengangId, that.studiengangId) &&
                Objects.equals(aktivesStudium, that.aktivesStudium);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matNr, studiengangId, aktivesStudium);
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "mat_nr", referencedColumnName = "mat_nr")
    @JoinColumn(name = "mat_nr", referencedColumnName = "mat_nr", nullable = false)
    public StudentEntity getStudentByMatNr() {
        return studentByMatNr;
    }

    public void setStudentByMatNr(StudentEntity studentByMatNr) {
        this.studentByMatNr = studentByMatNr;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "studiengang_id", referencedColumnName = "studiengang_id")
    @JoinColumn(name = "studiengang_id", referencedColumnName = "studiengang_id", nullable = false)
    public StudiengangEntity getStudiengangByStudiengangId() {
        return studiengangByStudiengangId;
    }

    public void setStudiengangByStudiengangId(StudiengangEntity studiengangByStudiengangId) {
        this.studiengangByStudiengangId = studiengangByStudiengangId;
    }

    @Basic
    @Column(name = "semeser", nullable = false)
    public Integer getSemeser() {
        return semeser;
    }

    public void setSemeser(Integer semeser) {
        this.semeser = semeser;
    }
}
