package com.epam.parser;

import com.epam.entity.Medicine;
import com.epam.entity.Medicines;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JaxbParser implements XmlParser {

    @Override
    public List<Medicine> parse(String path) throws ParserException {
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
