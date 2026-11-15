package com.example.api.controller;

import com.example.api.dto.ClassEntityDTO;
import com.example.api.service.ClassEntityService;
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
@RequestMapping("/class-entities")
public class ClassEntityController {

    private final ClassEntityService classEntityService;

    public ClassEntityController(ClassEntityService classEntityService) {
        this.classEntityService = classEntityService;
    }

    @GetMapping
    public List<ClassEntityDTO> findAll() {
        return classEntityService.findAll();
    }

    @GetMapping("/{id}")
    public ClassEntityDTO findById(@PathVariable Long id) {
        return classEntityService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassEntityDTO create(@RequestBody ClassEntityDTO dto) {
        return classEntityService.create(dto);
    }

    @PutMapping("/{id}")
    public ClassEntityDTO update(@PathVariable Long id, @RequestBody ClassEntityDTO dto) {
        return classEntityService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        classEntityService.delete(id);
    }

}
