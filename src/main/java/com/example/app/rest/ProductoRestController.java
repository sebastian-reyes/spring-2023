package com.example.app.rest;

import com.example.app.interfaceService.IProductoService;
import com.example.app.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {

    @Autowired
    private IProductoService service;

    @GetMapping("/page/{page}")
    public Page<Producto> listarProductos(@PathVariable Integer page) {
        return service.listarProductos(PageRequest.of(page, 10));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProducto(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Producto producto = null;
        try {
            producto = service.buscarProducto(id);
            if (producto != null) {
                response.put("producto", producto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("mensaje", "No se encontró el producto");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("message", "Error when querying the database.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
