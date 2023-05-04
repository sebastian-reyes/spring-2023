package com.example.app.interfaceService;

import com.example.app.model.Categoria;

import java.util.List;

public interface ICategoriaService {
    public List<Categoria> listarCategorias();
    public Categoria guardarCategoria(Categoria categoria);
    public Categoria buscarCategoria(int id);
}
