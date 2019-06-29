# Spring-Angular-CouponS
Full Stack Spring - Angular Project

Welcome to Pavel Nikoforov coupon system

Instructions:
Important - In order to initiate the web site, please install the Database in the SQL Server.
The ”application.properties” file encapsulate the entire system linking to the DB. The Logger level is set to : Debug.

The spring server will run on http 8080 port
The angular server will run on http 4200 port

Server structure:
The server comprises from several layers and entities.
Entities- Coupon, Company and Customer.

Auxiliary class -
A logged customer is not an entity but returns to the server as a logged customer.

Two ENUM Lists-
The first contains the USER type and the second contains the COUPON type.

Layers- 
The Repository layer - this layer connects to the DB by using custom queries and Spring queries.
The Service layer - constitutes the Business Logic of the Server.
The Controllers (Web Service) - constitutes the access point to the server and does not have any BL.
In the Controller layer, there is a Login Controller that constitutes the first stage in connecting to the Admin, Customer and Company.
The Filter class responsible for login inspection. After committing login, the filter will create a session and check if it exists.
If it does, the user will grant an access.

Auxiliary class-
Under this classes you will find the Validation class in which the service classes use.
In it, you’ll have validations methods.

Utility area-
The Utility class was formed in order to allow a not registered customer to see all coupons in different variations.
There is the service and the web service.


The Website Structure-
The site is composed from a default main page that includes links that routs to several places and three rows of eight coupons from different types.
You can surf the website and any of the coupon categories, even if you are not connected.
In order to buy coupons, you must be connected as a client!
In order to connect, you must press the Login button. Then the system will have several types of users . 
I will attach them in the end of the file.

After the login, the website will rout you to the designated personalo area. 
In the case of ADMIN login, the user will be routed to the personal account of the ADMIN,  there he’ll have a menu with the functions he can use such as ; creating companies and cutomers , updating them And so on.. 
In a case of COMPANY login, the user will be routed to the personal account of the COMPANY, there he’ll find a menu with all the functions he can use such as ; creating coupons , updating them And so on.. 
In case of CUSTOMER login, the user is routed to the main menu, where he can now purchase coupons from the main menu and from any of the categories. In an event the customer will want to go to his personal account, he will have to press the “personal area” button, there he will find a menu with all the functions he can use.
Each of the users can surf to his personal account and go back to the main page by pressing the website logo.

I’m attaching an ADMIN user:
ADMIN- name: admin
Password:1234
