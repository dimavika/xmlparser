package com.epam.entity;

import com.epam.entity.enums.Group;
import com.epam.entity.enums.Pack;
import com.epam.entity.enums.Version;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "recipe-medicine")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecipeMedicine extends Medicine{

    @XmlElement(name = "doctor-name")
    private String doctorName;
    @XmlElement(name = "medical-org")
    private String medicalOrganization;
    @XmlElement(name = "days-duration")
    private int daysDuration;

    public RecipeMedicine() {
    }

    public RecipeMedicine(String name, String firm, Group group, Version version, Pack pack, int dosage,
                          String doctorName, String medicalOrganization, int daysDuration) {
        super(name, firm, group, version, pack, dosage);
        this.doctorName = doctorName;
        this.medicalOrganization = medicalOrganization;
        this.daysDuration = daysDuration;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getMedicalOrganization() {
        return medicalOrganization;
    }

    public int getDaysDuration() {
        return daysDuration;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setMedicalOrganization(String medicalOrganization) {
        this.medicalOrganization = medicalOrganization;
    }

    public void setDaysDuration(int daysDuration) {
        this.daysDuration = daysDuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) return false;

        RecipeMedicine that = (RecipeMedicine) o;

        if (daysDuration != that.daysDuration) return false;
        if (!doctorName.equals(that.doctorName)) return false;
        return medicalOrganization.equals(that.medicalOrganization);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + doctorName.hashCode();
        result = 31 * result + medicalOrganization.hashCode();
        result = 31 * result + daysDuration;
        return result;
    }

    @Override
    public String toString() {
        return "RecipeMedicine{" +
                "doctorName='" + doctorName + '\'' +
                ", medicalOrganization='" + medicalOrganization + '\'' +
                ", daysDuration=" + daysDuration +
                "} " + super.toString();
    }
}
