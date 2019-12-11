package entities;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "studiengang", schema = "informationssystem")
public class StudiengangEntity {
    private Integer studiengangId;
    private String studiengangName;
    private Integer numerusClaususNote;
    private Integer studiengangPlatzzahl;
    private Integer studiengangFreiePlaetze;
    private Boolean vorraussetzungTest;
    private String beschreibungVoraussetzung;
    private Collection<BewerberEntity> bewerbersByStudiengangId;
    private Collection<StudentStudiengangEntity> studentStudiengangsByStudiengangId;
    private LocalDate zulassungszeitraum;

    @Id
    @Column(name = "studiengang_id", nullable = false)
    public Integer getStudiengangId() {
        return studiengangId;
    }

    public void setStudiengangId(Integer studiengangId) {
        this.studiengangId = studiengangId;
    }

    @Basic
    @Column(name = "studiengang_name", nullable = true, length = 30)
    public String getStudiengangName() {
        return studiengangName;
    }

    public void setStudiengangName(String studiengangName) {
        this.studiengangName = studiengangName;
    }

    @Basic
    @Column(name = "numerus_clausus_note", nullable = true)
    public Integer getNumerusClaususNote() {
        return numerusClaususNote;
    }

    public void setNumerusClaususNote(Integer numerusClaususNote) {
        this.numerusClaususNote = numerusClaususNote;
    }

    @Basic
    @Column(name = "studiengang_platzzahl", nullable = false)
    public Integer getStudiengangPlatzzahl() {
        return studiengangPlatzzahl;
    }

    public void setStudiengangPlatzzahl(Integer studiengangPlatzzahl) {
        this.studiengangPlatzzahl = studiengangPlatzzahl;
    }

    @Basic
    @Column(name = "studiengang_freie_plaetze", nullable = false)
    public Integer getStudiengangFreiePlaetze() {
        return studiengangFreiePlaetze;
    }

    public void setStudiengangFreiePlaetze(Integer studiengangFreiePlaetze) {
        this.studiengangFreiePlaetze = studiengangFreiePlaetze;
    }

    @Basic
    @Column(name = "vorraussetzung_test", nullable = false)
    public Boolean getVorraussetzungTest() {
        return vorraussetzungTest;
    }

    public void setVorraussetzungTest(Boolean vorraussetzungTest) {
        this.vorraussetzungTest = vorraussetzungTest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudiengangEntity that = (StudiengangEntity) o;
        return Objects.equals(studiengangId, that.studiengangId) &&
                Objects.equals(studiengangName, that.studiengangName) &&
                Objects.equals(numerusClaususNote, that.numerusClaususNote) &&
                Objects.equals(studiengangPlatzzahl, that.studiengangPlatzzahl) &&
                Objects.equals(studiengangFreiePlaetze, that.studiengangFreiePlaetze) &&
                Objects.equals(vorraussetzungTest, that.vorraussetzungTest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studiengangId, studiengangName, numerusClaususNote, studiengangPlatzzahl, studiengangFreiePlaetze, vorraussetzungTest);
    }

    @Basic
    @Column(name = "beschreibung_voraussetzung", nullable = true, length = 120)
    public String getBeschreibungVoraussetzung() {
        return beschreibungVoraussetzung;
    }

    public void setBeschreibungVoraussetzung(String beschreibungVoraussetzung) {
        this.beschreibungVoraussetzung = beschreibungVoraussetzung;
    }

    @OneToMany(mappedBy = "studiengangByStudiengangId")
    public Collection<BewerberEntity> getBewerbersByStudiengangId() {
        return bewerbersByStudiengangId;
    }

    public void setBewerbersByStudiengangId(Collection<BewerberEntity> bewerbersByStudiengangId) {
        this.bewerbersByStudiengangId = bewerbersByStudiengangId;
    }

    @OneToMany(mappedBy = "studiengangByStudiengangId")
    public Collection<StudentStudiengangEntity> getStudentStudiengangsByStudiengangId() {
        return studentStudiengangsByStudiengangId;
    }

    public void setStudentStudiengangsByStudiengangId(Collection<StudentStudiengangEntity> studentStudiengangsByStudiengangId) {
        this.studentStudiengangsByStudiengangId = studentStudiengangsByStudiengangId;
    }

    @Basic
    @Column(name = "zulassungszeitraum", nullable = false)
    public LocalDate getZulassungszeitraum() {
        return zulassungszeitraum;
    }

    public void setZulassungszeitraum(LocalDate zulassungszeitraum) {
        this.zulassungszeitraum = zulassungszeitraum;
    }
}