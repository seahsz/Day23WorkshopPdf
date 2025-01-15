use northwind;

create user 'northwinduser'@'%' identified by 'northwindpassword';

grant all privileges on northwind.* to 'northwinduser'@'%';
flush privileges;

-- Kenneth provided answer
select
	o.id as order_id,
	date_format(o.order_date, '%Y-%m-%d') as order_date,
	o.customer_id as customer_id,
	sum(od.quantity * od.unit_price * (1-od.discount)) as total_price,
	sum(od.quantity * p.standard_cost) as cost_price
	from orders as o
	inner join order_details as od
		on o.id = od.order_id
	inner join products as p
		on od.product_id = p.id
	where o.id=30
	group by
		o.id,
		o.order_date,
		o.customer_id;


