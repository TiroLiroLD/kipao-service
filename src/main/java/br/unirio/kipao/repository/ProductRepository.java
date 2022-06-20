package br.unirio.kipao.repository;

import org.springframework.data.repository.CrudRepository;

import br.unirio.kipao.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
