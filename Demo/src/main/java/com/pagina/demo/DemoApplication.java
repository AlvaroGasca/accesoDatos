package com.pagina.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication

@Controller
public class DemoApplication {

    @GetMapping("/plantilla1")
    public String getPlantilla1() {
        return "template1"; 
    }

    @GetMapping("/plantilla2")
    public String getPlantilla2() {
        return "Template2"; 
    }
    
    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}