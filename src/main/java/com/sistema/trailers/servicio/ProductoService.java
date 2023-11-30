package com.sistema.trailers.servicio;

import com.sistema.trailers.modelo.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductoService {

    Productos guardar(Productos producto);

    List<Productos> listarProductos();

    Productos obtenerProducto(int id);

    void eliminarProducto(int id);

    Page<Productos> listarProductosPaginados(Pageable pageable);

    Productos actualizarProducto(int id, Productos producto);

    Page<Productos> buscarPorDescripcion(String descripcion, Pageable pageable);




}
