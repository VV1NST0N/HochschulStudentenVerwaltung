package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StudentStudiengangEntityPK implements Serializable {
    private Integer matNr;
    private Integer studiengangId;

    @Column(name = "mat_nr", nullable = false)
    @Id
    public Integer getMatNr() {
        return matNr;
    }

    public void setMatNr(Integer matNr) {
        this.matNr = matNr;
    }

    @Column(name = "studiengang_id", nullable = false)
    @Id
    public Integer getStudiengangId() {
        return studiengangId;
    }

    public void setStudiengangId(Integer studiengangId) {
        this.studiengangId = studiengangId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentStudiengangEntityPK that = (StudentStudiengangEntityPK) o;
        return Objects.equals(matNr, that.matNr) &&
                Objects.equals(studiengangId, that.studiengangId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matNr, studiengangId);
    }
}
