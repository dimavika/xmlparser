package com.epam.entity;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "medicines")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medicines {

    @XmlElementRefs(value = {@XmlElementRef(name = "medicine", type = Medicine.class),
            @XmlElementRef(name = "recipe-medicine", type = RecipeMedicine.class)})
    private List<Medicine> medicines = new ArrayList<>();

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
