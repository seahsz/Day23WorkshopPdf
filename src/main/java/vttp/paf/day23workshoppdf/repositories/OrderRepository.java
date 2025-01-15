package vttp.paf.day23workshoppdf.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate template;

    public SqlRowSet getOrdersByOrderId(int orderId) {

        return template.queryForRowSet(Queries.SQL_GET_ORDER_BY_ORDERID, orderId);
    }
    
}
