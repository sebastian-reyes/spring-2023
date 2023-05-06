package com.example.app.service;

import com.example.app.interfaceService.ICategoriaService;
import com.example.app.model.Categoria;
import com.example.app.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository repo;

    @Override
    public List<Categoria> listarCategorias() {
        return repo.findAll();
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return repo.save(categoria);
    }

    @Override
    public Categoria buscarCategoria(int id) {
        return repo.findById(id).orElse(null);
    }
}
