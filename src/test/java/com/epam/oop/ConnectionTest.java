package com.epam.oop;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.HttpURLConnection;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;

public class ConnectionTest {

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

}
