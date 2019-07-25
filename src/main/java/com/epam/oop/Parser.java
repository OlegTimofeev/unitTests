package com.epam.oop;

import java.util.Map;
import java.util.logging.Logger;

public class Parser {

    final static Logger logger = Logger.getLogger(Parser.class.toString());

    public static Map<String,String> symbolsMap;
    private String phrase;

    public void parsePhrase(){
        phrase = phrase.trim();
        for(String key : symbolsMap.keySet() ){
            phrase = phrase.replaceAll(key,symbolsMap.get(key));
        }
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
