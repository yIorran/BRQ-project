package com.example.pdv.application.controller;

import com.example.pdv.application.model.Produto;
import com.example.pdv.application.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository repositoryGenP;

    @PostMapping
    public void salvarProduto(@RequestBody Produto products){
        repositoryGenP.save(products);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<Object> buscarProduct(){
        return new ResponseEntity<Object>(repositoryGenP.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deletarProdutos(@PathVariable("id") Integer id){
        repositoryGenP.deleteById(id);
    }

    @PutMapping("/attprod/{id}")
    public Produto atualizaProduto(@PathVariable("id") Integer id, @RequestBody Produto produto){
        return attProduto(id, produto);
    }

    public Produto attProduto(Integer id, Produto produto) {
        produto.setId(id);
        return repositoryGenP.save(produto);
    }
}
