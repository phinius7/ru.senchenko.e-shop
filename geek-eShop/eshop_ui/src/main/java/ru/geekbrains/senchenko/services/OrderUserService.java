package ru.geekbrains.senchenko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.senchenko.controllers.repr.UserRepr;
import ru.geekbrains.senchenko.entities.Order;
import ru.geekbrains.senchenko.entities.User;
import ru.geekbrains.senchenko.repositories.OrderRepository;
import ru.geekbrains.senchenko.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class OrderUserService {

    private final OrderRepository orderRepository;
    private final CartUserService cartUserService;
    private UserRepository userRepository;
    private final UserService userService;

    public OrderUserService(OrderRepository orderRepository,
                            CartUserService cartUserService,
                            UserRepository userRepository,
                            UserService userService) {
        this.orderRepository = orderRepository;
        this.cartUserService = cartUserService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Autowired


    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder() {
        Order order = new Order();
        order.setCode(UUID.randomUUID().toString().substring(0,4));
        UserRepr userRepr = userService.getCurrentUser();
        User user = userRepository.findUserById(userRepr.getId());
        System.out.println("createOrder user = " + user);
        order.setUser(user);
        order.setTotalPrice(cartUserService.getTotalPrice());
        order.setOrderEntries(cartUserService.getOrderEntries());
        cartUserService.getOrderEntries().forEach(orderEntry -> {
            orderEntry.setOrder(order);
        });
        orderRepository.save(order);
        cartUserService.clearCart();
        return order;
    }
}
