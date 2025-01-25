package com.proyect.base.persistencia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Entity
@Table(name = "locales")
public class Locales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_local", nullable = false)
    private String nombreLocal;
    private String direccion;
    private String telefono;

    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private  LocalDateTime fechaModificacion;

    @OneToMany(mappedBy = "locales", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos;

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
