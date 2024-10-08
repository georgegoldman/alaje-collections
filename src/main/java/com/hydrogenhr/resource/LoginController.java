package com.hydrogenhr.resource;

import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

/**
 * Project title: foundation-service
 *
 * @author johnadeshola
 * Date: 10/1/24
 * Time: 2:05 PM
 */
@Controller
public class LoginController {

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
