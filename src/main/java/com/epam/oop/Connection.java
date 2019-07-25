package com.epam.oop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Logger;

public class Connection {

    final static Logger logger = Logger.getLogger(Connection.class.toString());

    public PropertyReader pr;
    private HttpURLConnection siteConnection;

    public void openConnection(String siteName, Integer num){
        try {
            URL url = new URL(pr.getSiteLink(siteName) + num);
            siteConnection = (HttpURLConnection) url.openConnection();
            siteConnection.setRequestMethod("GET");
        } catch (Exception e){
            logger.warning("Не удалось отправить запрос по ссылке "+pr.getSiteLink(siteName) + num);
        }
    }

    public boolean checkConnection(){
        try {
            if (siteConnection!=null && siteConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return true;
            }
        } catch (Exception ignored){
            logger.warning("Подключение отсутствует");
        }
        return false;
    }

    public String getPhraseFromSite() {
        if (checkConnection()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(siteConnection.getInputStream(), "utf8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("quote__body")) {
                        line = reader.readLine();
                        closeConnection();
                        reader.close();
                        if (!line.contains("Утверждено")) {
                            return line;
                        } else {

                            return "Цитаты не найдено.";
                        }
                    }
                }
            }catch (IOException e){
                logger.warning("Неудалось создать входящий поток");
            }
        }
        return "Отстутствует соединение.";
    }

    public void closeConnection(){
        siteConnection.disconnect();
    }

    public HttpURLConnection getSiteConnection() {
        return siteConnection;
    }

    public void setSiteConnection(HttpURLConnection siteConnection) {
        this.siteConnection = siteConnection;
    }
}
