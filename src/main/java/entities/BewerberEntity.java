package entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "bewerber", schema = "informationssystem")
public class BewerberEntity {
    private Integer bewerberId;
    private String nachname;
    private String vorname;
    private String email;
    private String geburtsort;
    private String nationalitaet;
    private String adresse;
    private LocalDate geburtsdatum;
    private Integer studiengangId;
    private Integer matNr;
    private String sonstigeInformationen;
    private StudiengangEntity studiengangByStudiengangId;
    private Collection<ImmatrikulationsverfahrenStatusEntity> immatrikulationsverfahrenStatusesByBewerberId;
    private String wohnort;
    private Integer abiturnote;

    @Id
    @Column(name = "bewerber_id", nullable = false)
    public Integer getBewerberId() {
        return bewerberId;
    }

    public void setBewerberId(Integer bewerberId) {
        this.bewerberId = bewerberId;
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
    @Column(name = "vorname", nullable = false, length = 30)
    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    @Column(name = "nationalitaet", nullable = false, length = 30)
    public String getNationalitaet() {
        return nationalitaet;
    }

    public void setNationalitaet(String nationalitaet) {
        this.nationalitaet = nationalitaet;
    }

    @Basic
    @Column(name = "adresse", nullable = false, length = 30)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Basic
    @Column(name = "geburtsdatum", nullable = false)
    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    @Basic
    @Column(name = "studiengang_id", nullable = false, insertable=false, updatable=false)
    public Integer getStudiengangId() {
        return studiengangId;
    }

    public void setStudiengangId(Integer studiengangId) {
        this.studiengangId = studiengangId;
    }

    @Basic
    @Column(name = "mat_nr", nullable = true)
    public Integer getMatNr() {
        return matNr;
    }

    public void setMatNr(Integer matNr) {
        this.matNr = matNr;
    }

    @Basic
    @Column(name = "sonstige_informationen", nullable = true, length = 400)
    public String getSonstigeInformationen() {
        return sonstigeInformationen;
    }

    public void setSonstigeInformationen(String sonstigeInformationen) {
        this.sonstigeInformationen = sonstigeInformationen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BewerberEntity that = (BewerberEntity) o;
        return Objects.equals(bewerberId, that.bewerberId) &&
                Objects.equals(nachname, that.nachname) &&
                Objects.equals(vorname, that.vorname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(geburtsort, that.geburtsort) &&
                Objects.equals(nationalitaet, that.nationalitaet) &&
                Objects.equals(adresse, that.adresse) &&
                Objects.equals(geburtsdatum, that.geburtsdatum) &&
                Objects.equals(studiengangId, that.studiengangId) &&
                Objects.equals(matNr, that.matNr) &&
                Objects.equals(sonstigeInformationen, that.sonstigeInformationen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bewerberId, nachname, vorname, email, geburtsort, nationalitaet, adresse, geburtsdatum, studiengangId, matNr, sonstigeInformationen);
    }

    @ManyToOne
    @JoinColumn(name = "studiengang_id", referencedColumnName = "studiengang_id", nullable = false)
    public StudiengangEntity getStudiengangByStudiengangId() {
        return studiengangByStudiengangId;
    }

    public void setStudiengangByStudiengangId(StudiengangEntity studiengangByStudiengangId) {
        this.studiengangByStudiengangId = studiengangByStudiengangId;
    }

    @OneToMany
    @JoinColumn(name = "bewerber_id", referencedColumnName = "bewerber_id", nullable = false)
    public Collection<ImmatrikulationsverfahrenStatusEntity> getImmatrikulationsverfahrenStatusesByBewerberId() {
        return immatrikulationsverfahrenStatusesByBewerberId;
    }

    public void setImmatrikulationsverfahrenStatusesByBewerberId(Collection<ImmatrikulationsverfahrenStatusEntity> immatrikulationsverfahrenStatusesByBewerberId) {
        this.immatrikulationsverfahrenStatusesByBewerberId = immatrikulationsverfahrenStatusesByBewerberId;
    }

    @Basic
    @Column(name = "wohnort", nullable = false, length = 30)
    public String getWohnort() {
        return wohnort;
    }

    public void setWohnort(String wohnort) {
        this.wohnort = wohnort;
    }

    @Basic
    @Column(name = "abiturnote", nullable = false)
    public Integer getAbiturnote() {
        return abiturnote;
    }

    public void setAbiturnote(Integer abiturnote) {
        this.abiturnote = abiturnote;
    }
}
