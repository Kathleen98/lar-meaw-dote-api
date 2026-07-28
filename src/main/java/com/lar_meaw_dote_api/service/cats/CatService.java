package com.lar_meaw_dote_api.service.cats;

import com.lar_meaw_dote_api.dto.CreateCatRequest;
import com.lar_meaw_dote_api.model.Cat;
import com.lar_meaw_dote_api.repository.CatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {

    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> listCats() {
        return catRepository.findAll();
    }

    public Cat create(CreateCatRequest request) {
        Cat cat = new Cat(request.name(), request.age(), request.color(), request.altered());
        return catRepository.save(cat);
    }

    public Optional<Cat> findCat(Long id) {
        return catRepository.findById(id);
    }

    public Cat updatedCat(Long id, CreateCatRequest request) {
        Cat cat = catRepository.findById(id).orElseThrow();
        cat.setName(request.name());
        cat.setAge(request.age());
        cat.setColor(request.color());
        cat.setAltered(request.altered());

        return catRepository.save(cat);

    }

    public void deleteCat(Long id) {
        catRepository.deleteById(id);
    }
}
