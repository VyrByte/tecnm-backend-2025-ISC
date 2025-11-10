package mx.tecnm.backend.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/Test")
@RestController
public class Test {
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hola API Rest";
    }
}