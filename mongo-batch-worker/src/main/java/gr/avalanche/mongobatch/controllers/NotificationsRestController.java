package gr.avalanche.mongobatch.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 * NotificationsRestController Class
 * @author eskiada
 */
@RestController
public class NotificationsRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationsRestController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String hello() { //TODO Delete This Method
        Timestamp persisted = null;
        try {
            LOGGER.info("hello() Method Invoked.");
            persisted = jdbcTemplate.queryForObject("SELECT testing FROM testing WHERE id = 1", Timestamp.class);
        } catch (Exception exception) {
            LOGGER.error("Exception", exception);
        }
        return persisted==null ? "No Entries Found" : "Persisted Value Is " + persisted;
    }
}
