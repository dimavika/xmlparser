package parser;

import entity.Medicine;
import entity.RecipeMedicine;
import entity.enums.Group;
import entity.enums.Pack;
import entity.enums.Version;
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

public class StAXParser implements XmlParser<Medicine> {

    private static StAXParser ourInstance = new StAXParser();
    public static StAXParser getInstance() {
        return ourInstance;
    }
    private XMLInputFactory inputFactory;
    private List<Medicine> medicines = new ArrayList<>();
    private StAXParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

    private final static Logger logger = Logger.getLogger(StAXParser.class);
    @Override
    public List<Medicine> getData(String path) throws ParserException {
        medicines.clear();
        logger.info("start StAX");
        FileInputStream inputStream;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(path));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamReader.START_ELEMENT) {
                    name = reader.getLocalName();
                    if ((name.equals("medicine")) || (name.equals("recipe-medicine"))) {
                        Medicine medicine = buildMedicine(reader);
                        logger.debug("Add " + medicine.toString());
                        medicines.add(medicine);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new ParserException();
        } catch (XMLStreamException e) {
            throw new ParserException("" + e);
        }

        return medicines;
    }

    private Medicine buildMedicine(XMLStreamReader reader) throws XMLStreamException, ParserException {
        String str = reader.getLocalName();
        if (str.equals("medicine")) {
            Medicine medicine = new Medicine();
            String name;
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (name) {
                            case "name":
                                medicine.setName(getXMLText(reader));
                                break;
                            case "firm":
                                medicine.setFirm(getXMLText(reader));
                                break;
                            case "group":
                                medicine.setGroup(Group.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case "version":
                                medicine.setVersion(Version.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case "pack":
                                medicine.setPack(Pack.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case "dosage":
                                medicine.setDosage(Integer.parseInt(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (name.equals("medicine")) {
                            return medicine;
                        }
                        break;
                }
            }
        } else {
            RecipeMedicine recipeMedicine = new RecipeMedicine();
            String name;
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (name) {
                            case "name":
                                recipeMedicine.setName(getXMLText(reader));
                                break;
                            case "firm":
                                recipeMedicine.setFirm(getXMLText(reader));
                                break;
                            case "group":
                                recipeMedicine.setGroup(Group.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case "version":
                                recipeMedicine.setVersion(Version.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case "pack":
                                recipeMedicine.setPack(Pack.valueOf(getXMLText(reader).toUpperCase()));
                                break;
                            case "dosage":
                                recipeMedicine.setDosage(Integer.parseInt(getXMLText(reader)));
                                break;
                            case "doctor-name":
                                recipeMedicine.setDoctorName(getXMLText(reader));
                                break;
                            case "medical-org":
                                recipeMedicine.setMedicalOrganization(getXMLText(reader));
                                break;
                            case "days-duration":
                                recipeMedicine.setDaysDuration(Integer.parseInt(getXMLText(reader)));
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (name.equals("recipe-medicine")) {
                            return recipeMedicine;
                        }
                        break;
                }
            }
        }
        throw new ParserException("Unknown element in flower");
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
