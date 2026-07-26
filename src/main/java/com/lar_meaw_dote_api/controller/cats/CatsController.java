package com.lar_meaw_dote_api.controller.cats;

import com.lar_meaw_dote_api.service.cats.CatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cats")
public class CatsController{

    private final CatsService catsService;

    public CatsController(CatsService catsService){
        this.catsService = catsService;
    }

    @GetMapping
    public String listCats(){
        return catsService.message();
    }
}