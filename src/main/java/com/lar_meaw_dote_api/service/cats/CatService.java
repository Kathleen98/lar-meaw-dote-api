package com.lar_meaw_dote_api.service.cats;

import com.lar_meaw_dote_api.model.Cat;
import com.lar_meaw_dote_api.repository.CatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatService {

    private final CatRepository catRepository;

    public CatService(CatRepository catRepository){
        this.catRepository = catRepository;
    }

    public List<Cat> listCats(){
        return catRepository.findAll();
    }
}
