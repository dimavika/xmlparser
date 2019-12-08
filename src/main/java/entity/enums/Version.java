package entity.enums;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "version")
public enum  Version {

    @XmlEnumValue("tablets")
    TABLETS,
    @XmlEnumValue("capsules")
    CAPSULES,
    @XmlEnumValue("powder")
    POWDER,
    @XmlEnumValue("drops")
    DROPS
}
