package com.epam.parser;

public class ParserFactory {

    private static ParserFactory instance;
    private final static String DOM = "DOM";
    private final static String SAX = "STAX";
    private final static String JAXB = "JAXB";

    public static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }
        return instance;
    }

    private ParserFactory() {

    }

    public XmlParser newParser(String parserType) throws ParserException {
        XmlParser parser;
        switch (parserType) {
            case DOM:
                parser = new DomParser();
                break;
            case SAX:
                parser = new StaxParser();
                break;
            case JAXB:
                parser = new JaxbParser();
                break;
            default:
                throw new ParserException("Parser type not found");
        }
        return parser;
    }

}
