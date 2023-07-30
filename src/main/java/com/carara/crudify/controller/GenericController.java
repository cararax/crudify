package com.carara.crudify.controller;

import com.carara.crudify.service.GenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GenericController<T, I> {
    private final GenericService<T, I> service;

    protected GenericController(GenericService<T, I> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        return new ResponseEntity<>(service.create(entity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<T>> readAll() {
        return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> readById(@PathVariable I id) {
        return service.readById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<T> update(@RequestBody T entity) {
        return new ResponseEntity<>(service.update(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable I id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
