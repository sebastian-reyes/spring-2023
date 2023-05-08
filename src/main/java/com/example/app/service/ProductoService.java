package com.example.app.service;

import com.example.app.interfaceService.IProductoService;
import com.example.app.model.Producto;
import com.example.app.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public Page<Producto> listarProductos(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Producto buscarProducto(int id) {
        return repo.findById(id).orElse(null);
    }
}
