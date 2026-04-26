# 💸 Expense Tracker CLI

A command-line interface (CLI) application for tracking personal expenses built with Kotlin. Features an intuitive menu interface for encoding expenses which can be viewed in a formatted table display and saved as a local JSON file.

## 💻 Technologies

- `Kotlin`
- `Gradle`

## ✨ Features

- Add expense records with amount, category, and description
- Remove expense records by ID
- View all recorded expenses in a formatted table
- View recorded expenses filtered by category
- View spending summary with spending insights
- Store expense record as JSON file

## 💡 Process

This project was an inspired idea I developed to apply and master fundamental Kotlin concepts before diving into Android native development.

I focused on hands-on practice with data classes and enums for type-safe modeling, collections operations (`map`, `filter`, `groupBy`, `sumOf`) for data manipulation, and null safety best practices (`?.`, `?:`, safe calls) for error handling. I used `File I/O` with `kotlinx.serialization` and custom serializers for session persistence while demonstrating proper OOP structure and separation of concerns through repository pattern and service layer.

Refactoring from a single file with scattered logic to clean helper functions and architectural separation reinforced how solid fundamentals enable a maintainable system without needing async patterns like coroutines for this synchronous CLI project.

## 🛠️ Running the Project
Prerequisites: `JDK 17 or higher`, `Git`

1. Clone the GitHub repository
```bash
git clone https://github.com/tristantamani/expense-tracker-cli.git
cd expense-tracker-cli
```
2. Run it with Gradle
```bash
# Run as plain console for best UI
./gradlew run --console:plain --quiet

# On Windows use:
gradlew.bat run
```
3. Build an executable JAR file (Optional)
```bash
./gradlew build
java -jar build/libs/expense-tracker-cli.jar
```
4. Navigate the options on the CLI Application
5. After adding/removing a record, data automatically saves to `expense.json`
