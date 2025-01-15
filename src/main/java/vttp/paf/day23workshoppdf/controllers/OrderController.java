package vttp.paf.day23workshoppdf.controllers;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.paf.day23workshoppdf.models.Order;
import vttp.paf.day23workshoppdf.models.exceptions.ResourceNotFoundException;
import vttp.paf.day23workshoppdf.services.OrderService;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderSvc;

    @GetMapping("order/total")
    public String getOrderSummary(@RequestParam(name = "orderId") String orderId) {

        return "redirect:/order/total/%s".formatted(orderId);
    }

    @GetMapping("/order/total/{order_id}")
    public String getOrderSummaryByOrderId(@PathVariable(name = "order_id") String orderId, Model model) throws ParseException {

        try {
            int orderIdAsInt = Integer.parseInt(orderId);
            Optional<Order> opt = orderSvc.getOrderSummary(orderIdAsInt);

            if (opt.isEmpty())
                throw new ResourceNotFoundException(orderIdAsInt);

            model.addAttribute("order", opt.get());
            return "orderSummary";

            
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
}
