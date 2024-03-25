# Project Overview

This project is a modular Android application designed to search images efficiently using Kotlin and Clean Architecture principles. It comprises various modules structured to ensure maintainability, scalability, and ease of development.

## Modules

1. **Core:**
    - **Network:** Configures network connections using Retrofit.
    - **Database:** Manages data storage, including caching images for offline usage.
    - **SystemDesign:** Sets up the application theme and common UI components.
    - **Utility:** Contains base classes for the application.

2. **SearchImage:**
    - Facilitates image search functionality using Jetpack Compose for UI rendering.
    - Implements pagination with Paging 3 to load images incrementally.

## Technology Stack

- **Dependency Injection:** Utilizes Hilt for dependency injection.
- **Networking:** Retrofit for handling network requests.
- **UI Development:** Implements Jetpack Compose for building UI components.
- **Database:** Room for local data storage and caching.
- **Pagination:** Utilizes Paging 3 library for loading images page by page.

## Features

- **Modular Architecture:** Promotes code reusability and separation of concerns.
- **Clean Architecture:** Ensures a clear separation of business logic from presentation and data layers.
- **Offline Support:** Caches images in the database for seamless offline handling.
- **Scalable UI:** Employs Jetpack Compose for flexible and dynamic UI development.
- **Efficient Data Loading:** Implements pagination with Paging 3 for efficient data loading and performance optimization.

## Search Image API

- Utilizes the Pixabay API (https://pixabay.com/api/docs/) for fetching images.
- Leverages the vast collection of high-quality images available on Pixabay for a rich user experience.
- Integrates seamlessly with the application to provide users with a diverse selection of images for their search queries.

## Unit Tests

- Developed comprehensive unit tests to ensure the reliability and stability of the application.
- Utilizes frameworks like JUnit, Turbine, and Mockito for writing and executing unit tests.
- Tests cover critical components including business logic, data handling, and UI interactions.
