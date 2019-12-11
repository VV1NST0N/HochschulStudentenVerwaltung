package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "bewerbungsunterlagen", schema = "informationssystem")
public class BewerbungsunterlagenEntity {
    private Integer unterlagenId;
    private Boolean hochschulreife;
    private Boolean krankenversicherung;
    private Boolean immatrikulationsantrag;
    private Boolean bewerbungsschreiben;
    private String hochschulreifeLocation;
    private String krankenversicherungLocation;
    private String immatrikulationsantragLocation;
    private String bewerbungsschreibenLocation;
    private Collection<ImmatrikulationsverfahrenStatusEntity> immatrikulationsverfahrenStatusesByUnterlagenId;

    @Id
    @Column(name = "unterlagen_id", nullable = false)
    public Integer getUnterlagenId() {
        return unterlagenId;
    }

    public void setUnterlagenId(Integer unterlagenId) {
        this.unterlagenId = unterlagenId;
    }

    @Basic
    @Column(name = "hochschulreife", nullable = false)
    public Boolean getHochschulreife() {
        return hochschulreife;
    }

    public void setHochschulreife(Boolean hochschulreife) {
        this.hochschulreife = hochschulreife;
    }

    @Basic
    @Column(name = "krankenversicherung", nullable = false)
    public Boolean getKrankenversicherung() {
        return krankenversicherung;
    }

    public void setKrankenversicherung(Boolean krankenversicherung) {
        this.krankenversicherung = krankenversicherung;
    }

    @Basic
    @Column(name = "immatrikulationsantrag", nullable = false)
    public Boolean getImmatrikulationsantrag() {
        return immatrikulationsantrag;
    }

    public void setImmatrikulationsantrag(Boolean immatrikulationsantrag) {
        this.immatrikulationsantrag = immatrikulationsantrag;
    }

    @Basic
    @Column(name = "bewerbungsschreiben", nullable = true)
    public Boolean getBewerbungsschreiben() {
        return bewerbungsschreiben;
    }

    public void setBewerbungsschreiben(Boolean bewerbungsschreiben) {
        this.bewerbungsschreiben = bewerbungsschreiben;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BewerbungsunterlagenEntity that = (BewerbungsunterlagenEntity) o;
        return Objects.equals(unterlagenId, that.unterlagenId) &&
                Objects.equals(hochschulreife, that.hochschulreife) &&
                Objects.equals(krankenversicherung, that.krankenversicherung) &&
                Objects.equals(immatrikulationsantrag, that.immatrikulationsantrag) &&
                Objects.equals(bewerbungsschreiben, that.bewerbungsschreiben);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unterlagenId, hochschulreife, krankenversicherung, immatrikulationsantrag, bewerbungsschreiben);
    }



    @Basic
    @Column(name = "hochschulreifeLocation", nullable = true, length = 80)
    public String getHochschulreifeLocation() {
        return hochschulreifeLocation;
    }

    public void setHochschulreifeLocation(String hochschulreifeLocation) {
        this.hochschulreifeLocation = hochschulreifeLocation;
    }

    @Basic
    @Column(name = "krankenversicherungLocation", nullable = true, length = 80)
    public String getKrankenversicherungLocation() {
        return krankenversicherungLocation;
    }

    public void setKrankenversicherungLocation(String krankenversicherungLocation) {
        this.krankenversicherungLocation = krankenversicherungLocation;
    }

    @Basic
    @Column(name = "immatrikulationsantragLocation", nullable = true, length = 80)
    public String getImmatrikulationsantragLocation() {
        return immatrikulationsantragLocation;
    }

    public void setImmatrikulationsantragLocation(String immatrikulationsantragLocation) {
        this.immatrikulationsantragLocation = immatrikulationsantragLocation;
    }

    @Basic
    @Column(name = "bewerbungsschreibenLocation", nullable = true, length = 80)
    public String getBewerbungsschreibenLocation() {
        return bewerbungsschreibenLocation;
    }

    public void setBewerbungsschreibenLocation(String bewerbungsschreibenLocation) {
        this.bewerbungsschreibenLocation = bewerbungsschreibenLocation;
    }

    @OneToMany(mappedBy = "bewerbungsunterlagenByUnterlagenId")
    public Collection<ImmatrikulationsverfahrenStatusEntity> getImmatrikulationsverfahrenStatusesByUnterlagenId() {
        return immatrikulationsverfahrenStatusesByUnterlagenId;
    }

    public void setImmatrikulationsverfahrenStatusesByUnterlagenId(Collection<ImmatrikulationsverfahrenStatusEntity> immatrikulationsverfahrenStatusesByUnterlagenId) {
        this.immatrikulationsverfahrenStatusesByUnterlagenId = immatrikulationsverfahrenStatusesByUnterlagenId;
    }
}
