package com.example.api.service;

import com.example.api.dto.VesselDTO;
import com.example.api.exception.NotFoundException;
import com.example.api.model.Vessel;
import com.example.api.repository.VesselRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VesselService {

    private final VesselRepository vesselRepository;
    private final ClassEntityService classEntityService;

    public VesselService(VesselRepository vesselRepository, ClassEntityService classEntityService) {
        this.vesselRepository = vesselRepository;
        this.classEntityService = classEntityService;
    }

    public List<VesselDTO> findAll() {
        return vesselRepository.findAll().stream().map(VesselDTO::from).toList();
    }

    public VesselDTO findById(Long id) {
        return VesselDTO.from(getVessel(id));
    }

    @Transactional
    public VesselDTO create(VesselDTO dto) {
        Vessel vessel = new Vessel();
        fill(vessel, dto);
        return VesselDTO.from(vesselRepository.save(vessel));
    }

    @Transactional
    public VesselDTO update(Long id, VesselDTO dto) {
        Vessel vessel = getVessel(id);
        fill(vessel, dto);
        return VesselDTO.from(vesselRepository.save(vessel));
    }

    @Transactional
    public void delete(Long id) {
        vesselRepository.delete(getVessel(id));
    }

    private Vessel getVessel(Long id) {
        return vesselRepository.findById(id).orElseThrow(() -> new NotFoundException("Vessel not found"));
    }

    private void fill(Vessel vessel, VesselDTO dto) {
        vessel.setName(dto.name());
        vessel.setRegistrationNumber(dto.registrationNumber());
        vessel.setState(dto.state());
        vessel.setClassEntity(dto.classEntityId() != null ? classEntityService.getClassEntity(dto.classEntityId()) : null);
    }

}
