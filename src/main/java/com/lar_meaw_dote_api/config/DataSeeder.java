package com.lar_meaw_dote_api.config;


import com.lar_meaw_dote_api.model.Cat;
import com.lar_meaw_dote_api.repository.CatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CatRepository catRepository;

    public DataSeeder(CatRepository catRepository){
        this.catRepository = catRepository;
    }

    @Override
    public void run(String... args){
        catRepository.save(new Cat("Bolota", 6, "Preto", true));
        catRepository.save(new Cat("Pipoca", 6, "Preto", true));
    }
}
