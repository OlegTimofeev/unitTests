package com.epam.oop;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

public class ConnectionTest {
    public static final String PHRASE =  "PRPRPR\n";
    public static final String RESPONCE = "<main class=\"columns__main \">\n" +
            "<article class=\"quote\" data-quote=\"456957\">\n" +
            "  <div class=\"quote__frame\"><div class=\"quote__body\">\n" +
            PHRASE +
            "              <a class=\"quote__header_permalink\" href=\"/quote/456957\">#456957</a>\n" +
            "      </footer>\n" +
            "      </div>\n" +
            "</article>    </main>";

    private Connection connection;

    @Before
    public void before(){
        connection = new Connection();
        connection.setSiteConnection(Mockito.mock(HttpURLConnection.class));
    }

    @Test
    public void checkConnectionTest() throws IOException {
        when(connection.getSiteConnection().getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        assertTrue(connection.checkConnection());
    }

    @Test
    public void checkConnectionTestWrongAnswer() throws IOException {
        when(connection.getSiteConnection().getResponseCode()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);
        assertFalse(connection.checkConnection());
    }

    @Test
    public void checkConnectionTestConnectionNull() {
        connection.setSiteConnection(null);
        assertFalse(connection.checkConnection());
    }

    @Test
    public void checkgetPhraseFromSite() throws IOException {
        PropertyReader pr = new PropertyReader();
        Parser parser = new Parser();
        parser.setPhrase(PHRASE);
        parser.parsePhrase();
        when(connection.getSiteConnection().getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        when(connection.getSiteConnection().getInputStream()).thenReturn(new ByteArrayInputStream(RESPONCE.getBytes()));
        assertEquals(parser.getPhrase(),connection.getPhraseFromSite());
    }

}
