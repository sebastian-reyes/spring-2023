package com.example.app.interfaceService;

import com.example.app.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductoService {
    public Page<Producto> listarProductos(Pageable pageable);
    public Producto buscarProducto(int id);
}
