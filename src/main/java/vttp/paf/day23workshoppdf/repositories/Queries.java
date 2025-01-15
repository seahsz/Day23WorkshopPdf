package vttp.paf.day23workshoppdf.repositories;

public class Queries {

    public static final String SQL_GET_ORDER_BY_ORDERID = 
        "select orders.id as order_id, date_format(orders.order_date, '%Y-%m-%d') as order_date, " +
        "orders.customer_id as customer_id, " +
        "order_details.quantity as quantity, order_details.unit_price as unit_price, " +
        "order_details.discount as discount, products.standard_cost as cost " +
        "from orders " +
        "join order_details on orders.id = order_details.order_id " +
        "join products on order_details.product_id = products.id " +
        "where order_id = ?";

}
