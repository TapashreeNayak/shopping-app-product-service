# ShopWise Product Service

Spring Boot product catalog microservice using Java 21, Spring Boot 3.4.5, Spring Data JPA, Oracle JDBC, Flyway and Actuator.

## Start Oracle locally
Requires Docker Desktop / Docker Engine:
```bash
docker compose -f docker-compose.oracle.yml up -d
```
This starts Oracle Free with PDB `FREEPDB1` and creates the local `SHOPWISE` app user. These credentials are for disposable local development only.

## Run the service
Requires JDK 21 and Maven 3.9+:
```bash
mvn spring-boot:run
```
Flyway creates the `PRODUCTS` table and seeds eight demo products. Hibernate is set to `ddl-auto: validate`; schema changes belong in versioned Flyway scripts.

Endpoints: `GET http://localhost:8081/actuator/health`, `GET http://localhost:8081/api/products`, `GET /api/products?category=Electronics`, `GET /api/products?search=watch`, `GET /api/products/{id}`.

## CRUD API
- `POST /api/products` create
- `PUT /api/products/{id}` update
- `DELETE /api/products/{id}` soft-deactivate (ACTIVE=0)

Example create request:
```json
{
  "sku":"ELEC-2001", "name":"Bluetooth speaker", "category":"Electronics",
  "description":"Portable wireless speaker", "price":179.00, "rating":4.5,
  "badge":"NEW", "imageUrl":"https://example.com/speaker.jpg", "active":true
}
```

## Connect Node BFF
In `../bff/.env` configure:
```env
PORT=4000
PRODUCT_SERVICE_URL=http://localhost:8081
USE_MOCK_FALLBACK=false
```
Start Product Service then from the project root run `npm run dev`. React -> Vite proxy -> Node BFF -> Spring Boot -> Oracle. Set fallback true when working on UI without Java running.

## Notes
The API response contains the frontend fields (`id`, `name`, `category`, `description`, `price`, `rating`, `badge`, `imageUrl`) plus `sku` and `active`. Oracle is accessed only by this service, never by React. Product catalog reads remain REST; Kafka is better introduced for domain events in the order/inventory workflow. Before production, add authz, audit history, secret management, rate limits, observability and explicit currency codes.
