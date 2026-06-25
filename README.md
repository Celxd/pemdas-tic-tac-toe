# Simple Tic-Tac-Toe Game with Java Swing, Login, and Statistics

## Student Information
- **Name:** Alif Gibran Muhammad Ervin
- **Student ID:** 5026251070
- **Class:** E

---

## Project Description
This project is a Tic-Tac-Toe game application built using Java Swing. It features secure player login, a database connection to MySQL, personal player statistics tracking, and a global leaderboard displaying the Top 5 players based on their scores.

The architecture follows standard OOP style:
- **Models** to encapsulate state (`Player`)
- **Services** to query and update the database (`PlayerService`)
- **Engine Logic** to validate moves and determine outcomes (`GameLogic`)
- **GUI Frames** to present interactive windows and capture input (`LoginFrame`, `MainMenuFrame`, `GameFrame`, `StatisticsFrame`, `TopScorersFrame`)

---

## Features
- **Secure Database Login:** Authenticates username and password against a MySQL table.
- **Interactive Tic-Tac-Toe Board:** Dynamic 3x3 game board play against a randomized computer intelligence.
- **Score Calculation:**
  - **Win:** +10 points
  - **Draw:** +3 points
  - **Lose:** +0 points
- **Personal Statistics:** View your real-time stats including lifetime wins, losses, draws, and current score.
- **Top 5 Leaderboard:** Live ranking of the top 5 players based on highest score. In case of ties, the player with more wins is ranked higher.

---

## Database Configuration
- **Database Engine:** MySQL
- **Port:** `3307`
- **Username:** `root`
- **Password:** `""` (empty)
- **Database Name:** `game_project`
- **Table Name:** `players`

### Schema Definition
```sql
CREATE DATABASE IF NOT EXISTS game_project;
USE game_project;

CREATE TABLE IF NOT EXISTS players (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    wins INT DEFAULT 0,
    losses INT DEFAULT 0,
    draws INT DEFAULT 0,
    score INT DEFAULT 0
);
```

---

## How to Run
1. **Create and Setup Database:**
   - Make sure your local MySQL instance is running on port `3307`.
   - Run the SQL script located at `database/schema.sql` to create the database and seed it with test accounts.
2. **Launch Application:**
   - Double-click `compile_and_run.bat` (on Windows) or run it from the command line:
     ```bash
     .\compile_and_run.bat
     ```
3. **Login Details (Seeded accounts):**
   - **Username:** `student1` | **Password:** `12345`
   - **Username:** `student2` | **Password:** `12345`
   - **Username:** `student3` | **Password:** `12345`
   - **Username:** `student4` | **Password:** `12345`
   - **Username:** `student5` | **Password:** `12345`

---

## Class Explanation
- **[Main.java](src/Main.java):** Set the UI look-and-feel and launches `LoginFrame`.
- **[Player.java](src/Player.java):** Encapsulates player information (id, username, wins, losses, draws, and score).
- **[DatabaseManager.java](src/DatabaseManager.java):** Establishes Connection to the MySQL database on port `3307`.
- **[PlayerService.java](src/PlayerService.java):** Performs SQL operations including profile verification, statistics updates, and fetching the Top 5 rankings.
- **[GameLogic.java](src/GameLogic.java):** Handles game rules, checking win conditions, check draw conditions, and choosing computer move.
- **[LoginFrame.java](src/LoginFrame.java):** Renders the user credential inputs and validates them using the service class.
- **[MainMenuFrame.java](src/MainMenuFrame.java):** Provides navigation buttons to go to the game board, check stats, view leaderboard, or exit.
- **[GameFrame.java](src/GameFrame.java):** The Tic-Tac-Toe UI board capturing player turns and scheduling the computer's turn.
- **[StatisticsFrame.java](src/StatisticsFrame.java):** Shows personal records fetched from the database.
- **[TopScorersFrame.java](src/TopScorersFrame.java):** Shows top 5 players sorted by score and wins in a `JTable`.

---

## Screenshots
Screenshots of the running GUI is stored under `screenshots/`

---

## GitHub & Video Links
- **GitHub Repository:** https://github.com/Celxd/pemdas-tic-tac-toe
- **YouTube Demonstration Video:** https://youtu.be/NDZo2U0aC94
