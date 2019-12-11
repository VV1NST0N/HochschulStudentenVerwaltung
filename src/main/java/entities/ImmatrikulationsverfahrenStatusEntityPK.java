package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ImmatrikulationsverfahrenStatusEntityPK implements Serializable {
    private Integer bewerberId;
    private Integer unterlagenId;

    @Column(name = "bewerber_id", nullable = false)
    @Id
    public Integer getBewerberId() {
        return bewerberId;
    }

    public void setBewerberId(Integer bewerberId) {
        this.bewerberId = bewerberId;
    }

    @Column(name = "unterlagen_id", nullable = false)
    @Id
    public Integer getUnterlagenId() {
        return unterlagenId;
    }

    public void setUnterlagenId(Integer unterlagenId) {
        this.unterlagenId = unterlagenId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmatrikulationsverfahrenStatusEntityPK that = (ImmatrikulationsverfahrenStatusEntityPK) o;
        return Objects.equals(bewerberId, that.bewerberId) &&
                Objects.equals(unterlagenId, that.unterlagenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bewerberId, unterlagenId);
    }
}
