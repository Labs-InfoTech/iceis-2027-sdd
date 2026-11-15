package com.example.api.dto;

import com.example.api.model.Supplier;

public record SupplierDTO(Long id, String name, String taxId, String state) {

    public static SupplierDTO from(Supplier supplier) {
        return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getTaxId(), supplier.getState());
    }

}
