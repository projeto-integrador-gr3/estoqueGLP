package br.com.projetointegradorgr3.estoqueGLP.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    private String helloWorld(@PathVariable("name") String name) {
        return "Hello world, " + name + "!";
    }
}
