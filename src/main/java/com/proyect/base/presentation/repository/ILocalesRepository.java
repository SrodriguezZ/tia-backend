package com.proyect.base.presentation.repository;

import com.proyect.base.persistencia.entity.Locales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocalesRepository extends JpaRepository<Locales,Long> {
}
