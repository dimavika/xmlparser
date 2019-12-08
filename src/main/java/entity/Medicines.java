package entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "medicines")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medicines {

    @XmlElement(name = "medicine")
    private List<Medicine> medicines = new ArrayList<>();

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
