# Car Selling Service Example App

[![Build Status](https://travis-ci.org/riezenmark/car_selling_service.svg?branch=master)](https://travis-ci.org/riezenmark/car_selling_service)
[![codecov](https://codecov.io/gh/riezenmark/car_selling_service/branch/master/graph/badge.svg)](https://codecov.io/gh/riezenmark/car_selling_service)

Пример web-приложения для продажи автомобилей.

В приложении реализованы функции добавления, изменеия, удаления и поиска автомобилей.
Добавлена авторизация с помощью сервисов Google и панель администратора.
При авторизации сохраняет сессию пользователя с помощью Spring Session JDBC.
Приложение собирается с помощью Gradle, предустанавливает Node на prod сервер.
Запускается с помощью Spring Boot, хранит данные в базе данных Postgres.
При миграции создаёт в базе данных таблицы и заполняет их первичными данными с помощью sql-скриптов и библиотеки flywaydb.
При разработке использован Project Lombok.

Авторизация с помощью Google.
![Авторизация.](screenshots/authorization.jpg)

Главная страница.
![Поиск авто.](screenshots/car_search.jpg "Поиск авто по параметрам.")

Панель управления пользователями.
![Поиск пользователей](screenshots/user_search.jpg "Поиск пользователей на станице администратора.")

Панель управления марками и моделями машин.
![Управление моделями](screenshots/model_crud.jpg "Управление моделями машин.")
