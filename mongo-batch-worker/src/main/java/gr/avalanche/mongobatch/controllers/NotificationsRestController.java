package gr.avalanche.mongobatch.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NotificationsRestController Class
 * @author eskiada
 */
@RestController
public class NotificationsRestController {

    @RequestMapping("/")
    public String hello() { //TODO Delete This Method
        return "Hello From A Worker.";
    }
}
