package space.besh.beka_back.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController()
@RequestMapping("/")
@Slf4j
public class MainController {

    Gson gson;

    @GetMapping()
    public RedirectView  redirectToSwagger(HttpServletRequest request) {
        log.info("INSPECTOR: {} this govnyuk is accessing my endpoint" +
                        "\nCookies: {}",
                request.getRemoteAddr(),
                request.getCookies()
        );
        return new RedirectView("/swagger-ui/index.html");
    }

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
    //TODO написать функцию которая сохраняет все кто стучался в мой адрес
    // а потом будет пробивать через сервис 2ip.ru или аналогичные инфу про айпи и сохранять уникальные


}
