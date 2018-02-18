/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;


/**
 * Hello world!
 *
 * @author <a href="mailto:${developer-email}">${developer-name}</a>
 * @version 1.0-SNAPSHOT
 **/
public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(final String[] args) {
        LOGGER.info("Hello fucking world, we are the fucking awesome team");
        final List<String> list = Lists.newLinkedList();
        LOGGER.info("This is an empty list '{}'", list);

        UtilsFile fr = new UtilsFile("example.in");
        int[] header = fr.getHeader();
        char[][] data = fr.getData();

    }

}