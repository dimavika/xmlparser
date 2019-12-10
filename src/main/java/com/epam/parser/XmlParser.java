package com.epam.parser;

import com.epam.entity.Medicine;

import java.util.List;

public interface XmlParser {

    List<Medicine> parse(String path) throws ParserException;
}
