

CREATE  TABLE customer (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_id varchar(255) NOT NULL UNIQUE,
  first_name varchar(255),
  last_name varchar(255),
  address_id BIGINT not null
);

CREATE TABLE address(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  address_line_1 CHAR(255) NOT NULL,
  address_line_2 CHAR(255),
  zip VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  country  VARCHAR(255) NOT NULL
);

CREATE TABLE appliance (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  appliance_id VARCHAR(255) NOT NULL UNIQUE,
  factory_id VARCHAR(255) NOT NULL
);


CREATE TABLE appliance_status (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  appliance_id BIGINT not null,
  customer_id BIGINT not null,
  status VARCHAR(100) NOT NULL,
  last_modified TIMESTAMP NOT NULL
);

ALTER TABLE customer ADD FOREIGN KEY (address_id) REFERENCES address(id);
ALTER TABLE appliance_status ADD FOREIGN KEY (appliance_id) REFERENCES appliance(id);
ALTER TABLE appliance_status ADD FOREIGN KEY (customer_id) REFERENCES customer(id);




