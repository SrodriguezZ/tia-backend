package com.proyect.base.persistencia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;
    private Long stock;

    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private  LocalDateTime fechaModificacion;
    @ManyToOne
    @JoinColumn(name = "locales_id")
    private Locales locales;

    @PrePersist
    private void createdActivo(){
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now(ZoneOffset.UTC);

    }

    @PreUpdate
    private void updateActivo(){
        this.fechaModificacion = LocalDateTime.now(ZoneOffset.UTC);
    }

}
