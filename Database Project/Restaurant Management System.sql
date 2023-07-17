DROP TABLE Delivery;
DROP TABLE OrderItem;
DROP TABLE Payment;
DROP TABLE Orders;
DROP TABLE Customer;
DROP TABLE Membership;
DROP TABLE Reservations;
DROP TABLE ExpiryItems;
DROP TABLE Stock;
DROP TABLE ItemMenu;
DROP TABLE Category;
DROP TABLE Tables;
DROP TABLE Restaurant;
DROP TABLE Staff;
DROP TABLE Discount;


CREATE TABLE Restaurant (
    Restaurant_id NUMBER(10) PRIMARY KEY,
    Capacity NUMBER(10),
    Total_tables NUMBER(10),
    Total_Staff NUMBER(10),
    Location VARCHAR(10) NOT NULL
);

CREATE TABLE Tables (
    Table_id NUMBER(10) PRIMARY KEY,
    Table_Number NUMBER(10) NOT NULL,
    Capacity NUMBER(10)
);

CREATE TABLE Category (
    Category_id NUMBER(20) PRIMARY KEY,
    Name VARCHAR(20) UNIQUE
);

CREATE TABLE ItemMenu (
    Item_id NUMBER(20) PRIMARY KEY,
    Name VARCHAR(20) NOT NULL,
    Price NUMBER(20),
    Description VARCHAR(100),
    Category_id NUMBER(20),
    Restaurant_id NUMBER(20),
    FOREIGN KEY (Category_id) REFERENCES Category(Category_id),
    FOREIGN KEY (Restaurant_id) REFERENCES Restaurant(Restaurant_id)
);

CREATE TABLE Stock (
    Stock_id NUMBER(20) PRIMARY KEY,
    Quantity NUMBER(20),
    Unit_cost NUMBER(20),
    Date_of_Stock DATE,
    expire_date DATE,
    Item_id NUMBER(20),
    FOREIGN KEY (Item_id) REFERENCES ItemMenu(Item_id)
);

CREATE TABLE ExpiryItems (
    Expiry_Item_ID NUMBER PRIMARY KEY,
    Stock_id NUMBER(20),
    Expiration_date DATE,
    Quantity NUMBER(20),
    FOREIGN KEY (Stock_id) REFERENCES Stock(Stock_id)
);

CREATE TABLE Reservations (
    Reservation_id NUMBER(20) PRIMARY KEY,
    Customer_id VARCHAR(20) NOT NULL,
    Date_of_Reservation TIMESTAMP,
    Total_persons NUMBER(20),
    Restaurant_id NUMBER(10),
    Table_id NUMBER(10),
    FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id),
    FOREIGN KEY (Restaurant_id) REFERENCES Restaurant(Restaurant_id),
    FOREIGN KEY (Table_id) REFERENCES Tables(Table_id)
);

CREATE TABLE Membership (
    Membership_id NUMBER(20) PRIMARY KEY,
    Start_date DATE,
    End_date DATE,
    Membership_Type VARCHAR(20) NOT NULL
);

CREATE TABLE Customer (
    Customer_id VARCHAR(20) PRIMARY KEY,
    Customer_name VARCHAR(20) NOT NULL,
    Phone NUMBER(20),
    Membership_id NUMBER(20),
    FOREIGN KEY (Membership_id) REFERENCES Membership(Membership_id)
);

CREATE TABLE Orders (
    Order_id NUMBER(20) PRIMARY KEY,
    Order_date DATE,
    Customer_id VARCHAR(20),
    Reservation_id NUMBER(20),
    Order_time VARCHAR(20),
    Status VARCHAR(20),
    Total_amount NUMBER(20),
    Discount_id VARCHAR(20),
    FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id),
    FOREIGN KEY (Reservation_id) REFERENCES Reservations(Reservation_id),
    FOREIGN KEY (Discount_id) REFERENCES Discount(Discount_id)
);

CREATE TABLE Payment (
    Pay_id NUMBER(20) PRIMARY KEY,
    Method VARCHAR(20),
    Order_id NUMBER(20),
    FOREIGN KEY (Order_id) REFERENCES Orders(Order_id)
);

CREATE TABLE OrderItem (
    Order_item_id NUMBER(20) PRIMARY KEY,
    Order_id NUMBER(20),
    Item_id NUMBER(20),
    Quantity NUMBER(20),
    Special_requests VARCHAR(20),
    FOREIGN KEY (Order_id) REFERENCES Orders(Order_id),
    FOREIGN KEY (Item_id) REFERENCES ItemMenu(Item_id)
);

CREATE TABLE Discount (
    Discount_id VARCHAR(20) PRIMARY KEY,
    Percentage NUMBER(20) CHECK (Percentage >= 0 AND Percentage <= 100),
    Disc_name VARCHAR(20)
); 

CREATE TABLE Staff (
    Staff_id NUMBER(20) PRIMARY KEY,
    Name VARCHAR(20) NOT NULL,
    Position VARCHAR(20),
    Salary NUMBER(20)
);

CREATE TABLE Delivery (
    Delivery_id NUMBER(20) PRIMARY KEY,
    Order_id NUMBER(20),
    Delivery_date DATE,
    Staff_id NUMBER(20),
    Status VARCHAR(20),
    Area varchar(20),
    Address varchar(20),
    FOREIGN KEY (Order_id) REFERENCES Orders(Order_id),
    FOREIGN KEY (Staff_id) REFERENCES Staff(Staff_id)
);

                    --------------------------------------------------------------------

            --------    INSERTING DATA IN TABLES    --------

--insert data in Restaurant table
INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (1, 50, 10, 20, 'Lahore');

INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (2, 40, 8, 15, 'Multan');

INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (3, 30, 6, 10, 'Sialkot');

INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (4, 60, 12, 25, 'Karachi');

INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (5, 35, 7, 12, 'Quttea');

INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (6, 85, 30, 25, 'Islamabad');

INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (7, 55, 20, 15, 'Peshawar');

INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (8, 65, 20, 18, 'Murree');

INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (9, 75, 28, 23, 'Sawat');

INSERT INTO Restaurant (Restaurant_id, Capacity, Total_tables, Total_Staff, Location)
VALUES (10, 85, 35, 29, 'Kalam');

--insert data in Tables table
INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (1, 1, 4);

INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (2, 2, 6);

INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (3, 3, 2);

INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (4, 4, 8);

INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (5, 5, 4);

INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (6, 6, 4);

INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (7, 7, 6);

INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (8, 8, 2);

INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (9, 9, 8);

INSERT INTO Tables (Table_id, Table_Number, Capacity)
VALUES (10, 10, 4);

--insert data in Category table
INSERT INTO Category (Category_id, Name)
VALUES (1, 'Appetizers');

INSERT INTO Category (Category_id, Name)
VALUES (2, 'Main Course');

INSERT INTO Category (Category_id, Name)
VALUES (3, 'Desserts');

INSERT INTO Category (Category_id, Name)
VALUES (4, 'Beverages');

INSERT INTO Category (Category_id, Name)
VALUES (5, 'Salads');

INSERT INTO Category (Category_id, Name)
VALUES (6, 'Seafood');

INSERT INTO Category (Category_id, Name)
VALUES (7, 'Mix Vegetables');

INSERT INTO Category (Category_id, Name)
VALUES (8, 'Pasta');

INSERT INTO Category (Category_id, Name)
VALUES (9, 'Soup');

INSERT INTO Category (Category_id, Name)
VALUES (10, 'Steak');

--insert data in ItemMenu table
INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (1, 'Caesar Salad', 8.99, 'Fresh romaine lettuce with Caesar dressing', 1, 1);

INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (2, 'Margherita Pizza', 12.99, 'Classic pizza with tomato sauce, mozzarella, and basil.', 2, 2);

INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (3, 'Tiramisu', 6.99, 'Italian dessert consisting of layers of coffee-soaked ladyfingers and mascarpone cream.', 3, 3);

INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (4, 'Cocktail', 2.99, 'Carbonated soft drink.', 4, 4);

INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (5, 'Lamb Steak', 9.99, 'Romaine lettuce, croutons, Parmesan cheese, and Caesar dressing.', 5, 5);

INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (6, 'Grilled Salmon', 500, 'Fresh salmon fillet grilled to perfection', 6, 6);

INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (7, 'Vegetable Stir Fry', 700, 'Assorted vegetables stir-fried in a savory sauce', 7, 7);

INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (8, 'Alfredo Pasta', 600, 'Creamy fettuccine pasta with Parmesan sauce', 8, 8);

INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (9, 'Tomato Soup', 1000, 'Classic tomato soup with a hint of basil', 9, 9);

INSERT INTO ItemMenu (Item_id, Name, Price, Description, Category_id, Restaurant_id)
VALUES (10, 'Ribeye Steak', 1500, 'Juicy ribeye steak cooked to your preference', 10, 10);

--insert data in Stock table
INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES(1, 100, 1, TO_DATE('2023-06-01', 'YYYY-MM-DD'), TO_DATE('2024-06-07', 'YYYY-MM-DD'), 1);

INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES(2, 200, 15.99, TO_DATE('2022-06-02', 'YYYY-MM-DD'), TO_DATE('2023-06-02', 'YYYY-MM-DD'), 2);

INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES(3, 150, 12.99, TO_DATE('2022-06-03', 'YYYY-MM-DD'), TO_DATE('2023-06-03', 'YYYY-MM-DD'), 3);

INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES(4, 120, 9.99, TO_DATE('2023-06-04', 'YYYY-MM-DD'), TO_DATE('2024-06-04', 'YYYY-MM-DD'), 4);

INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES(5, 180, 11.99, TO_DATE('2021-06-05', 'YYYY-MM-DD'), TO_DATE('2024-06-05', 'YYYY-MM-DD'), 5);

INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES (6, 50, 2.5, TO_DATE('2021-06-05', 'YYYY-MM-DD'), TO_DATE('2024-06-07', 'YYYY-MM-DD'), 6);

INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES (7, 100, 1, TO_DATE('2021-06-05', 'YYYY-MM-DD'), TO_DATE('2024-06-07', 'YYYY-MM-DD'), 7);

INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES (8, 75, 1.5, TO_DATE('2021-06-05', 'YYYY-MM-DD'), TO_DATE('2024-06-07', 'YYYY-MM-DD'), 8);

INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES (9, 120, 0.75, TO_DATE('2021-06-05', 'YYYY-MM-DD'), TO_DATE('2024-06-07', 'YYYY-MM-DD'), 9);

INSERT INTO Stock (Stock_id, Quantity, Unit_cost, Date_of_Stock, expire_date, Item_id)
VALUES (10, 80, 5, TO_DATE('2021-06-05', 'YYYY-MM-DD'), TO_DATE('2024-06-07', 'YYYY-MM-DD'), 10);


--insert data in ExpiryItems table
INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (1, 1, TO_DATE('2024-06-07', 'YYYY-MM-DD'), 5);

INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (2, 2, TO_DATE('2023-06-07', 'YYYY-MM-DD'), 3);

INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (3, 3, TO_DATE('2023-05-30', 'YYYY-MM-DD'), 10);

INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (4, 4, TO_DATE('2024-06-15', 'YYYY-MM-DD'), 2);

INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (5, 5, TO_DATE('2023-06-20', 'YYYY-MM-DD'), 7);

INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (6, 6, TO_DATE('2023-06-07', 'YYYY-MM-DD'), 4);

INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (7, 7, TO_DATE('2023-06-11', 'YYYY-MM-DD'), 1);

INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (8, 8, TO_DATE('2023-06-19', 'YYYY-MM-DD'), 6);

INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (9, 9, TO_DATE('2023-07-10', 'YYYY-MM-DD'), 9);

INSERT INTO ExpiryItems (Expiry_Item_ID, Stock_id, Expiration_date, Quantity)
VALUES (10, 10, TO_DATE('2023-06-21', 'YYYY-MM-DD'), 3);


--insert data in Customer table
INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST001', 'Sajjad', '1234567890', 1);

INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST002', 'Faseeh', '9876543210', 2);

INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST003', 'Waqas', '5551234567', 3);

INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST004', 'Ali', '4449876543', 4);

INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST005', 'David Wilson', '7775551234', 5);

INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST006', 'Jennifer Davis', '5557891234', 6);

INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST007', 'Jessica Lee', '4446549870', 7);

INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST008', 'Matthew Thompson', '1239874560', 8);

INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST009', 'Laura Anderson', '9871236540', 9);

INSERT INTO Customer (Customer_id, Customer_name, Phone, Membership_id)
VALUES ('CUST010', 'Brian Wilson', '7773335555', 10);


--insert data in Membership table
INSERT INTO Membership (Membership_id,Start_date, End_date, Membership_Type)
VALUES (1, TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2024-01-01', 'YYYY-MM-DD'), 'Gold');

INSERT INTO Membership (Membership_id, Start_date, End_date, Membership_Type)
VALUES (2, TO_DATE('2023-02-01', 'YYYY-MM-DD'), TO_DATE('2024-02-01', 'YYYY-MM-DD'), 'Silver');

INSERT INTO Membership (Membership_id, Start_date, End_date, Membership_Type)
VALUES (3,  TO_DATE('2023-03-01', 'YYYY-MM-DD'), TO_DATE('2024-03-01', 'YYYY-MM-DD'), 'Bronze');

INSERT INTO Membership (Membership_id,Start_date, End_date, Membership_Type)
VALUES (4, TO_DATE('2023-04-01', 'YYYY-MM-DD'), TO_DATE('2024-04-01', 'YYYY-MM-DD'), 'Silver');

INSERT INTO Membership (Membership_id, Start_date, End_date, Membership_Type)
VALUES (5, TO_DATE('2023-05-01', 'YYYY-MM-DD'), TO_DATE('2024-05-01', 'YYYY-MM-DD'), 'Gold');

INSERT INTO Membership (Membership_id, Start_date, End_date, Membership_Type)
VALUES (6, TO_DATE('2023-06-01', 'YYYY-MM-DD'), TO_DATE('2024-06-01', 'YYYY-MM-DD'), 'Silver');

INSERT INTO Membership (Membership_id, Start_date, End_date, Membership_Type)
VALUES (7, TO_DATE('2023-07-01', 'YYYY-MM-DD'), TO_DATE('2024-07-01', 'YYYY-MM-DD'), 'Bronze');

INSERT INTO Membership (Membership_id, Start_date, End_date, Membership_Type)
VALUES (8, TO_DATE('2023-08-01', 'YYYY-MM-DD'), TO_DATE('2024-08-01', 'YYYY-MM-DD'), 'Gold');

INSERT INTO Membership (Membership_id, Start_date, End_date, Membership_Type)
VALUES (9, TO_DATE('2023-09-01', 'YYYY-MM-DD'), TO_DATE('2024-09-01', 'YYYY-MM-DD'), 'Bronze');

INSERT INTO Membership (Membership_id, Start_date, End_date, Membership_Type)
VALUES (10, TO_DATE('2023-10-01', 'YYYY-MM-DD'), TO_DATE('2024-10-01', 'YYYY-MM-DD'), 'Silver');


--insert data in Reservations table
INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (1, 'CUST001', TIMESTAMP '2023-06-01 18:00:00', 2, 1, 1);

INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (2, 'CUST002', TIMESTAMP '2023-06-02 19:30:00', 4, 2, 2);

INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (3, 'CUST003', TIMESTAMP '2023-06-03 20:15:00', 3, 3, 3);

INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (4, 'CUST004', TIMESTAMP '2023-06-04 18:45:00', 2, 4, 4);

INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (5, 'CUST005', TIMESTAMP '2023-06-05 19:00:00', 6, 1, 5);

INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (6, 'CUST006', TIMESTAMP '2023-06-06 17:30:00', 4, 6, 6);

INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (7, 'CUST007', TIMESTAMP '2023-06-07 20:45:00', 2, 3, 7);

INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (8, 'CUST008', TIMESTAMP '2023-06-08 17:45:00', 2, 8, 8);

INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (9, 'CUST009', TIMESTAMP '2023-06-09 19:00:00', 4, 9, 9);

INSERT INTO Reservations (Reservation_id, Customer_id, Date_of_Reservation, Total_persons, Restaurant_id, Table_id)
VALUES (10, 'CUST010', TIMESTAMP '2023-06-10 21:30:00', 2, 10, 10);


--insert data in Discount table
INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS001', 10, 'Summer Special');

INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS002', 4, 'Happy Hour');

INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS003', 2, 'Weekend Deal');

INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS004', 5, 'Meeting lunch');

INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS005', 8, 'Staff Family Pack');

INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS006', 5, 'Early Bird');

INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS007', 3, 'Big Deals');

INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS008', 5, 'Student Discount');

INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS009', 5, 'Special Days');

INSERT INTO Discount (Discount_id, Percentage, Disc_name)
VALUES ('DIS010', 5, 'Birthday Special');


--insert data in Orders table
select * from Orders
INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (1, TO_DATE('2023-05-03', 'YYYY-MM-DD'), 'CUST001', 1, '19:30', 'Pending', 459, 'DIS001');

INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (2, TO_DATE('2023-07-10', 'YYYY-MM-DD'), 'CUST002', 2, '20:00', 'Completed', 529, 'DIS002');

INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (3,TO_DATE('2023-05-10', 'YYYY-MM-DD'), 'CUST003', 3, '18:30', 'Pending', 2799, 'DIS003');

INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (4, TO_DATE('2023-05-13', 'YYYY-MM-DD'), 'CUST004', 4, '19:30', 'Completed', 7599, 'DIS004');

INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (5, TO_DATE('2023-05-13', 'YYYY-MM-DD'), 'CUST005', 5, '20:00', 'Pending', 10000, 'DIS005');

INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (6, TO_DATE('2023-05-03', 'YYYY-MM-DD'), 'CUST006', 6, '19:30', 'Pending', 5000, 'DIS006');

INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (7, TO_DATE('2023-07-10', 'YYYY-MM-DD'), 'CUST007', 7, '20:00', 'Completed', 4000, 'DIS007');

INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (8,TO_DATE('2023-05-10', 'YYYY-MM-DD'), 'CUST008', 8, '18:30', 'Completed', 6000, 'DIS008');

INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (9, TO_DATE('2023-05-13', 'YYYY-MM-DD'), 'CUST009', 9, '19:30', 'Completed', 3000, 'DIS009');

INSERT INTO Orders (Order_id, Order_date, Customer_id, Reservation_id, Order_time, Status, Total_amount, Discount_id)
VALUES (10, TO_DATE('2023-05-13', 'YYYY-MM-DD'), 'CUST010', 10, '20:00', 'Pending', 6099, 'DIS010');


--insert data in Payment table
INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (1, 'Credit Card', 1);

INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (2, 'Cash', 2);

INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (3, 'Debit Card', 3);

INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (4, 'Cash', 4);

INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (5, 'Credit Card', 5);

INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (6, 'Credit Card', 6);

INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (7, 'Cash', 7);

INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (8, 'Debit Card', 8);

INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (9, 'PayPal', 9);

INSERT INTO Payment (Pay_id, Method, Order_id)
VALUES (10, 'Gift Card', 10);


--insert data in OrderItems table
INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (1, 1, 1, 2, 'Extra cheese');

INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (2, 2, 2, 1, 'No onions');

INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (3, 3, 3, 2, 'Extra sauce');

INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (4, 4, 4, 3, 'Spicy');

INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (5, 5, 5, 1, 'No ice');

INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (6, 6, 6, 2, 'No onions');

INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (7, 7, 7, 3, 'Extra sauce');

INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (8, 8, 8, 1, 'No tomatoes');

INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (9, 9, 9, 4, 'Extra cheese');

INSERT INTO OrderItem (Order_item_id, Order_id, Item_id, Quantity, Special_requests)
VALUES (10, 10, 10, 2, 'Gluten-free bread');


--insert data in Staff table
INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (1, 'Faseeh', 'Manager', 5000);

INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (2, 'Jane', 'Chef', 4000);

INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (3, 'David', 'Waiter', 3000);

INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (4, 'Sajjad', 'Accountant', 2000);

INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (5, 'Michael', 'Bartender', 3500);

INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (6, 'Sarah', 'Waitress', 2800);

INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (7, 'Ahmad', 'Waiter', 2200);

INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (8, 'JSmith', 'Waiterss', 2400);

INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (9, 'Ali', 'Chef', 4000);

INSERT INTO Staff (Staff_id, Name, Position, Salary)
VALUES (10, 'Anderson', 'Bartender', 3000);


--insert data in Delivery table
INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (1, 1, DATE '2023-06-01', 1, 'Delivered', 'Area 1', 'Address 1');

INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (2, 2, DATE '2023-06-02', 2, 'In Progress', 'Area 2', 'Address 2');

INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (3, 3, DATE '2023-06-03', 3, 'Scheduled', 'Area 1', 'Address 3');

INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (4, 4, DATE '2023-06-04', 4, 'In Progress', 'Area 2', 'Address 4');

INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (5, 5, DATE '2023-06-05', 2, 'Delivered', 'Area 1', 'Address 5');

INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (6, 6, DATE '2023-06-06', 1, 'Scheduled', 'Area 2', 'Address 6');

INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (7, 7, DATE '2023-06-07', 3, 'In Progress', 'Area 1', 'Address 7');

INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (8, 8, DATE '2023-06-08', 4, 'Delivered', 'Area 2', 'Address 8');

INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (9, 9, DATE '2023-06-09', 2, 'In Progress', 'Area 2', 'Address 9');

INSERT INTO Delivery (Delivery_id, Order_id, Delivery_date, Staff_id, Status, Area, Address)
VALUES (10, 10, DATE '2023-06-10', 3, 'Scheduled', 'Area 3', 'Address 10');
                        
                        ------------------------------------------------------

            ---------   ADVANCE QURIES  ---------

--1. To calculate the revenue of each branch of the restaurant

SELECT R.Restaurant_id, R.Location, SUM(O.Total_amount) AS Revenue
FROM Restaurant R
JOIN Reservations RS ON R.Restaurant_id = RS.Restaurant_id
JOIN Orders O ON RS.Reservation_id = O.Reservation_id
GROUP BY R.Restaurant_id, R.Location;

--2. to calculate the expired items in stock,
SELECT *
FROM ExpiryItems
JOIN Stock ON ExpiryItems.Stock_id = Stock.Stock_id
WHERE ExpiryItems.Expiration_date <= CURRENT_DATE;

--3. To update the stock after deleting expired items
UPDATE Stock
SET Quantity = Quantity - (
    SELECT COUNT(*)
    FROM ExpiryItems
    WHERE ExpiryItems.Stock_id = Stock.Stock_id
        AND ExpiryItems.Expiration_date <= CURRENT_DATE
);

SELECT * FROM Stock;

--4. revenue 
SELECT r.Location, SUM(o.Total_amount) AS Total_Revenue
FROM Restaurant r
JOIN Reservations res ON r.Restaurant_id = res.Restaurant_id
JOIN Orders o ON res.Reservation_id = o.Reservation_id
GROUP BY r.Location;

--5. Calculate most order dishes
SELECT Item_id, Name, OrderCount
FROM (
  SELECT oi.Item_id, im.Name, COUNT(*) AS OrderCount
  FROM OrderItem oi
  JOIN ItemMenu im ON oi.Item_id = im.Item_id
  GROUP BY oi.Item_id, im.Name
  ORDER BY OrderCount DESC
) WHERE ROWNUM <= 5;


--6. Most popular dish
SELECT Dish, OrderCount
FROM (
  SELECT im.Name AS Dish, COUNT(*) AS OrderCount
  FROM OrderItem oi
  JOIN ItemMenu im ON oi.Item_id = im.Item_id
  GROUP BY oi.Item_id, im.Name
  ORDER BY OrderCount DESC
) WHERE ROWNUM = 1;


--7. Most delivered area
SELECT Area, DeliveryCount
FROM (
    SELECT Area, COUNT(*) AS DeliveryCount
    FROM Delivery
    GROUP BY Area
    ORDER BY COUNT(*) DESC
) WHERE ROWNUM = 1;


--8. retrieves the data for the total revenue generated by each restaurant on on a specific day:
SELECT r.Location, o.Order_date, SUM(o.Total_amount) AS Total_revenue
FROM Restaurant r
JOIN Reservations rs ON r.Restaurant_id = rs.Restaurant_id
JOIN Orders o ON rs.Reservation_id = o.Reservation_id
WHERE o.Order_date = TO_DATE('2023-05-10', 'YYYY-MM-DD')
GROUP BY r.Restaurant_id, r.Location, o.Order_date;

--9. Retrieve the total number of reservations made at each restaurant_branch:

SELECT r.Location, COUNT(*) AS Total_Reservations
FROM Restaurant r
JOIN Reservations res ON r.Restaurant_id = res.Restaurant_id
GROUP BY r.Location;


--10. Retrieve the details of orders along with the corresponding customer name, item name, and category name

SELECT o.Order_id, c.Customer_name, im.Name AS Item_Name, cat.Name AS Category_Name
FROM Orders o
JOIN Customer c ON o.Customer_id = c.Customer_id
JOIN OrderItem oi ON o.Order_id = oi.Order_id
JOIN ItemMenu im ON oi.Item_id = im.Item_id
JOIN Category cat ON im.Category_id = cat.Category_id;

--11. add stock in expiry item when that is out of date
INSERT INTO ExpiryItems (Stock_id, Expiration_date)
SELECT s.Stock_id, SYSDATE + INTERVAL '1' YEAR AS Expiration_date
FROM Stock s
WHERE s.Stock_id NOT IN (
    SELECT Stock_id
    FROM ExpiryItems
    WHERE Expiration_date > SYSDATE
);



--12. Get the details of all orders placed by a specific customer:

SELECT o.Order_id, o.Order_date, o.Order_time, i.Name AS Item_Name, oi.Quantity
FROM Orders o
JOIN OrderItem oi ON o.Order_id = oi.Order_id
JOIN ItemMenu i ON oi.Item_id = i.Item_id
WHERE o.Customer_id = 'CUST001';

--13. inset items from stock to expiryitems that are out of date:
INSERT INTO ExpiryItems (Stock_id, Expiration_date, Quantity)
SELECT s.Stock_id, CURRENT_DATE, :quantity
FROM Stock s
WHERE s.Item_id = :item_id
  AND s.Stock_id NOT IN (
    SELECT ei.Stock_id
    FROM ExpiryItems ei
    WHERE ei.Expiration_date >= CURRENT_DATE
  );


--14.Find the items that have expired in stock:
SELECT i.Name, e.Expiration_date
FROM ItemMenu i
JOIN Stock s ON i.Item_id = s.Item_id
JOIN ExpiryItems e ON s.Stock_id = e.Stock_id
WHERE e.Expiration_date < CURRENT_DATE;

--15. Find all the reservations made by customers with a Gold membership:
SELECT r.Reservation_id, r.Customer_id, c.Customer_name, c.Membership_id
FROM Reservations r
JOIN Customer c ON r.Customer_id = c.Customer_id
WHERE c.Membership_id = (
    SELECT MAX(Membership_id)
    FROM Membership
    WHERE Membership_Type = 'Gold'
);

--16. ??Get the total number of reservations made on a specific date:
SELECT COUNT(*) AS Total_Reservations
FROM Reservations
WHERE Date_of_Reservation = TIMESTAMP '2023-06-06 17:30:00';


--17. Retrieve the orders placed by a specific customer with their respective items and quantities:
SELECT o.Order_id, o.Order_date, i.Name AS Item, oi.Quantity
FROM Orders o
JOIN Customer c ON o.Customer_id = c.Customer_id
JOIN OrderItem oi ON o.Order_id = oi.Order_id
JOIN ItemMenu i ON oi.Item_id = i.Item_id
WHERE c.Customer_name = 'Faseeh';

--18. Retrieve the total quantity and cost of stock for each item:
SELECT s.Item_id, SUM(s.Quantity) AS Total_Quantity, SUM(s.Quantity * s.Unit_cost) AS Total_Cost
FROM Stock s
GROUP BY s.Item_id;

--19. 
SELECT r.Restaurant_id, TO_CHAR(o.Order_date, 'MM') AS Month, SUM(o.Total_amount) AS Total_Revenue
FROM Restaurant r
JOIN Reservations res ON r.Restaurant_id = res.Restaurant_id
JOIN Orders o ON res.Reservation_id = o.Reservation_id
GROUP BY r.Restaurant_id, TO_CHAR(o.Order_date, 'MM');

--20.Retrieve the total revenue generated by each restaurant_branch:
SELECT r.Location, SUM(o.Total_amount) AS Total_Revenue
FROM Restaurant1 r
JOIN Reservations res ON r.Restaurant_id = res.Restaurant_id
JOIN Orders o ON res.Reservation_id = o.Reservation_id
GROUP BY r.Location;

--21. Retrieve the total number of orders and the average total amount for each customer
SELECT c.Customer_name, 
       (SELECT COUNT(*) FROM Orders o WHERE o.Customer_id = c.Customer_id) AS Total_Orders,
       (SELECT AVG(o.Total_amount) FROM Orders o WHERE o.Customer_id = c.Customer_id) AS Average_Total_Amount
FROM Customer c;

--22. Get the details of all the items in a specific category:
SELECT i.Item_id, i.Name, i.Price, i.Description, c.Name AS Category
FROM ItemMenu i
JOIN Category c ON i.Category_id = c.Category_id
WHERE c.Name = 'Main Course';

--23. Retrieve the names of customers who have placed at least two orders:
SELECT Customer_name
FROM Customer
WHERE Customer_id IN (
    SELECT Customer_id
    FROM Orders
    GROUP BY Customer_id
    HAVING COUNT(*) >= 2
);

--24. Retrieve the names of customers who have placed orders on the same day as customer 'Sajjad':
SELECT c.Customer_name
FROM Orders o1
JOIN Customer c ON o1.Customer_id = c.Customer_id
WHERE o1.Order_date IN (
    SELECT o2.Order_date
    FROM Orders o2
    JOIN Customer c2 ON o2.Customer_id = c2.Customer_id
    WHERE c2.Customer_name = 'Ali'
);

--25. Retrieve the total amount and payment method for each completed order:
SELECT o.Order_id, o.Total_amount, p.Method
FROM Orders o
JOIN Payment p ON o.Order_id = p.Order_id
WHERE o.Status = 'Completed';

--26. Retrieve all the restaurants along with their capacity and total staff count:

SELECT r.Restaurant_id, r.Capacity, r.Total_Staff
FROM Restaurant1 r;

--27. Retrieve all the items in the menu along with their prices and categories:
SELECT i.Item_id, i.Name, i.Price, c.Name AS Category
FROM ItemMenu i
JOIN Category c ON i.Category_id = c.Category_id;
--28. Retrieve the total quantity and cost of stock for each item:
SELECT s.Item_id, SUM(s.Quantity) AS Total_Quantity, SUM(s.Quantity * s.Unit_cost) AS Total_Cost
FROM Stock s
GROUP BY s.Item_id;

--29. Retrieve the reservations made by a specific customer:
SELECT r.Reservation_id, r.Date_of_Reservation, r.Time, r.Total_persons, res.Name AS Restaurant, t.Table_Number
FROM Reservations r
JOIN Customer c ON r.Customer_id = c.Customer_id
JOIN Restaurant1 res ON r.Restaurant_id = res.Restaurant_id
JOIN Tables t ON r.Table_id = t.Table_id
WHERE c.Customer_name = 'John Smith';

--30. Retrieve the total revenue generated by each category:
SELECT c.Name AS Category, SUM(oi.Quantity * i.Price) AS Revenue
FROM Orders o
JOIN OrderItem oi ON o.Order_id = oi.Order_id
JOIN ItemMenu i ON oi.Item_id = i.Item_id
JOIN Category c ON i.Category_id = c.Category_id
GROUP BY c.Name;

--31.	Retrieve the deliveries completed by a specific staff member:
SELECT d.Delivery_id, d.Delivery_date, o.Order_id, o.Order_time, o.Total_amount
FROM Delivery d
JOIN Orders o ON d.Order_id = o.Order_id
JOIN Staff s ON d.Staff_id = s.Staff_id
WHERE s.Name = 'David Johnson' AND d.Status = 'Completed';

--32. Retrieve the staff members (name, position) and their corresponding restaurant locations:
SELECT s.Name, s.Position, r.Location
FROM Staff s
JOIN Restaurant1 r ON s.Staff_id = r.Total_Staff;

--33. Retrieve the total revenue for each restaurant along with its location:
SELECT r.Restaurant_id, r.Location, SUM(o.Total_amount) AS Total_Revenue
FROM Orders o
JOIN Restaurant1 r ON o.Restaurant_id = r.Restaurant_id
GROUP BY r.Restaurant_id, r.Location;

--34. Retrieve the order details (order ID, item name, quantity) for all orders placed by customers with a "Gold" membership:
SELECT o.Order_id, i.Name, oi.Quantity
FROM Orders o
JOIN Customer c ON o.Customer_id = c.Customer_id
JOIN OrderItem oi ON o.Order_id = oi.Order_id
JOIN ItemMenu i ON oi.Item_id = i.Item_id
JOIN Membership m ON c.Membership_id = m.Membership_id
WHERE m.Membership_Type = 'Gold';

--35. to calculate the revenue of each restaurant
SELECT
    r.restaurant_id,
    r.restaurant_name,
    SUM(o.total_amount) AS revenue
FROM
    restaurants r
    INNER JOIN orders o ON r.restaurant_id = o.restaurant_id
GROUP BY
    r.restaurant_id,
    r.restaurant_name;

--36. Retrieve the names of all customers who have made an order:
SELECT Customer_name
FROM Customer
WHERE Customer_id IN (SELECT DISTINCT Customer_id FROM Orders);

--37. Retrieve the total amount of each order along with the corresponding customer name:
SELECT o.Order_id, o.Total_amount, c.Customer_name
FROM Orders o
JOIN Customer c ON o.Customer_id = c.Customer_id;

--38. Retrieve the names of all items in the "Main Course" category:
SELECT Name
FROM ItemMenu
WHERE Category_id = (SELECT Category_id FROM Category WHERE Name = 'Main Course');

--39. Retrieve the total quantity of each item in stock along with its name:
SELECT s.Item_id, i.Name, SUM(s.Quantity) AS Total_Quantity
FROM Stock s
JOIN ItemMenu i ON s.Item_id = i.Item_id
GROUP BY s.Item_id, i.Name;

--40. Retrieve the names of all customers who have placed an order and their corresponding membership types:
SELECT c.Customer_name, m.Membership_Type
FROM Customer c
JOIN Membership m ON c.Membership_id = m.Membership_id
WHERE c.Customer_id IN (SELECT DISTINCT Customer_id FROM Orders);

--41. Retrieve the total number of reservations made for each restaurant:
SELECT r.Restaurant_id, r.Location, COUNT(*) AS Total_Reservations
FROM Reservations r
JOIN Restaurant1 res ON r.Restaurant_id = res.Restaurant_id
GROUP BY r.Restaurant_id, r.Location;

--42. Retrieve the order details (order ID, customer name, item name) for all completed orders:
SELECT o.Order_id, c.Customer_name, i.Name
FROM Orders o
JOIN Customer c ON o.Customer_id = c.Customer_id
JOIN OrderItem oi ON o.Order_id = oi.Order_id
JOIN ItemMenu i ON oi.Item_id = i.Item_id
WHERE o.Status = 'Completed';