package entity;

import entity.enums.Group;
import entity.enums.Pack;
import entity.enums.Version;

import javax.xml.bind.annotation.*;

@XmlType(name = "medicine")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(RecipeMedicine.class)
public class Medicine {

    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "firm")
    private String firm;
    @XmlElement(name = "group")
    private Group group;
    @XmlElement(name = "version")
    private Version version;
    @XmlElement(name = "pack")
    private Pack pack;
    @XmlElement(name = "dosage")
    private int dosage;

    public String getName() {
        return name;
    }

    public String getFirm() {
        return firm;
    }

    public Group getGroup() {
        return group;
    }

    public Version getVersion() {
        return version;
    }

    public Pack getPack() {
        return pack;
    }

    public int getDosage() {
        return dosage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Medicine medicine = (Medicine) o;

        if (dosage != medicine.dosage) return false;
        if (!name.equals(medicine.name)) return false;
        if (!firm.equals(medicine.firm)) return false;
        if (group != medicine.group) return false;
        if (version != medicine.version) return false;
        return pack == medicine.pack;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + firm.hashCode();
        result = 31 * result + group.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + pack.hashCode();
        result = 31 * result + dosage;
        return result;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", firm='" + firm + '\'' +
                ", group=" + group +
                ", version=" + version +
                ", pack=" + pack +
                ", dosage=" + dosage +
                '}';
    }
}
