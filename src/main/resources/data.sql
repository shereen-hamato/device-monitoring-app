insert into address (id, address_line_1,zip, city, country) values
(100,'Cementvägen 8', '111 11','Södertälje','Sweden'),
(200,'Balkvägen 12', '222 22' ,'Stockholm','Sweden'),
(300,'Budgetvägen 1', '333 33', 'Uppsala', 'Sweden');


insert into customer (id, first_name,last_name, customer_id, address_id) values
(100,'Kalles', 'Grustransporter AB','kalles',100),
(200,'Johans ', 'Bulk AB','johans',200),
(300,'Haralds ', 'Värdetransporter AB','haralds',300);



insert into appliance (id, appliance_id, factory_id) values
(100,'YS2R4X20005399401', 'ABC123'),
(200,'VLUR4X20009093588', 'DEF456'),
(300,'VLUR4X20009048066', 'GHI789'),
(400,'YS2R4X20005388011', 'JKL012'),
(500,'YS2R4X20005387949', 'MNO345'),
(600,'YS2R4X20009048066', 'PQR678'),
(700,'YS2R4X20005387055', 'STU901');

insert into appliance_status (id, appliance_id, customer_id,status,last_modified) values
(100,100, 100,'up',CURRENT_TIME),
(200,200, 100,'up',CURRENT_TIME),
(300,300, 100,'up',CURRENT_TIME),
(400,400,200,'up',CURRENT_TIME),
(500,500,200, 'up',CURRENT_TIME),
(600,600,300, 'up',CURRENT_TIME),
(700,700,300, 'up',CURRENT_TIME);



