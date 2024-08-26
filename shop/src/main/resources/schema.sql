create table shop (
    id bigint primary key auto_increment,
    identifier varchar not null,
    status varchar not null,
    date_shop date
);

create table shop_item (
    id bigint primary key auto_increment,
    product_identifier varchar(100) not null,
    amount int not null,
    price float not null,
    shop_id bigint REFERENCES shop(id)
);
--
--INSERT INTO shop (identifier, status, date_shop) VALUES
--('SHOP001', 'active', '2024-08-01'),
--('SHOP002', 'inactive', '2024-08-05'),
--('SHOP003', 'active', '2024-08-10'),
--('SHOP004', 'under maintenance', '2024-08-15');
--
--INSERT INTO shop_item (product_identifier, amount, price, shop_id) VALUES
--('PROD001', 10, 19.99, 1),
--('PROD002', 5, 29.99, 4),
--('PROD003', 20, 9.99, 2),
--('PROD004', 15, 49.99, 2),
--('PROD005', 7, 15.99, 3);