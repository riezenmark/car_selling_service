# Car Selling Service Example App

[![Build Status](https://travis-ci.org/riezenmark/car_selling_service.svg?branch=master)](https://travis-ci.org/riezenmark/car_selling_service)
[![codecov](https://codecov.io/gh/riezenmark/car_selling_service/branch/master/graph/badge.svg)](https://codecov.io/gh/riezenmark/car_selling_service)

https://car-selling-service.herokuapp.com

Пример web-приложения для продажи автомобилей.

В приложении реализованы функции добавления, изменеия, удаления и поиска автомобилей.
Добавлена авторизация и панель администратора.
При авторизации сохраняет сессию пользователя с помощью Spring Session JDBC.
Приложение собирается с помощью Maven.
Запускается с помощью Spring Boot, хранит данные в базе данных Postgres.
При миграции создаёт в базе данных таблицы и заполняет их первичными данными с помощью sql-скриптов и библиотеки flywaydb.
При разработке использован Project Lombok.
Все запросы к базе данных оптимизированы посредством использования Criteria API, JPA Specification и Entity Graph.
Присутствует docker-compose файл, собирающий приложение из репозитория. Front, back и база данных находятся в отдельных контейнерах.

Пример создан исключительно с целью демонстрации различных подходов в разработке.
