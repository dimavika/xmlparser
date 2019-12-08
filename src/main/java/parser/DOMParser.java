package parser;

import entity.Medicine;
import entity.RecipeMedicine;
import entity.enums.Group;
import entity.enums.Pack;
import entity.enums.Version;
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

public class DOMParser implements XmlParser<Medicine> {

    private static DOMParser ourInstance;
    private DocumentBuilder documentBuilder;

    static {
        try {
            ourInstance = new DOMParser();
        } catch (ParserException e) {
            e.printStackTrace();
        }

    }
    public static DOMParser getInstance() {
        return ourInstance;
    }

    private DOMParser() throws ParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParserException("Error in initialize DOM Parser" + e);
        }
    }

    @Override
    public List<Medicine> getData(String path) throws ParserException {
        List<Medicine> medicines = new ArrayList<>();
        Document document;
        try {
            document = documentBuilder.parse(path);
            Element element = document.getDocumentElement();
            NodeList medicineList = element.getElementsByTagName("medicine");
            NodeList recipeMedicineList = element.getElementsByTagName("recipe-medicine");
            for (int i = 0; i < medicineList.getLength(); i++) {
                Element medicineElement = (Element) medicineList.item(i);
                Medicine medicine = buildMedicine(medicineElement);
                medicines.add(medicine);
            }
            for (int i = 0; i < recipeMedicineList.getLength(); i++) {
                Element medicineElement = (Element) recipeMedicineList.item(i);
                Medicine medicine = buildMedicine(medicineElement);
                medicines.add(medicine);
            }
        } catch (SAXException | IOException e) {
            throw new ParserException();
        }
        return medicines;
    }

    private Medicine buildMedicine(Element element) {
        String name = element.getNodeName();
        if (name.equals("medicine")) {
            Medicine medicine = new Medicine();
            medicine.setName(getElementTextContent(element, "name"));
            medicine.setFirm(getElementTextContent(element, "firm"));
            medicine.setGroup(Group.valueOf(getElementTextContent(element, "group").toUpperCase()));
            medicine.setVersion(Version.valueOf(getElementTextContent(element, "version").toUpperCase()));
            medicine.setPack(Pack.valueOf(getElementTextContent(element, "pack").toUpperCase()));
            medicine.setDosage(Integer.parseInt(getElementTextContent(element, "dosage")));
            return medicine;
        } else {
            RecipeMedicine recipeMedicine = new RecipeMedicine();
            recipeMedicine.setName(getElementTextContent(element, "name"));
            recipeMedicine.setFirm(getElementTextContent(element, "firm"));
            recipeMedicine.setGroup(Group.valueOf(getElementTextContent(element, "group").toUpperCase()));
            recipeMedicine.setVersion(Version.valueOf(getElementTextContent(element, "version").toUpperCase()));
            recipeMedicine.setPack(Pack.valueOf(getElementTextContent(element, "pack").toUpperCase()));
            recipeMedicine.setDosage(Integer.parseInt(getElementTextContent(element, "dosage")));
            recipeMedicine.setDoctorName(getElementTextContent(element, "doctor-name"));
            recipeMedicine.setMedicalOrganization(getElementTextContent(element, "medical-org"));
            recipeMedicine.setDaysDuration(Integer.parseInt(getElementTextContent(element, "days-duration")));
            return recipeMedicine;
        }
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
