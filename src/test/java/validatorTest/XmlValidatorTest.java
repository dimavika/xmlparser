package validatorTest;

import org.junit.Assert;
import org.junit.Test;
import com.epam.parser.XmlValidator;

import java.io.File;

public class XmlValidatorTest {

    private final static File XML = new File("./src/main/resources/medicines.xml");
    private final static File XSD = new File("./src/main/resources/schema.xsd");

    @Test
    public void validateXmlByXsdTest(){
        XmlValidator validator = new XmlValidator();
        Assert.assertTrue(validator.validateXmlByXsd(XML, XSD));
    }
}
