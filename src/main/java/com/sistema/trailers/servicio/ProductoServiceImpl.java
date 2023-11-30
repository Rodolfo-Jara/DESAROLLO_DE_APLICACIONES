package com.sistema.trailers.servicio;

import com.sistema.trailers.modelo.Productos;
import com.sistema.trailers.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServiceImpl implements ProductoService {



    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Productos guardar(Productos producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Productos> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Productos obtenerProducto(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }
    @Override
    public Page<Productos> listarProductosPaginados(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }
    @Override
    public Productos actualizarProducto(int id, Productos producto) {
        if (productoRepository.existsById(id)) {
            producto.setCodigo(id);
            return productoRepository.save(producto);
        } else {
            return null;
        }
    }

    @Override
    public Page<Productos> buscarPorDescripcion(String descripcion, Pageable pageable) {
        return productoRepository.findByDescripcionContaining(descripcion, pageable);
    }


}
