Segue o para avalição.

Para subir o projeto basta rodar o comando docker
-- docker compose up

- Primeiro crie a wishlist com o id do cliente
  POST - http://localhost:8080/api/wishlist
  {
  "customerId": ""
  }

- Adicione um produto a lista
  PATCH -  http://localhost:8080/api/wishlist/{wishlistId}/product

- Remova um produto da lista
  PATCH -  http://localhost:8080/api/wishlist/{wishlistId}/product/{productId}/remove

- Filtre um produto ou receba uma lista completa
  GET -http://localhost:8080/api/wishlist/{wishlistId}?product_id={productId}

Atenciosamente Raul Alves de Oliveira