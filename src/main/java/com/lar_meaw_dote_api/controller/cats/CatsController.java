package com.lar_meaw_dote_api.controller.cats;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cats")
public class CatsController{

    @GetMapping
    public String listCats(){
        return "Deu certo, gatinhos!";
    }
}