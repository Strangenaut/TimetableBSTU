# University Timetable Android App

This is an Android app that resides on the BSTU (SPb) website and downloads the timetable via an HTTP request. The timetable is then displayed inside the Android app. The project was implemented using technologies such as Kotlin, Jetpack Compose, Dagger-Hilt, Retrofit2, Coroutines, MVVM, and Clean Architecture.

## Features

- **Getting a timetable**: The app establishes an HTTP connection to the university website using Retrofit2 to download the timetable data.
- **Displaying a timetable**: The downloaded timetable is presented to the user in a convenient format using Jetpack Compose, ensuring easy navigation and interaction.
- **Synchronization capability**: The app provides periodic synchronization of the timetable, ensuring that the user has up-to-date information about the timetable.
- **Error Handling**: Handle various error scenarios such as native network, server unavailability, and invalid data, providing appropriate Null and Fallback messages.

## Architecture and Design Patterns

The project follows the Clean Architecture principles and uses the MVVM architectural pattern, which provides a clear separation of responsibilities for testing and facilitates project management and maintenance. Dagger-Hilt is used for dependency evolution, implementing a modular and scalable approach to dependency management. Coroutines are used to handle asynchronous operations and provide a responsive user interface.

## Screenshots
### Choose a group
![Экран предложения выбрать группу](https://user-images.githubusercontent.com/98609700/231455880-6ed43df1-a5e5-44bb-a207-1daebe3c0357.png)
![Экран выбора группы](https://user-images.githubusercontent.com/98609700/231455980-34a95db8-6870-4e41-8d79-e7c2ae248ea1.png)
### Schedule downloaded
![Загруженное раписание](https://user-images.githubusercontent.com/98609700/231456065-269fae04-603a-473b-9b9d-040e5cc44335.png)
![Загруженное расписание отфильтрованное](https://user-images.githubusercontent.com/98609700/231456070-40529ff9-7318-4515-bf28-d3a004b4c6bd.png)
