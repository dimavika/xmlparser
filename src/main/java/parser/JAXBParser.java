package parser;

import entity.Medicine;
import entity.Medicines;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JAXBParser implements XmlParser<Medicine> {

    @Override
    public List<Medicine> getData(String path) throws ParserException {
        File file = new File(path);
        try {
            JAXBContext context = JAXBContext.newInstance(Medicines.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Medicines medicines = (Medicines) unmarshaller.unmarshal(file);
            return medicines.getMedicines();
        } catch (JAXBException e) {
            throw new ParserException("Wrong file");
        }
    }
}
