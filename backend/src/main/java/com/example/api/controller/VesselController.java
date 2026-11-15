package com.example.api.controller;

import com.example.api.dto.VesselDTO;
import com.example.api.service.VesselService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vessels")
public class VesselController {

    private final VesselService vesselService;

    public VesselController(VesselService vesselService) {
        this.vesselService = vesselService;
    }

    @GetMapping
    public List<VesselDTO> findAll() {
        return vesselService.findAll();
    }

    @GetMapping("/{id}")
    public VesselDTO findById(@PathVariable Long id) {
        return vesselService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VesselDTO create(@RequestBody VesselDTO dto) {
        return vesselService.create(dto);
    }

    @PutMapping("/{id}")
    public VesselDTO update(@PathVariable Long id, @RequestBody VesselDTO dto) {
        return vesselService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        vesselService.delete(id);
    }

}
