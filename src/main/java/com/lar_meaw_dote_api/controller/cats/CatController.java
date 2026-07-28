package com.lar_meaw_dote_api.controller.cats;

import com.lar_meaw_dote_api.dto.CreateCatRequest;
import com.lar_meaw_dote_api.model.Cat;
import com.lar_meaw_dote_api.service.cats.CatService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cats")
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping
    public List<Cat> listCats() {
        return catService.listCats();
    }

    @PostMapping
    public ResponseEntity<Cat> create(@Valid @RequestBody CreateCatRequest request) {
        Cat saved = catService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public Optional<Cat> findCat(@PathVariable Long id) {
        return catService.findCat(id);
    }

    @PutMapping("/{id}")
    public Cat updatedCat(@PathVariable Long id, @Valid @RequestBody CreateCatRequest request) {
        return catService.updatedCat(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id) {
        catService.deleteCat(id);
        return ResponseEntity.noContent().build();
    }
}