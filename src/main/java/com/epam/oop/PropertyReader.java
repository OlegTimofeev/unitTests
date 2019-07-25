package com.epam.oop;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyReader {

    final static Logger logger = Logger.getLogger(PropertyReader.class.toString());

    private Properties siteProperty ;
    private Properties parserProperty ;
    private Map<String,String> allSymbolsMap;

    public PropertyReader() {
        siteProperty = new Properties();
        parserProperty = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/site.properties");
            siteProperty.load(fis);
            fis.close();
        } catch (IOException e) {
            logger.warning("Не удалось открыть файл site.property");
        }
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/parser.properties");
            parserProperty.load(fis);
            getAllSymbols();
            Parser.symbolsMap = getAllSymbolsMap();
            fis.close();
        } catch (IOException e) {
            logger.warning("Не удалось открыть файл parser.property");
        }
    }

    public String getSiteLink(String siteName) {
        return siteProperty.getProperty(siteName);
    }

    private void getAllSymbols() {
        Map<String, String> propertyMap = new HashMap<>();
        for (Object name : parserProperty.keySet()) {
            propertyMap.put((String) name, parserProperty.getProperty((String) name));
        }
        allSymbolsMap = propertyMap;
    }

    public Properties getSiteProperty() {
        return siteProperty;
    }

    public void setSiteProperty(Properties siteProperty) {
        this.siteProperty = siteProperty;
    }

    public Properties getParserProperty() {
        return parserProperty;
    }

    public void setParserProperty(Properties parserProperty) {
        this.parserProperty = parserProperty;
    }

    public Map<String, String> getAllSymbolsMap() {
        return allSymbolsMap;
    }
}
