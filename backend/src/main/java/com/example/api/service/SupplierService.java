package com.example.api.service;

import com.example.api.dto.SupplierDTO;
import com.example.api.exception.NotFoundException;
import com.example.api.model.Supplier;
import com.example.api.repository.SupplierRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierDTO> findAll() {
        return supplierRepository.findAll().stream().map(SupplierDTO::from).toList();
    }

    public SupplierDTO findById(Long id) {
        return SupplierDTO.from(getSupplier(id));
    }

    @Transactional
    public SupplierDTO create(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        fill(supplier, dto);
        return SupplierDTO.from(supplierRepository.save(supplier));
    }

    @Transactional
    public SupplierDTO update(Long id, SupplierDTO dto) {
        Supplier supplier = getSupplier(id);
        fill(supplier, dto);
        return SupplierDTO.from(supplierRepository.save(supplier));
    }

    @Transactional
    public void delete(Long id) {
        supplierRepository.delete(getSupplier(id));
    }

    private Supplier getSupplier(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
    }

    private void fill(Supplier supplier, SupplierDTO dto) {
        supplier.setName(dto.name());
        supplier.setTaxId(dto.taxId());
        supplier.setState(dto.state());
    }

}
