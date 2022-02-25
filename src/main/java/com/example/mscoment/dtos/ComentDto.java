package com.example.mscoment.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ComentDto {
    @NotBlank
    private String name;
    @NotBlank
    private String message;

}

