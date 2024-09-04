1. Из корневого каталога проекта запустить PostgreSQL из контейнера:
   docker compose up -d
2. Импортировать в Postman файл TestTask.postman_collection из каталога проекта
3. OpenAPI: http://localhost:8080/swagger-ui/index.html
     CRUD модель для работы с контактной информацией:
        methods for contact info
     CRUD модель для работы с детальной информацией о пользователе с фотографией и без:
        methods for customers
      фотографии сохраняются локально в папке photos
