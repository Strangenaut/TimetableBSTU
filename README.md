# Android-приложение расписания университета

Это Android-приложение, которое подключается к веб-сайту университета БГТУ (СПб) и загружает расписание с помощью HTTP-запроса. Затем расписание отображается внутри приложения для Android. Для реализации проекта были использованы такие технологии, как Kotlin, Jetpack Compose, Dagger-Hilt, Retrofit2, Coroutines, MVVM и Clean Architecture.

## Возможности

- **Получение расписания**: Приложение устанавливает HTTP-соединение с веб-сайтом университета с помощью Retrofit2 для загрузки данных расписания.
- **Отображение расписания**: Загруженное расписание представляется пользователю в удобном формате с помощью Jetpack Compose, обеспечивая простую навигацию и взаимодействие.
- **Синхронизация**: Приложение предоставляет возможность периодической синхронизации расписания, гарантируя, что у пользователя есть актуальная информация о расписании.
- **Обработка ошибок**: Приложение обрабатывает различные сценарии ошибок, такие как сбои сети, недоступность сервера и неверные данные, предоставляя соответствующие сообщения об ошибке и запасные варианты.

## Архитектура и шаблоны проектирования

Проект следует принципам Clean Architecture и использует архитектурный паттерн MVVM, обеспечивая четкое разделение ответственностей и способствуя тестированию и поддержке проекта. Dagger-Hilt используется для внедрения зависимостей, обеспечивая модульный и масштабируемый подход к управлению зависимостями. Coroutines применяются для обработки асинхронных операций и обеспечения отзывчивого пользовательского интерфейса.

## Скриншоты работы
### Экран ожидания ответа сервера
![Экран оиждания ответа сервера](https://user-images.githubusercontent.com/98609700/231455809-24686fc8-e910-40f0-be1f-af8da022df03.png)
### Экран предложения выбрать группу
![Экран предложения выбрать группу ](https://user-images.githubusercontent.com/98609700/231455880-6ed43df1-a5e5-44bb-a207-1daebe3c0357.png)
### Экран выбора группы
![Экран выбора группы](https://user-images.githubusercontent.com/98609700/231455980-34a95db8-6870-4e41-8d79-e7c2ae248ea1.png)
### Загруженное раписание
![Загруженное раписание](https://user-images.githubusercontent.com/98609700/231456065-269fae04-603a-473b-9b9d-040e5cc44335.png)
![Загруженное расписание отфильтрованное](https://user-images.githubusercontent.com/98609700/231456070-40529ff9-7318-4515-bf28-d3a004b4c6bd.png)
### Тёмная тема
![Загруженное расписание (тёмная тема)](https://user-images.githubusercontent.com/98609700/231456170-c65e6c12-1674-4a6f-9faf-b4fa1c63ecfd.png)
![Загруженное расписание отфильтрованное (тёмная тема)](https://user-images.githubusercontent.com/98609700/231456150-957e0612-16a4-42e1-9e9b-953b12e310ab.png)
![Экран выбора группы (тёмная тема)](https://user-images.githubusercontent.com/98609700/231456206-dd411808-b3e6-4331-986d-28ec7c90c735.png)
### Экран загрузки
![Экран загрузки приложения](https://user-images.githubusercontent.com/98609700/231458259-ba4e704a-dc78-4aa1-9ad7-ab53e870cf65.png)
