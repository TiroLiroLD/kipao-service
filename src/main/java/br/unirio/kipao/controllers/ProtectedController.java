package br.unirio.kipao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unirio.kipao.model.Order;
import br.unirio.kipao.model.Product;
import br.unirio.kipao.repository.OrderRepository;
import br.unirio.kipao.repository.ProductRepository;
import br.unirio.kipao.security.SecurityService;

@RestController
@RequestMapping("protected")
public class ProtectedController {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("data")
	public String getProtectedData() {
		String name = securityService.getUser().getName();
		return name.split("\\s+")[0] + ", you have accessed protected data from spring boot";
	}
	
	
	@GetMapping("products")
	public ResponseEntity<Iterable<Product>> getProducts()
	{
		return ResponseEntity.ok(productRepository.findAll());
		
	}
	
	@GetMapping("orders")
	public ResponseEntity<Iterable<Order>> getOrders() {
		return ResponseEntity.ok(orderRepository.findAll());
		
	}

}
