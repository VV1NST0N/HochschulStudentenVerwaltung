package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "zahlungsstatusfremdsystem", schema = "informationssystem")
public class ZahlungsstatusfremdsystemEntity {
    private int bewerberId;
    private Boolean zahlungsstatus;
    private int unterlagenId;

    @Id
    @Column(name = "bewerber_id")
    public int getBewerberId() {
        return bewerberId;
    }

    public void setBewerberId(int bewerberId) {
        this.bewerberId = bewerberId;
    }

    @Basic
    @Column(name = "zahlungsstatus")
    public Boolean getZahlungsstatus() {
        return zahlungsstatus;
    }

    public void setZahlungsstatus(Boolean zahlungsstatus) {
        this.zahlungsstatus = zahlungsstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZahlungsstatusfremdsystemEntity that = (ZahlungsstatusfremdsystemEntity) o;
        return bewerberId == that.bewerberId &&
                zahlungsstatus == that.zahlungsstatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bewerberId, zahlungsstatus);
    }

    @Basic
    @Column(name = "unterlagen_id")
    public int getUnterlagenId() {
        return unterlagenId;
    }

    public void setUnterlagenId(int unterlagenId) {
        this.unterlagenId = unterlagenId;
    }
}
