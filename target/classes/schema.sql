CREATE TABLE IF NOT EXISTS USERS (
 userid INT PRIMARY KEY auto_increment,
 username VARCHAR(20),
 salt VARCHAR,
 password VARCHAR,
 firstname VARCHAR(20),
 lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS GIFTS (
    giftId INT PRIMARY KEY auto_increment,
    targetName VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    relationship VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    requestMessage TEXT,
    aiResponse TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);