CREATE TABLE category (
	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id)
);

INSERT INTO CATEGORY (name, description,image_url) values('Laptop', 'This is description for Laptop Category!','CAT_1.png');
INSERT INTO CATEGORY (name, description,image_url) values('Television', 'This is description for Television Category!','CAT_2.png');
INSERT INTO CATEGORY (name, description,image_url) values('Mobile', 'This is description for Mobile Category!','CAT_3.png');


CREATE TABLE USER_DETAIL (
	id IDENTITY,
	first_name varchar(50),
	last_name varchar(50),
	role varchar(50),
	enabled boolean,
	password varchar(50),
	email varchar(100),
	contact_number varchar(15),
	constraint pk_user_id primary key(id)
);

insert into USER_DETAIL (first_name,last_name,role,enabled,password,email ,contact_number) 
						values ('Deepak', 'Varghese' ,'ADMIN' ,true , 'admin', 'dv@gmail.com' , '7842812457' );

insert into USER_DETAIL (first_name,last_name,role,enabled,password,email ,contact_number) 
						values ('Anand', 'Kumar' ,'SUPPLIER' , true , '12345', 'ak@gmail.com' , '9948225074' );
						
insert into USER_DETAIL (first_name,last_name,role,enabled,password,email ,contact_number) 
						values ('Ravi', 'Varma' ,'SUPPLIER' , true , '12345', 'rv@gmail.com' , '9963656181' );

CREATE TABLE PRODUCT (
	id IDENTITY,
	code varchar(20),
	name varchar(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active boolean,
	category_id INT ,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id primary key (id),
	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category(id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id)
);

INSERT INTO PRODUCT (code , name , brand, description , unit_price , quantity , is_active , category_id , supplier_id , purchases , views)
		VALUES ('PRDACDF123', 'iPhone 5s','apple','This is the one of the best iphone available in the market',200,4,true,3,3,1,500 );
		
		

INSERT INTO PRODUCT (code , name , brand, description , unit_price , quantity , is_active , category_id , supplier_id , purchases , views)
		VALUES ('PRDACDF124', 'Samsung s7','samsung','A smart phone by samsung',300,5,true,3,2,2,250);
		


INSERT INTO PRODUCT (code , name , brand, description , unit_price , quantity , is_active , category_id , supplier_id , purchases , views)
		VALUES ('PRDACDF125', 'Google Pixel','google','This is the one of the best android smart phone  available',250,5,true,3,3,5,240 );
		

		
INSERT INTO PRODUCT (code , name , brand, description , unit_price , quantity , is_active , category_id , supplier_id , purchases , views)
		VALUES ('PRDACDF126', 'Macbook Pro','apple','This is the one of the best laptops available',700,6,true,1,2,15,200);
		

INSERT INTO PRODUCT (code , name , brand, description , unit_price , quantity , is_active , category_id , supplier_id , purchases , views)
		VALUES ('PRDACDF127', 'Dell Latitude E6510','dell','This is the one of the best laptop',750,10,true,2,2,20,350);

		

		