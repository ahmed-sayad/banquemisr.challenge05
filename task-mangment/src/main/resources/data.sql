
INSERT INTO users VALUES
(1, 'John Doe', 'john.doe@example.com', 'hashed_password', 'USER'),
(2, 'Admin User', 'admin@example.com', 'hashed_password', 'ADMIN');

INSERT INTO tasks VALUES
(1, 1, 'Fix Bug #123', 'Fix the reported bug in the login module', '2024-12-15', 'HIGH', 'IN_PROGRESS'),
(2, 1, 'Add Feature X', 'Develop and test feature X for the dashboard', '2024-12-20', 'MEDUIM', 'TODO');

INSERT INTO notifications VALUES
(1, 1, '2024-11-30 14:00:00', 1, 'Bug #123 is being worked on'),
(2, 2, '2024-11-30 15:00:00', 1, 'New task assigned: Add Feature X');


INSERT INTO history VALUES
(1, 1, 1, 1),
(2, 2, 2, 1);
