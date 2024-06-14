package space.besh.beka_back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Позволяет CORS для всех эндпоинтов
                .allowedOrigins("*") // Разрешенные источники (здесь можно указать конкретные домены)
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // Разрешенные HTTP методы
                .allowedHeaders("*"); // Разрешенные заголовки
    }
}
