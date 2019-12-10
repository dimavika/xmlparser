package com.epam.entity.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "pack")
@XmlEnum
public enum Pack {

    @XmlEnumValue("box")
    BOX,
    @XmlEnumValue("bank")
    BANK,
    @XmlEnumValue("test_tube")
    TEST_TUBE
}
