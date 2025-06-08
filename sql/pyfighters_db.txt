-- Crear la base de datos para PyFighters
CREATE DATABASE pyfighters_db;
USE pyfighters_db;

-- Crear la tabla de usuarios (Fighters)
CREATE TABLE Fighters (
    fighter_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    victories INT DEFAULT 0,
    defeats INT DEFAULT 0,
    draws INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Crear la tabla de scripts
CREATE TABLE Scripts (
    script_id INT AUTO_INCREMENT PRIMARY KEY,
    fighter_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    code TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (fighter_id) REFERENCES Fighters(fighter_id) ON DELETE CASCADE
);

-- Crear la tabla de arenas
CREATE TABLE Arenas (
    arena_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Crear la tabla de desafíos
CREATE TABLE Challenges (
    challenge_id INT AUTO_INCREMENT PRIMARY KEY,
    arena_id INT NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    difficulty VARCHAR(50) NOT NULL,
    rules TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (arena_id) REFERENCES Arenas(arena_id) ON DELETE CASCADE
);

-- Crear la tabla de combates (Matches)
CREATE TABLE Matches (
    match_id INT AUTO_INCREMENT PRIMARY KEY,
    fighter1_id INT NOT NULL,
    fighter2_id INT NOT NULL,
    arena_id INT NOT NULL,
    result VARCHAR(50),
    date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (fighter1_id) REFERENCES Fighters(fighter_id) ON DELETE CASCADE,
    FOREIGN KEY (fighter2_id) REFERENCES Fighters(fighter_id) ON DELETE CASCADE,
    FOREIGN KEY (arena_id) REFERENCES Arenas(arena_id) ON DELETE CASCADE
);

-- Crear la tabla de resultados
CREATE TABLE Results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    match_id INT NOT NULL,
    winner_id INT,
    loser_id INT,
    is_draw BOOLEAN DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (match_id) REFERENCES Matches(match_id) ON DELETE CASCADE,
    FOREIGN KEY (winner_id) REFERENCES Fighters(fighter_id) ON DELETE SET NULL,
    FOREIGN KEY (loser_id) REFERENCES Fighters(fighter_id) ON DELETE SET NULL
);

-- Crear la tabla de torneos
CREATE TABLE Tournaments (
    tournament_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Crear la tabla de logros (Achievements)
CREATE TABLE Achievements (
    achievement_id INT AUTO_INCREMENT PRIMARY KEY,
    fighter_id INT NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    date_awarded DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (fighter_id) REFERENCES Fighters(fighter_id) ON DELETE CASCADE
);

-- Insertar datos de prueba para Fighters
INSERT INTO Fighters (username, email, password, victories, defeats, draws) VALUES
('IronPython', 'iron@pyfighters.com', '123456', 5, 2, 1),
('PyNinja', 'ninja@pyfighters.com', '123456', 3, 4, 2),
('SnakeMaster', 'snake@pyfighters.com', '123456', 8, 1, 0),
('GuidoVanBoss', 'guido@pyfighters.com', '123456', 10, 0, 0),
('AIWarrior', 'ai@pyfighters.com', '123456', 7, 3, 1),
('ScriptSamurai', 'samurai@pyfighters.com', '123456', 2, 6, 2);

-- Insertar datos de prueba para Scripts
INSERT INTO Scripts (fighter_id, title, code) VALUES
(1, 'Fibonacci Finder', 'def fibonacci(n): return n if n <= 1 else fibonacci(n-1) + fibonacci(n-2)'),
(2, 'Prime Hunter', 'def is_prime(n): return all(n % i != 0 for i in range(2, int(n**0.5) + 1))'),
(3, 'Sorting Samurai', 'def quicksort(arr): return arr if len(arr) <= 1 else quicksort([x for x in arr[1:] if x < arr[0]]) + [arr[0]] + quicksort([x for x in arr[1:] if x >= arr[0]])'),
(4, 'Matrix Master', 'def matrix_multiply(A, B): return [[sum(x*y for x,y in zip(row,col)) for col in zip(*B)] for row in A]'),
(5, 'AI Predictor', 'def predict(input): return \"Predicted Value\"'),
(6, 'Game Bot', 'def make_move(board): return \"Next Best Move\"');

-- Insertar datos de prueba para Arenas
INSERT INTO Arenas (name, description, date) VALUES
('Arena Pythonica', 'El campo de batalla definitivo para los amantes de Python', '2025-06-01'),
('Data Science Dome', 'Solo para los más rápidos en análisis de datos', '2025-06-15'),
('Machine Learning Madness', 'Desafíos para las IA más avanzadas', '2025-06-30');

-- Insertar datos de prueba para Challenges
INSERT INTO Challenges (arena_id, name, difficulty, rules) VALUES
(1, 'Fibonacci Fury', 'Medio', 'Calcula la secuencia de Fibonacci para n=1000'),
(2, 'Data Duel', 'Difícil', 'Encuentra la correlación entre dos grandes conjuntos de datos'),
(3, 'AI Annihilation', 'Extremo', 'Desarrolla un algoritmo de clustering en menos de 50 líneas de código');

-- Insertar datos de prueba para Matches
INSERT INTO Matches (fighter1_id, fighter2_id, arena_id, result, date) VALUES
(1, 2, 1, 'Victoria de IronPython', '2025-06-01'),
(3, 4, 2, 'Victoria de SnakeMaster', '2025-06-15'),
(5, 6, 3, 'Empate', '2025-06-30');

-- Insertar datos de prueba para Results
INSERT INTO Results (match_id, winner_id, loser_id, is_draw) VALUES
(1, 1, 2, 0),
(2, 3, 4, 0),
(3, NULL, NULL, 1);

-- Insertar datos de prueba para Tournaments
INSERT INTO Tournaments (name, start_date, end_date, description) VALUES
('Python World Championship', '2025-07-01', '2025-07-15', 'El torneo más grande de programación en Python'),
('Data Science Showdown', '2025-08-01', '2025-08-15', 'Competición de análisis de datos en tiempo real');

-- Insertar datos de prueba para Achievements
INSERT INTO Achievements (fighter_id, name, description, date_awarded) VALUES
(1, 'Primer Sangre', 'Primera victoria en combate', '2025-06-01'),
(3, 'Invicto', '10 victorias sin derrota', '2025-06-15'),
(4, 'Python Legend', 'El mejor programador del campeonato', '2025-06-30'),
(2, 'Data Master', 'El mejor analista de datos', '2025-08-01');

-- Posibles acciones especiales para perfil específico (Admin)
INSERT INTO Fighters (fighter_id, username, email, password)
VALUES (0, 'admin', 'admin@pyfighters.com', 'admin');
