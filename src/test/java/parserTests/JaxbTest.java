package parserTests;

import com.epam.entity.Medicine;
import com.epam.entity.RecipeMedicine;
import com.epam.entity.enums.Group;
import com.epam.entity.enums.Pack;
import com.epam.entity.enums.Version;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.epam.parser.JaxbParser;
import com.epam.parser.ParserException;
import com.epam.parser.ParserFactory;

import java.util.ArrayList;
import java.util.List;

public class JaxbTest {

    private final static ParserFactory FACTORY = ParserFactory.getInstance();
    private final static String PATH = "./src/main/resources/medicines.xml";
    private final static int EXPECTED_LIST_SIZE = 3;
    private final static Medicine MEDICINE= new Medicine("med1", "firm1", Group.ANTIBIOTIC,
            Version.TABLETS, Pack.BANK, 23);
    private final static RecipeMedicine RECIPE_MEDICINE = new RecipeMedicine("rec-med1", "pharm2",
            Group.PAIN_MEDICATION, Version.CAPSULES, Pack.BOX, 25, "namee",
            "medorg", 10);
    private static List<Medicine> medicines = new ArrayList<>();

    @BeforeClass
    public static void before(){
        try {
            JaxbParser parser = (JaxbParser) FACTORY.newParser("JAXB");
            medicines = parser.parse(PATH);
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseTestSizeOfReturnedListShouldBeThree(){
        Assert.assertEquals(EXPECTED_LIST_SIZE, medicines.size());
    }

    @Test
    public void parseTestFirstObjectInListShouldBeMedicineAndThirdShouldBeRecipeMedicine(){
        Assert.assertEquals(MEDICINE.toString(), medicines.get(0).toString());
        Assert.assertEquals(RECIPE_MEDICINE.toString(), medicines.get(2).toString());
    }
}
