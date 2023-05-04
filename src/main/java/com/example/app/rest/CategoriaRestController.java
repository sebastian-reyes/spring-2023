package com.example.app.rest;

import com.example.app.interfaceService.ICategoriaService;
import com.example.app.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/categorias")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService service;

    @GetMapping
    public ResponseEntity<?> listarCategorias() {
        Map<String, Object> response = new HashMap<>();
        List<Categoria> categorias = service.listarCategorias();
        try {
            if(categorias!=null){
                response.put("categorias",categorias);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                response.put("mensaje", "No se encontraron categorías");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoria(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Categoria categoria = null;
        try {
            categoria = service.buscarCategoria(id);
            if (categoria != null) {
                response.put("categoria",categoria);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                response.put("mensaje", "La categoría no se encontró en la base de datos.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", Objects.requireNonNull(e.getMessage()).concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
