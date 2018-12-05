package com.longnh.mininow.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.longnh.mininow.model.OrderItem;
import com.longnh.mininow.model.OrderPlaced;
import com.longnh.mininow.model.Product;
import com.longnh.mininow.model.ProductExtra;
import com.longnh.mininow.repository.OrderItemRepository;
import com.longnh.mininow.repository.OrderPlacedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class OrderPlacedServiceImpl implements OrderPlacedService {

    private static ObjectMapper om = new ObjectMapper();
    static {
        om.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    @Autowired
    OrderPlacedRepository orderPlacedRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public OrderPlaced createOrder(Map<String, Object> request) throws IOException {
        OrderPlaced order = om.convertValue(request.get("order"), OrderPlaced.class);
        JsonNode detail = om.readTree((String) request.get("detail"));
        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < detail.size(); i++) {
            JsonNode itemNode = detail.get(i);

            Product product = new Product();
            product.setId(itemNode.get("productID").asLong());
            product.setName(itemNode.get("name").asText());

            Set<ProductExtra> extras = new HashSet<>();
            JsonNode extrasNode = itemNode.get("extras");
            if (extrasNode != null) {
                extras = new HashSet<>(Arrays.asList(om.readValue(extrasNode.toString(), ProductExtra[].class)));
            }


            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setExtras(extras);
            item.setDescription(itemNode.get("description").asText());
            item.setPrice(itemNode.get("price").asInt());
            item.setQuantity(itemNode.get("quantity").asInt());
            item.setTotal(itemNode.get("totalPrice").asInt());
            items.add(item);
        }

        OrderPlaced result = orderPlacedRepository.save(order);

        for (OrderItem item : items) {
            orderItemRepository.save(item);
        }

        return result;

    }

    @Override
    public OrderPlaced getOrder(long id) {
        Optional<OrderPlaced> order = orderPlacedRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public List<OrderPlaced> getOngoingOrder(int status, long id) {
        return orderPlacedRepository.findByStatusLessThanAndCustomer_IdEquals(status, id);
    }

    @Override
    public List<OrderPlaced> getFinishedOrder(int status, long id) {
        return orderPlacedRepository.findByStatusGreaterThanAndCustomer_IdEquals(status, id);
    }

    @Override
    public void changeOrderStatus(int status, long id) {
        Optional<OrderPlaced> order = orderPlacedRepository.findById(id);
        if (order.isPresent()) {
            OrderPlaced orderPlaced = order.get();
            orderPlaced.setStatus(status);
            orderPlacedRepository.save(orderPlaced);
        }
    }
}
