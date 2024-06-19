INSERT INTO Holidays (name, the_year, the_month, day_of_month, day_of_week, week_of_month, duration_days, holiday_type)
VALUES
    ('Independence Day', NULL, 7, 4, NULL, NULL, 1, 'ANNUAL'),
    ('Labor Day', NULL, 9, NULL, 1, 1, 1, 'RELATIVE');

INSERT INTO Users (username, password, role)
VALUES
    ('admin', '$2a$10$bduukYecKVGKAWyb9uIhHO.zAsjomrIhjuEHGuU60DGliI/Y1g6WC', 'ADMIN'),
    ('user', '$2a$10$bduukYecKVGKAWyb9uIhHO.zAsjomrIhjuEHGuU60DGliI/Y1g6WC', 'USER');


INSERT INTO Brands (brand_name)
VALUES
    ('Stihl'),
    ('Werner'),
    ('DeWalt'),
    ('Ridgid');

INSERT INTO Tool_Types (tool_type_name, daily_charge, weekday_charge, weekend_charge, holiday_charge)
VALUES
    ('Ladder', 1.99, true, true, false),
    ('Chainsaw', 1.49, true, false, true),
    ('Jackhammer', 2.99, true, false, false);

INSERT INTO Tools (tool_code, tool_type_id, brand_id)
VALUES
    ('CHNS',
        (SELECT tool_type_id FROM Tool_Types WHERE tool_type_name = 'Chainsaw'),
        (SELECT brand_id FROM Brands WHERE brand_name = 'Stihl')),
    ('LADW',
        (SELECT tool_type_id FROM Tool_Types WHERE tool_type_name = 'Ladder'),
        (SELECT brand_id FROM Brands WHERE brand_name = 'Werner')),
    ('JAKD',
        (SELECT tool_type_id FROM Tool_Types WHERE tool_type_name = 'Jackhammer'),
        (SELECT brand_id FROM Brands WHERE brand_name = 'DeWalt')),
    ('JAKR',
        (SELECT tool_type_id FROM Tool_Types WHERE tool_type_name = 'Jackhammer'),
        (SELECT brand_id FROM Brands WHERE brand_name = 'Ridgid'));


INSERT INTO Tool_Quantities (tool_id, in_stock_count)
VALUES
    ((SELECT tool_id FROM Tools WHERE tool_code = 'CHNS'), 2),
    ((SELECT tool_id FROM Tools WHERE tool_code = 'LADW'), 5),
    ((SELECT tool_id FROM Tools WHERE tool_code = 'JAKD'), 10),
    ((SELECT tool_id FROM Tools WHERE tool_code = 'JAKR'), 1);

INSERT INTO Settings (setting_key, setting_value)
VALUES ('notifyDueRentalsCron', '0 0 8 * * ?');
