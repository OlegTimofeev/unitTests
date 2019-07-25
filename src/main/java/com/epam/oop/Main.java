package com.epam.oop;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class.toString());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PropertyReader pr = new PropertyReader();
        logger.info("Input site name");
        String siteName = sc.next();
        while (true) {
            logger.info("Input your number");
            String numStr = sc.next();
            Integer num;
            try {
                num = Integer.parseInt(numStr);
            } catch (NumberFormatException e) {
                continue;
            }

            if (num < 0) {
                break;
            }
            Connection connection = new Connection();
            connection.pr = pr;
            connection.openConnection(siteName,num);
            Parser parser = new Parser();
            parser.setPhrase(connection.getPhraseFromSite());
            parser.parsePhrase();
            logger.info(parser.getPhrase());
        }
    }
}
