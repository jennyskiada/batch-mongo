package gr.avalanche.mongobatch.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NotificationsRestController Class
 * @author eskiada
 */
@RestController
public class NotificationsRestController {

    //TODO Delete This Method
    @RequestMapping("hello")
    public String hello() {
        return "Hello World";
    }
}
