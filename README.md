# Сервис для системы пропусков
Сервис реализует интерфейс допуска  
http://localhost:8080/check?roomId=1&entrance=true&keyId=1  
roomId - номер комнаты  
keyId - идентификатор пользователя  
entrance - true вход, false выход  

### Ответы
200 - дверь можно открыть  
403 - запрет на вход  
500 ошибка  

### Сборка
Для сборки docker image:
```java
 mvn clean install -P buildDocker
```
Будет создан image с наименованием  
```java
 cheparin/demo/control-system
```
### Запуск
```java
 docker run -e DB_URL=<Database URL> cheparin/demo/control-system
```
### Переменные окружения
DB_URL - url базы данных  
DB_USERNAME - пользователь базы  
DB_PASSWORD - пароль пользователя базы  
USERS_MAX - максимальное количество пользователей (по умолчанию 5)  
ROOMS_MAX - максимальное количество комнат (по умолчанию 10000)

### Swagger
При запуске swagger схема будет доступна по url:  
http://localhost:8080/swagger-ui.html
