package com.epam.parser;

import com.epam.entity.Medicine;
import com.epam.entity.RecipeMedicine;
import com.epam.entity.enums.Group;
import com.epam.entity.enums.Pack;
import com.epam.entity.enums.Version;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StaxParser implements XmlParser {

    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    private List<Medicine> medicines = new ArrayList<>();
    private final static Logger logger = Logger.getLogger(StaxParser.class);

    @Override
    public List<Medicine> parse(String path) throws ParserException {
        medicines.clear();
        logger.info("start StAX");
        try {
            FileInputStream inputStream = new FileInputStream(new File(path));
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamReader.START_ELEMENT) {
                    String name = reader.getLocalName();
                    if (name.equals(XmlTagConstants.MEDICINE_TAG)) {
                        Medicine medicine = buildMedicine(reader);
                        logger.debug("Add " + medicine.toString());
                        medicines.add(medicine);
                    } else if (name.equals(XmlTagConstants.RECIPE_TAG)) {
                        RecipeMedicine recipeMedicine = buildRecipeMedicine(reader);
                        logger.debug("Add " + recipeMedicine.toString());
                        medicines.add(recipeMedicine);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new ParserException(e.getMessage());
        }
        return medicines;
    }

    private Medicine buildMedicine(XMLStreamReader reader) throws XMLStreamException, ParserException {
        Medicine medicine = new Medicine();
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    String name = reader.getLocalName();
                    switch (name) {
                        case XmlTagConstants.NAME:
                            medicine.setName(getXMLText(reader));
                            break;
                        case XmlTagConstants.FIRM:
                            medicine.setFirm(getXMLText(reader));
                            break;
                        case XmlTagConstants.GROUP:
                            Group group = Group.valueOf(getXMLText(reader).toUpperCase());
                            medicine.setGroup(group);
                            break;
                        case XmlTagConstants.VERSION:
                            Version version = Version.valueOf(getXMLText(reader).toUpperCase());
                            medicine.setVersion(version);
                            break;
                        case XmlTagConstants.PACK:
                            Pack pack = Pack.valueOf(getXMLText(reader).toUpperCase());
                            medicine.setPack(pack);
                            break;
                        case XmlTagConstants.DOSAGE:
                            int dosage = Integer.parseInt(getXMLText(reader));
                            medicine.setDosage(dosage);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (reader.getLocalName().equals("medicine")) {
                        return medicine;
                    }
            }
        }
        throw new ParserException("Unknown element in xml file");
    }

    private RecipeMedicine buildRecipeMedicine(XMLStreamReader reader) throws XMLStreamException, ParserException {
        RecipeMedicine recipeMedicine = new RecipeMedicine();
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    String name = reader.getLocalName();
                    switch (name) {
                        case XmlTagConstants.NAME:
                            recipeMedicine.setName(getXMLText(reader));
                            break;
                        case XmlTagConstants.FIRM:
                            recipeMedicine.setFirm(getXMLText(reader));
                            break;
                        case XmlTagConstants.GROUP:
                            Group group = Group.valueOf(getXMLText(reader).toUpperCase());
                            recipeMedicine.setGroup(group);
                            break;
                        case XmlTagConstants.VERSION:
                            Version version = Version.valueOf(getXMLText(reader).toUpperCase());
                            recipeMedicine.setVersion(version);
                            break;
                        case XmlTagConstants.PACK:
                            Pack pack = Pack.valueOf(getXMLText(reader).toUpperCase());
                            recipeMedicine.setPack(pack);
                            break;
                        case XmlTagConstants.DOSAGE:
                            int dosage = Integer.parseInt(getXMLText(reader));
                            recipeMedicine.setDosage(dosage);
                            break;
                        case XmlTagConstants.DOCTOR_NAME:
                            recipeMedicine.setDoctorName(getXMLText(reader));
                            break;
                        case XmlTagConstants.MEDICAL_ORG:
                            recipeMedicine.setMedicalOrganization(getXMLText(reader));
                            break;
                        case XmlTagConstants.DAYS_DURATION:
                            int daysDuration = Integer.parseInt(getXMLText(reader));
                            recipeMedicine.setDaysDuration(daysDuration);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (reader.getLocalName().equals("recipe-medicine")) {
                        return recipeMedicine;
                    }
                    }
        }
        throw new ParserException("Unknown element in xml file");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        logger.debug("Add data to " + text);
        return text;
    }
}
