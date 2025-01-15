package vttp.paf.day23workshoppdf.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import vttp.paf.day23workshoppdf.models.Order;
import vttp.paf.day23workshoppdf.models.OrderItem;
import vttp.paf.day23workshoppdf.repositories.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public Optional<Order> getOrderSummary(int orderId) throws ParseException {

        // Query for the relevant orders
        SqlRowSet rs = orderRepo.getOrdersByOrderId(orderId);

        List<OrderItem> items = new LinkedList<>();

        Order o = new Order();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        while (rs.next()) {

            OrderItem item = new OrderItem();

            o.setOrderId(rs.getInt("order_id"));
            o.setCustomerId(rs.getInt("customer_id"));
            o.setOrderDate(df.parse(rs.getString("order_date")));

            item.setQuantity(rs.getDouble("quantity"));
            item.setPrice(rs.getDouble("unit_price"));
            item.setCost(rs.getDouble("cost"));
            item.setDiscount(rs.getDouble("discount"));
            items.add(item);
        }

        // Return Optional.empty if there are no items --> To prompt error in controller
        if (items.isEmpty())
            return Optional.empty();

        // Process data to populate Order
        double totalPrice = 0;
        double totalCost = 0;

        for (int i = 0; i < items.size(); i++) {
            OrderItem currItem = items.get(i);
            totalPrice += currItem.getQuantity() * currItem.getPrice() * (1 - currItem.getDiscount());
            totalCost += currItem.getQuantity() * currItem.getCost();
        }

        o.setPrice(totalPrice);
        o.setCost(totalCost);

        return Optional.of(o);
    }

    
    
}
