package cloud.dowhat.monolith.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author linen
 */
@RequestMapping("/")
@Controller
public class ThymeleafController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("hello")
    @ResponseBody
    public String hello() {
        return "Hello Welcome My Email";
    }

}
