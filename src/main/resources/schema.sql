
CREATE TABLE Holidays (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          the_year INT,
                          the_month INT,
                          day_of_month INT,
                          day_of_week INT,
                          week_of_month INT,
                          duration_days INT NOT NULL,
                          holiday_type VARCHAR(50) NOT NULL
);




CREATE TABLE Users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       role VARCHAR(10),
                       roles varchar(20)
);

CREATE TABLE Customers (
                         customer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         contact VARCHAR(50) NOT NULL
);

CREATE TABLE Tool_Types (
                         tool_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         tool_type_name VARCHAR(50) NOT NULL,
                         daily_charge DOUBLE PRECISION NOT NULL,
                         weekday_charge BIT NOT NULL,
                         weekend_charge BIT NOT NULL,
                         holiday_charge BIT NOT NULL
);

CREATE TABLE Brands (
                       brand_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       brand_name VARCHAR(50) NOT NULL
);

CREATE TABLE Tools (
                       tool_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       tool_code VARCHAR(50) NOT NULL,
                       tool_type_id BIGINT NOT NULL,
                       brand_id BIGINT NOT NULL,
                       FOREIGN KEY (tool_type_id) REFERENCES Tool_Types(tool_type_id),
                       FOREIGN KEY (brand_id) REFERENCES Brands(brand_id)

);

CREATE TABLE Tool_Quantities (
                        tool_quantity_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        tool_id BIGINT NOT NULL,
                        in_stock_count INT NOT NULL,
                        FOREIGN KEY (tool_id) REFERENCES Tools(tool_id)
);

CREATE TABLE Rentals (
                         rental_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         tool_id BIGINT NOT NULL,
                         customer_id BIGINT NOT NULL,
                         rental_days INT NOT NULL,
                         discount_percent INT NOT NULL,
                         checkout_date DATE NOT NULL,
                         due_date DATE NOT NULL,
                         charge_days INT NOT NULL,
                         pre_discount_charge DOUBLE PRECISION NOT NULL,
                         discount_amount DOUBLE PRECISION NOT NULL,
                         final_charge DOUBLE PRECISION NOT NULL,
                         FOREIGN KEY (tool_id) REFERENCES Tools(tool_id),
                         FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);



