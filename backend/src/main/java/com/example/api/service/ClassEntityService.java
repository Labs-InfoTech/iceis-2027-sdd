package com.example.api.service;

import com.example.api.dto.ClassEntityDTO;
import com.example.api.exception.NotFoundException;
import com.example.api.model.ClassEntity;
import com.example.api.repository.ClassEntityRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassEntityService {

    private final ClassEntityRepository classEntityRepository;

    public ClassEntityService(ClassEntityRepository classEntityRepository) {
        this.classEntityRepository = classEntityRepository;
    }

    public List<ClassEntityDTO> findAll() {
        return classEntityRepository.findAll().stream().map(ClassEntityDTO::from).toList();
    }

    public ClassEntityDTO findById(Long id) {
        return ClassEntityDTO.from(getClassEntity(id));
    }

    @Transactional
    public ClassEntityDTO create(ClassEntityDTO dto) {
        ClassEntity classEntity = new ClassEntity();
        fill(classEntity, dto);
        return ClassEntityDTO.from(classEntityRepository.save(classEntity));
    }

    @Transactional
    public ClassEntityDTO update(Long id, ClassEntityDTO dto) {
        ClassEntity classEntity = getClassEntity(id);
        fill(classEntity, dto);
        return ClassEntityDTO.from(classEntityRepository.save(classEntity));
    }

    @Transactional
    public void delete(Long id) {
        classEntityRepository.delete(getClassEntity(id));
    }

    public ClassEntity getClassEntity(Long id) {
        return classEntityRepository.findById(id).orElseThrow(() -> new NotFoundException("Class entity not found"));
    }

    private void fill(ClassEntity classEntity, ClassEntityDTO dto) {
        classEntity.setName(dto.name());
        classEntity.setTaxId(dto.taxId());
    }

}
