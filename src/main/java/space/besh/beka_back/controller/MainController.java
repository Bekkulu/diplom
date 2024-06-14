package space.besh.beka_back.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/")
@Slf4j
public class MainController {

    Gson gson;

    public MainController(Gson gson) {
        this.gson = gson;
    }

    @GetMapping(produces = "application/json")
    public String greeter(HttpServletRequest request) {
        log.info("greeter endpoint accessed by {}", request.getRemoteAddr());
        String response = "Hello World!";
        log.info("response: {}", response);
        return gson.toJson(response);
    }

}
