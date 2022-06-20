package br.unirio.kipao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unirio.kipao.model.Customer;
import br.unirio.kipao.model.Order;
import br.unirio.kipao.model.OrderSubscribe;
import br.unirio.kipao.repository.OrderRepository;
import br.unirio.kipao.security.SecurityService;
import br.unirio.kipao.security.models.User;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private CustomerService costumerService;
	
	public Order saveOrder(Order order) {
		
		Customer customer = costumerService.getCustomerByLoggedUser();
		
		order.setCustomer(customer);
		order.getItems().forEach(item -> item.setOrder(order));
		
		Order savedOrder = orderRepository.save(order);
		orderRepository.refresh(savedOrder);
		
		return savedOrder;
	}
	
	public Order saveOrderSubscribe(OrderSubscribe order) {
		
		Customer customer = costumerService.getCustomerByLoggedUser();
		
		order.setCustomer(customer);
		order.setType("Assinatura");
		order.getItems().forEach(item -> item.setOrder(order));
		
		OrderSubscribe savedOrder = orderRepository.save(order);
		orderRepository.refresh(savedOrder);
		
		return savedOrder;	
	}

	public Iterable<Order> getOrdersByCustomer()
	{
		User user = securityService.getUser();
		
		return orderRepository.findOrdersByCustomerUserId(user.getUid());
	}

}
