package com.epam.parser;

import com.epam.entity.Medicine;
import com.epam.entity.RecipeMedicine;
import com.epam.entity.enums.Group;
import com.epam.entity.enums.Pack;
import com.epam.entity.enums.Version;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements XmlParser {

    private final static DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();;

    @Override
    public List<Medicine> parse(String path) throws ParserException {
        List<Medicine> medicines = new ArrayList<>();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = FACTORY.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParserException(e.getMessage());
        }
        try {
            Document document = documentBuilder.parse(path);
            Element element = document.getDocumentElement();
            NodeList list = element.getChildNodes();
            Medicine medicine = null;
            for (int i = 1; i < list.getLength() ; i = i+2) {
                Element medicineElement = (Element) list.item(i);
                if (medicineElement.getNodeName().equals(XmlTagConstants.MEDICINE_TAG)) {
                    medicine = buildMedicine(medicineElement);
                } else if (medicineElement.getNodeName().equals(XmlTagConstants.RECIPE_TAG)){
                    medicine = buildRecipeMedicine(medicineElement);
                }
                medicines.add(medicine);
            }
        } catch (SAXException | IOException e) {
            throw new ParserException(e.getMessage());
        }
        return medicines;
    }

    private Medicine buildMedicine(Element element) {
        Medicine medicine = new Medicine();
        medicine.setName(getElementTextContent(element, XmlTagConstants.NAME));
        medicine.setFirm(getElementTextContent(element, XmlTagConstants.FIRM));
        Group group = Group.valueOf(getElementTextContent(element, XmlTagConstants.GROUP).toUpperCase());
        medicine.setGroup(group);
        Version version = Version.valueOf(getElementTextContent(element, XmlTagConstants.VERSION).toUpperCase());
        medicine.setVersion(version);
        Pack pack = Pack.valueOf(getElementTextContent(element, XmlTagConstants.PACK).toUpperCase());
        medicine.setPack(pack);
        int dosage = Integer.parseInt(getElementTextContent(element, XmlTagConstants.DOSAGE));
        medicine.setDosage(dosage);
        return medicine;
    }

    private Medicine buildRecipeMedicine(Element element) {
        RecipeMedicine recipeMedicine = new RecipeMedicine();
        recipeMedicine.setName(getElementTextContent(element, XmlTagConstants.NAME));
        recipeMedicine.setFirm(getElementTextContent(element, XmlTagConstants.FIRM));
        Group group = Group.valueOf(getElementTextContent(element, XmlTagConstants.GROUP).toUpperCase());
        recipeMedicine.setGroup(group);
        Version version = Version.valueOf(getElementTextContent(element, XmlTagConstants.VERSION).toUpperCase());
        recipeMedicine.setVersion(version);
        Pack pack = Pack.valueOf(getElementTextContent(element, XmlTagConstants.PACK).toUpperCase());
        recipeMedicine.setPack(pack);
        int dosage = Integer.parseInt(getElementTextContent(element, XmlTagConstants.DOSAGE));
        recipeMedicine.setDosage(dosage);
        recipeMedicine.setDoctorName(getElementTextContent(element, XmlTagConstants.DOCTOR_NAME));
        recipeMedicine.setMedicalOrganization(getElementTextContent(element, XmlTagConstants.MEDICAL_ORG));
        int daysDuration = Integer.parseInt(getElementTextContent(element, XmlTagConstants.DAYS_DURATION));
        recipeMedicine.setDaysDuration(daysDuration);
        return recipeMedicine;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
