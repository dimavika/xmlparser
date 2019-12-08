import entity.Medicine;
import entity.Medicines;
import parser.JAXBParser;
import parser.ParserException;
import parser.StAXParser;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParserException {
//        DOMParser domParser = DOMParser.getInstance();
//        List<Medicine> list = domParser.getData("./src/main/resources/medicines.xml");
//        System.out.println(list);
//        XmlValidator validator = new XmlValidator();
//        File xml = new File("./src/main/resources/medicines.xml");
//        File xsd = new File("./src/main/resources/schema.xsd");
//        System.out.println(validator.validateXMLByXSD(xml, xsd));

//        Medicines medicines = new Medicines();
//        JAXBParser parser = new JAXBParser();
//        File file = new File("./src/main/resources/medicines.xml");
//        medicines.setMedicines(parser.getData("./src/main/resources/medicines.xml"));
//        System.out.println(medicines.getMedicines().toString());

        StAXParser stAXParser = StAXParser.getInstance();
        List<Medicine> list = stAXParser.getData("./src/main/resources/medicines.xml");
        System.out.println(list);
    }
}
