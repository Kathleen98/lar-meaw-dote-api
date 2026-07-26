package com.lar_meaw_dote_api.controller.cats;

import com.lar_meaw_dote_api.model.Cat;
import com.lar_meaw_dote_api.service.cats.CatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService){
        this.catService = catService;
    }

    @GetMapping
    public List<Cat> listCats(){
        return catService.listCats();
    }
}