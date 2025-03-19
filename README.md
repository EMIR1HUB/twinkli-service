## Сборка Docker образа

1. Билд образа
```shell
docker build -t twinkli-service .
```
2. Запуск
```shell
docker run -d -p 8080:8080 --name twinkli-service twinkli-service
```
