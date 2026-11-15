package com.example.api.dto;

import com.example.api.model.Vessel;

public record VesselDTO(Long id, String name, String registrationNumber, String state, Long classEntityId) {

    public static VesselDTO from(Vessel vessel) {
        Long classEntityId = vessel.getClassEntity() != null ? vessel.getClassEntity().getId() : null;
        return new VesselDTO(vessel.getId(), vessel.getName(), vessel.getRegistrationNumber(), vessel.getState(), classEntityId);
    }

}
