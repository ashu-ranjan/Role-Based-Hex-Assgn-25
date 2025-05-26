CREATE DATABASE assignment_1;
USE assignment_1;

CREATE TABLE Book (
    id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publication_house VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    book_count INT DEFAULT 0 CHECK (book_count >= 0),
    status VARCHAR(255)
);


INSERT INTO Book (id, title, price, author, publication_house, category, book_count, status)
VALUES 
(1, 'War and Peace', 599.99, 'Leo Tolstoy', 'Mcgraw Hill', 'WAR', 10, 'IN STOCK'),
(2, 'The Comedy of Errors', 299.50, 'William Shakespeare', 'DreamFolks', 'COMEDY', 5, 'IN STOCK'),
(3, 'Sports Legends', 399.00, 'John Smith', 'Warner Bros', 'SPORTS', 3, 'OUT OF STOCK'),
(4, 'Fictional World', 199.99, 'Jane Doe', 'Mcgraw Hill', 'Fiction', 8, 'IN STOCK'),
(5, 'Battlefield Diaries', 450.00, 'George Martin', 'DreamFolks', 'WAR', 0, 'OUT OF STOCK'),
(6, 'Laugh Out Loud', 150.75, 'Ellen Hart', 'Warner Bros', 'COMEDY', 7, 'IN STOCK'),
(7, 'Game On', 349.00, 'Mark Taylor', 'Mcgraw Hill', 'SPORTS', 6, 'IN STOCK'),
(8, 'Mystery Realm', 275.25, 'Agatha Christie', 'DreamFolks', 'Fiction', 4, 'OUT OF STOCK');

-- 1. Fetch all books that are "IN STOCK" and price is less than give value.

DELIMITER $$
CREATE PROCEDURE books_in_stock(IN p_price DECIMAL(10,2))
BEGIN
	SELECT * FROM book
    WHERE status = 'IN STOCK' AND price < p_price;
END ;

CALL books_in_stock(300.00);

-- 2.Delete books that are from given publication_house.

DELIMITER $$
CREATE PROCEDURE delete_books_by_publisher(IN p_pub_house VARCHAR(255))
BEGIN
    DECLARE book_id INT;
    
    SELECT id INTO book_id
    FROM book 
    WHERE publication_house = p_pub_house
    LIMIT 1;
    
    WHILE book_id IS NOT NULL DO
    
		DELETE FROM book WHERE id = book_id;
        
		SELECT id INTO book_id
        FROM book 
        WHERE publication_house = p_pub_house
        LIMIT 1;
        
	END WHILE;
END ;

CALL delete_books_by_publisher('DreamFolks');

-- 3.update the price of books by given percent based on given category.

DELIMITER $$
CREATE PROCEDURE update_price_based_on_category(IN p_category VARCHAR(255), p_incr DOUBLE)
BEGIN
	DECLARE num_rows INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE p_id INT DEFAULT 0;
    
    SELECT COUNT(id) INTO num_rows 
    FROM book
    WHERE category = p_category;
    
    WHILE i < num_rows DO
		SELECT id INTO p_id
        FROM book
        WHERE category = p_category
        LIMIT i,1;
        
        UPDATE book
        SET price = price + (price * (p_incr / 100))
        WHERE id = p_id;
        
        SET i = i+1;
        
    END WHILE;
END ;

CALL update_price_based_on_category('COMEDY', 10);





