package kkalinowski.springframework.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Krzysztof Kalinowski on 17/11/2019.
 */

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String getIndexPage(){
        return "index";
    }
}
