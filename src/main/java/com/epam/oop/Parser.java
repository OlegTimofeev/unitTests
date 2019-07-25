package com.epam.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.logging.Logger;

public class Parser {

    final static Logger logger = Logger.getLogger(Parser.class.toString());

    public static Map<String,String> symbolsMap;
    private Connection connection;
    private String phrase;

    public void getPhraseFromSite() {
        if (connection.checkConnection()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getSiteConnection().getInputStream(), "utf8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("quote__body")) {
                        phrase = reader.readLine();
                        connection.closeConnection();
                        reader.close();
                        if (!phrase.contains("Утверждено")) {
                            return ;
                        } else {
                            phrase = "Цитаты не найдено.";
                            return;
                        }
                    }
                }
            }catch (IOException e){
                logger.warning("Неудалось создать входящий поток");
            }
        }
        phrase = "Отстутствует соединение.";
    }

    public void parsePhrase(){
        phrase = phrase.trim();
        for(String key : symbolsMap.keySet() ){
            phrase = phrase.replaceAll(key,symbolsMap.get(key));
        }
    }

    public String getPhrase() {
        return phrase;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setSymbolsMap(Map<String, String> symbolsMap) {
        this.symbolsMap = symbolsMap;
    }
}
