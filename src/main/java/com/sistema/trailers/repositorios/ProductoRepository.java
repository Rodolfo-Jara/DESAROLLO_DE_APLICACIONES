package com.sistema.trailers.repositorios;

import com.sistema.trailers.modelo.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Productos,Integer> {
    Page<Productos> findByDescripcionContaining(String descripcion, Pageable pageable);




}
