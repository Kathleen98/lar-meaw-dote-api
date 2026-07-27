package com.lar_meaw_dote_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateCatRequest (

        @NotBlank(message = "Preencha o nome")
        String name,

        @NotNull @PositiveOrZero(message = "A idade deve ser um número positivo")
        Integer age,

        @NotBlank
        String color,

        @NotNull
        Boolean altered
){}