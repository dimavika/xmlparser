package com.epam.entity.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "group")
@XmlEnum
public enum Group {

    @XmlEnumValue("antibiotic")
    ANTIBIOTIC,
    @XmlEnumValue("pain_medication")
    PAIN_MEDICATION,
    @XmlEnumValue("vitamin")
    VITAMIN
}
