package entities;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "immatrikulationsverfahren_status", schema = "informationssystem")
@IdClass(ImmatrikulationsverfahrenStatusEntityPK.class)
public class ImmatrikulationsverfahrenStatusEntity {
    private Boolean unterlagenVollstaendig;
    private Boolean zahlungStatus;
    private String statusInformationen;
    private Boolean zulassungStatus;
    private Integer bewerberId;
    private Integer unterlagenId;
    private BewerberEntity bewerberByBewerberId;
    private BewerbungsunterlagenEntity bewerbungsunterlagenByUnterlagenId;
    private LocalDate bewerbungseingang;

    @Basic
    @Column(name = "unterlagen_vollstaendig", nullable = false)
    public Boolean getUnterlagenVollstaendig() {
        return unterlagenVollstaendig;
    }

    public void setUnterlagenVollstaendig(Boolean unterlagenVollstaendig) {
        this.unterlagenVollstaendig = unterlagenVollstaendig;
    }

    @Basic
    @Column(name = "zahlung_status", nullable = false)
    public Boolean getZahlungStatus() {
        return zahlungStatus;
    }

    public void setZahlungStatus(Boolean zahlungStatus) {
        this.zahlungStatus = zahlungStatus;
    }

    @Basic
    @Column(name = "status_informationen", nullable = true, length = 100)
    public String getStatusInformationen() {
        return statusInformationen;
    }

    public void setStatusInformationen(String statusInformationen) {
        this.statusInformationen = statusInformationen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmatrikulationsverfahrenStatusEntity that = (ImmatrikulationsverfahrenStatusEntity) o;
        return  Objects.equals(unterlagenVollstaendig, that.unterlagenVollstaendig) &&
                Objects.equals(zahlungStatus, that.zahlungStatus) &&
                Objects.equals(statusInformationen, that.statusInformationen);
    }

    @Override
    public int hashCode() {
        return Objects.hash( unterlagenVollstaendig, zahlungStatus, statusInformationen);
    }

    @Basic
    @Column(name = "zulassung_status", nullable = false)
    public Boolean getZulassungStatus() {
        return zulassungStatus;
    }

    public void setZulassungStatus(Boolean zulassungStatus) {
        this.zulassungStatus = zulassungStatus;
    }

    @Id
    @Column(name = "bewerber_id", nullable = false)
    public Integer getBewerberId() {
        return bewerberId;
    }

    public void setBewerberId(Integer bewerberId) {
        this.bewerberId = bewerberId;
    }

    @Id
    @Column(name = "unterlagen_id", nullable = false)
    public Integer getUnterlagenId() {
        return unterlagenId;
    }

    public void setUnterlagenId(Integer unterlagenId) {
        this.unterlagenId = unterlagenId;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "bewerber_id", referencedColumnName = "bewerber_id")
    @JoinColumn(name = "bewerber_id", referencedColumnName = "bewerber_id", nullable = false)
    public BewerberEntity getBewerberByBewerberId() {
        return bewerberByBewerberId;
    }

    public void setBewerberByBewerberId(BewerberEntity bewerberByBewerberId) {
        this.bewerberByBewerberId = bewerberByBewerberId;
    }

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "unterlagen_id", referencedColumnName = "unterlagen_id")
    @JoinColumn(name = "unterlagen_id", referencedColumnName = "unterlagen_id", nullable = false)
    public BewerbungsunterlagenEntity getBewerbungsunterlagenByUnterlagenId() {
        return bewerbungsunterlagenByUnterlagenId;
    }

    public void setBewerbungsunterlagenByUnterlagenId(BewerbungsunterlagenEntity bewerbungsunterlagenByUnterlagenId) {
        this.bewerbungsunterlagenByUnterlagenId = bewerbungsunterlagenByUnterlagenId;
    }

    @Basic
    @Column(name = "bewerbungseingang", nullable = false)
    public LocalDate getBewerbungseingang() {
        return bewerbungseingang;
    }

    public void setBewerbungseingang(LocalDate bewerbungseingang) {
        this.bewerbungseingang = bewerbungseingang;
    }
}
