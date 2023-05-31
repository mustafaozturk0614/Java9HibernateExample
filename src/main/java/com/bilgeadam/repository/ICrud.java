package com.bilgeadam.repository;

import java.util.List;
import java.util.Optional;

public interface ICrud<T> {

    List<T> findAll();
    Optional<T> findById(Long id);

}
