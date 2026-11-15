package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.api.dto.ClassEntityDTO;
import com.example.api.exception.NotFoundException;
import com.example.api.model.ClassEntity;
import com.example.api.repository.ClassEntityRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClassEntityServiceTest {

    @Mock
    private ClassEntityRepository classEntityRepository;

    @InjectMocks
    private ClassEntityService classEntityService;

    @Test
    void should_create_class_entity_when_data_is_valid() {
        when(classEntityRepository.save(any(ClassEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ClassEntityDTO result = classEntityService.create(new ClassEntityDTO(null, "Fishers Association", "12345678000199"));

        assertEquals("12345678000199", result.taxId());
    }

    @Test
    void should_throw_not_found_when_class_entity_does_not_exist() {
        when(classEntityRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> classEntityService.findById(99L));
    }

}
