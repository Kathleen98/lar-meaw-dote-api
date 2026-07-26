package com.lar_meaw_dote_api.repository;

import com.lar_meaw_dote_api.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CatRepository extends JpaRepository<Cat, Long> {
}
