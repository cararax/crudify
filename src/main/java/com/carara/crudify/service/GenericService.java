package com.carara.crudify.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<T, I> {
    private final JpaRepository<T, I> repository;

    protected GenericService(JpaRepository<T, I> repository) {
        this.repository = repository;
    }

    public List<T> readAll() {
        return repository.findAll();
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public Optional<T> readById(I id) {
        return repository.findById(id);
    }

    public T update(T entity) {
        return repository.save(entity);
    }

    public void delete(I id) {
        repository.deleteById(id);
    }
}
