package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.api.dto.VesselDTO;
import com.example.api.exception.NotFoundException;
import com.example.api.model.Vessel;
import com.example.api.repository.VesselRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VesselServiceTest {

    @Mock
    private VesselRepository vesselRepository;

    @Mock
    private ClassEntityService classEntityService;

    @InjectMocks
    private VesselService vesselService;

    @Test
    void should_create_vessel_when_data_is_valid() {
        when(vesselRepository.save(any(Vessel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        VesselDTO result = vesselService.create(new VesselDTO(null, "Sea Breeze", "REG-001", "SC", null));

        assertEquals("REG-001", result.registrationNumber());
    }

    @Test
    void should_throw_not_found_when_vessel_does_not_exist() {
        when(vesselRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> vesselService.findById(99L));
    }

}
