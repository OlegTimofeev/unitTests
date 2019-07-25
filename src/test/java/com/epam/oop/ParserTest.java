package com.epam.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest {

    private Parser parser = new Parser();
    private PropertyReader pr = new PropertyReader();
    private static final String WRONG_PHRASE = "123&quot;456&gt;789&lt;0<br>12345";
    private static final String CORRECT_PHRASE = "123\"456>789<0\n12345";

    @Before
    public void before(){

    }

    @Test
    public void parseTest(){
        parser.setPhrase(WRONG_PHRASE);
        parser.parsePhrase();
        assertEquals(CORRECT_PHRASE,parser.getPhrase());
    }
}
