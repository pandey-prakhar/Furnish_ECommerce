# **E-commerce Platform**

## **Overview**
This e-commerce platform enables users to browse, purchase products, and manage orders efficiently. The platform features a microservices-based architecture, ensuring scalability, maintainability, and high availability. The following sections outline the platform’s functional and technical specifications.

## **Functional Requirements**

### **1. User Management**
- **Registration:** Users can sign up using email or social media profiles.
- **Login:** Secure user authentication via email or social profiles.
- **Profile Management:** Users can view and update their personal information.
- **Password Reset:** Password recovery through secure links.

### **2. Product Catalog**
- **Browsing:** View products categorized by different types.
- **Product Details:** Detailed product pages with images, specifications, and descriptions.
- **Search:** Fast, full-text product search using Elasticsearch.

### **3. Cart & Checkout**
- **Add to Cart:** Easily add products to a shopping cart.
- **Cart Review:** Review cart contents, quantities, and prices.
- **Checkout:** Seamless purchasing with options for delivery address and payment methods.

### **4. Order Management**
- **Order Confirmation:** Users receive an order confirmation post-purchase.
- **Order History:** Track and view past orders.
- **Order Tracking:** Live tracking of order delivery status.

### **5. Payment**
- **Multiple Payment Options:** Support for various payment methods like credit/debit cards, online banking, etc.
- **Secure Transactions:** Ensured through secure payment gateways.
- **Payment Receipt:** Users receive a receipt post-payment.

### **6. Authentication**
- **Secure Authentication:** User sessions are secured from login to logout.
- **Session Management:** Automatic session expiration or manual log out functionality.

## **High-Level Design (HLD)**

### **1. Architecture Components**
- **Load Balancers (LB):** Handles incoming user requests by distributing them across server instances.
- **API Gateway:** Routes requests, manages authentication, and handles rate limiting.
- **Microservices:** Each feature is isolated into independent microservices (user management, product catalog, cart, order management, payment, notifications).
- **Databases:**
  - **MySQL:** Used for structured data such as user info, orders, and payments.
  - **MongoDB:** Stores flexible, unstructured data like cart details.
- **Message Broker (Kafka):** Facilitates asynchronous communication between microservices, ensuring consistency.
- **Caching (Redis):** Enhances performance, especially in cart management.
- **Search and Analytics (Elasticsearch):** Ensures fast and relevant product search capabilities.

### **2. Microservices Breakdown**

#### **a. User Management Service**
Handles user registration, login, profile, and password resets using MySQL. It also communicates with other services (e.g., sends notifications after successful registration) via Kafka.

#### **b. Product Catalog Service**
Manages product listings and categories. Uses MySQL and Elasticsearch for real-time search capabilities, enabling features like typo correction and full-text search.

#### **c. Cart Service**
Manages user carts using MongoDB for flexibility and Redis for in-memory caching to improve performance.

#### **d. Order Management Service**
Manages order lifecycle including processing, history, and tracking. Communicates with the Payment Service and User Management Service through Kafka for order status updates.

#### **e. Payment Service**
Handles transactions using MySQL and communicates with the Order Management Service via Kafka upon successful payments.

#### **f. Notification Service**
Manages email and SMS notifications. It listens for Kafka events such as user registrations and order updates, and triggers notifications using Amazon SES.

### **3. Data Flow Example**
1. **Search and Add to Cart:**
   - User logs in and searches for a product. The API Gateway routes this to the Product Catalog Service, which queries Elasticsearch for fast results.
   - After selecting a product, the Cart Service adds the item to the user's cart and sends a message to Kafka about the action.
   
2. **Checkout and Order Processing:**
   - Upon checkout, the Order Management Service receives the order details and sends a message to Kafka. The Payment Service processes the payment and communicates the result back to the Order Management Service, which triggers an order confirmation and tracking status.

## **Tools & Technologies**
- **API Gateway:** Kong
- **Load Balancer:** Amazon Elastic Load Balancer (ELB)
- **Message Broker:** Kafka
- **Databases:** MySQL (structured data), MongoDB (unstructured data)
- **Caching:** Redis
- **Search:** Elasticsearch
- **Notification Service:** Amazon SES
- **Containerization:** Docker for microservices
- **Cloud Platform:** AWS (Elastic Load Balancer, SES, RDS)

Here are the updated installation steps for your Java Spring Boot backend application:

markdown
Copy code
## **Installation**

1. Clone the repository:
   ```bash
   git clone https://github.com/pandey-prakhar/Furnish_ECommerce.git

2. Navigate to the project directory:
   ```bash
   cd Furnish_ECommerce
3. Build the project using Maven:
   ```bash
   mvn clean install
4. Run the application:
   ```bash
   mvn spring-boot:run
   
The application will be accessible at:
   http://localhost:8080
