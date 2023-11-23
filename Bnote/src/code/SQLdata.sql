
CREATE TABLE IF NOT EXISTS admins (
    adminMailID VARCHAR(100) PRIMARY KEY,
    adminPassword VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS profs (
    mailID VARCHAR(100) PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    telephone VARCHAR(15),
    motDePasse VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS etudiants (
    mailID VARCHAR(100) PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    telephone VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS matieres (
    nomMatiereID VARCHAR(100) PRIMARY KEY,
    mailProfs VARCHAR(100),
    FOREIGN KEY (mailProfs) REFERENCES profs(mailID)
);

CREATE TABLE IF NOT EXISTS controles (
    date DATE,
    note INT,
    coef INT,
    mailID VARCHAR(100),
    appreciation VARCHAR(100),
    intitule VARCHAR(100),
    nomMatiereID VARCHAR(100),
    PRIMARY KEY (date, mailID, nomMatiereID),
    FOREIGN KEY (mailID) REFERENCES etudiants(mailID),
    FOREIGN KEY (nomMatiereID) REFERENCES matieres(nomMatiereID)
);


