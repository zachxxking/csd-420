
-- Create 'fans' table
CREATE TABLE fans (
    ID INT PRIMARY KEY,
    firstname VARCHAR(25),
    lastname VARCHAR(25),
    favoriteteam VARCHAR(25)
);

-- Sample records
INSERT INTO fans (ID, firstname, lastname, favoriteteam) VALUES
(1, 'Zach', 'King', 'Animals'),
(2, 'Alex', 'Elchish', 'Little Monsters'),
(3, 'Emily', 'Brown', 'Katycats'),
(4, 'Karissa', 'Clark', 'Lovatics');
