package com.example.api.dto;

import com.example.api.model.ClassEntity;

public record ClassEntityDTO(Long id, String name, String taxId) {

    public static ClassEntityDTO from(ClassEntity classEntity) {
        return new ClassEntityDTO(classEntity.getId(), classEntity.getName(), classEntity.getTaxId());
    }

}
