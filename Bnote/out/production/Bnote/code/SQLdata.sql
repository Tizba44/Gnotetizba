-- Contexte : Création d’un nouvel outil de gestion des
-- notes des étudiants en BTS
-- Le coordinateur de la formation de BTS SIO a exprimé son souhait de fournir à tous les professeurs
-- de la formation un outil de bureau facile à utiliser pour saisir les notes des étudiants.
-- Il a demandé à l’équipe de développement informatique du lycée de lui proposer une solution viable
-- facile à maintenir, robuste et à moindre coût.
-- Actuellement, il y a 8 matières enseignées pour la formation. Chaque enseignant(e) par matière peut
-- saisir le coefficient de la matière pour un contrôle donné, la note et les appréciations pour chaque
-- étudiant. Il/elle peut voir, au fur et à mesure de la saisie, la moyenne, la note minimale et maximale
-- de la classe pour sa matière. Chaque étudiant suit les 8 matières.
--  Chaque enseignant(e) doit avoir accès à une vue d’ensemble des notes et des appréciations de
-- toutes les matières des étudiants de la formation, avec affichage du(es) major(s) de la promotion par
-- semestre.
-- Dès la première version de l’application, le coordinateur de la formation a exigé que chaque
-- enseignant(e) doit pouvoir toujours saisir ses notes même s’il/elle est hors réseau. 


-- Page d’administration
-- L’administrateur, crée/modifie les matières enseignées (Seulement l’intitulé. Il n’est pas responsable
-- de l’intitulé du contrôle passé, ni des coefficients et ni des notes associés).
-- Il crée/modifie également les enseignant(e)s en ajoutant leur nom, prénom, adresse mail, numéro de
-- téléphone, leur mot de passe et la matière enseignée.
--  Il crée/modifie enfin les étudiants en ajoutant leur nom, prénom, adresse mail, numéro de
-- téléphone.
-- Page d’un utilisateur
-- Chaque enseignant(e) a accès à la liste de tous les étudiants de la formation. Il/elle peut
-- créer/modifier l’intitulé du contrôle passé, le coefficient, les notes (Total des points) et les
-- appréciations de chaque étudiant. Il/elle peut voir, au fur et à mesure de la saisie, la moyenne, la
-- note minimale et maximale de la classe pour sa matière. Un effet de couleurs est demandé pour voir
-- directement celui ou ceux qui a(ont) la note minimale ou maximale.
--  Chaque enseignant(e) doit avoir accès à une vue d’ensemble des notes et des appréciations de
-- toutes les matières des étudiants de la formation, avec affichage du(es) major(s) de la promotion par
-- semestre



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


