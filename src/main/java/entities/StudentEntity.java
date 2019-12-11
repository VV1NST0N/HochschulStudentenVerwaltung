package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "student", schema = "informationssystem", catalog = "")
public class StudentEntity {
    private Integer matNr;
    private String vorname;
    private String nachname;
    private String adresse;
    private Date geburtsdatum;
    private String geburtsort;
    private String email;
    private Collection<StudentStudiengangEntity> studentStudiengangsByMatNr;
    private String wohnort;

    @Id
    @Column(name = "mat_nr", nullable = false)
    public Integer getMatNr() {
        return matNr;
    }

    public void setMatNr(Integer matNr) {
        this.matNr = matNr;
    }

    @Basic
    @Column(name = "vorname", nullable = false, length = 30)
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Basic
    @Column(name = "nachname", nullable = false, length = 30)
    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Basic
    @Column(name = "adresse", nullable = false, length = 40)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "geburtsdatum", nullable = false)
    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    @Basic
    @Column(name = "geburtsort", nullable = false, length = 30)
    public String getGeburtsort() {
        return geburtsort;
    }

    public void setGeburtsort(String geburtsort) {
        this.geburtsort = geburtsort;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(matNr, that.matNr) &&
                Objects.equals(vorname, that.vorname) &&
                Objects.equals(nachname, that.nachname) &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(geburtsdatum, that.geburtsdatum) &&
                Objects.equals(geburtsort, that.geburtsort) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matNr, vorname, nachname, adresse, geburtsdatum, geburtsort, email);
    }

    @OneToMany(mappedBy = "studentByMatNr")
    public Collection<StudentStudiengangEntity> getStudentStudiengangsByMatNr() {
        return studentStudiengangsByMatNr;
    }

    public void setStudentStudiengangsByMatNr(Collection<StudentStudiengangEntity> studentStudiengangsByMatNr) {
        this.studentStudiengangsByMatNr = studentStudiengangsByMatNr;
    }

    @Basic
    @Column(name = "wohnort", nullable = false, length = 30)
    public String getWohnort() {
        return wohnort;
    }

    public void setWohnort(String wohnort) {
        this.wohnort = wohnort;
    }
}
