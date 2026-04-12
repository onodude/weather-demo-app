# Weather App (iOS & Android)

A simple weather app to search for a city, view current conditions, and see a 5-day forecast.

---

## Setup

Add your API key in `AppConstants` before running the app.

---

## Known Issues

- Icons on iOS may appear slightly different compared to Android

---

## Features

- Welcome screen (shown once)
- Search by city
- Current weather
- 5-day forecast
- Error handling with retry
- Persist last searched city

---

## Architecture

The app uses a simple layered structure:

### UI (Screen / View)
- Screens handle navigation and lifecycle
- Views are stateless and render UI

### ViewModels
- Manage UI state:
  - `Idle`
  - `Loading`
  - `Success`
  - `Error`
- Handle user actions

### Repository
- Handles API calls and preferences
- Maps DTOs to domain models

### Data
- API client
- Local preferences

---

## Key Decisions

- **Screen vs View separation**  
  Keeps UI simple and reusable, with navigation and lifecycle handled at the screen level.

- **Explicit search API**  
  Uses `search(city)` instead of relying on internal state to make behavior predictable.

- **DTO → Domain mapping**  
  Keeps API models separate from UI models.

- **Repository as single source of truth**  
  Centralizes data access and avoids duplication.

- **Platform-specific UI**  
  Android uses Material patterns, while iOS follows native conventions.

---

## Trade-offs

- Kept the architecture simple instead of introducing a DI framework
- Prioritized readability over abstraction
- Did not enforce pixel-perfect parity across platforms

---

## Next Steps

- Add more unit tests
- Improve formatting (dates, localization)
- Improve icon rendering on iOS