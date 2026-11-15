package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.api.dto.SupplierDTO;
import com.example.api.exception.NotFoundException;
import com.example.api.model.Supplier;
import com.example.api.repository.SupplierRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierService supplierService;

    @Test
    void should_create_supplier_when_data_is_valid() {
        when(supplierRepository.save(any(Supplier.class))).thenAnswer(invocation -> invocation.getArgument(0));

        SupplierDTO result = supplierService.create(new SupplierDTO(null, "Harbor Fuel", "98765432000188", "RS"));

        assertEquals("RS", result.state());
    }

    @Test
    void should_throw_not_found_when_supplier_does_not_exist() {
        when(supplierRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> supplierService.findById(99L));
    }

}
