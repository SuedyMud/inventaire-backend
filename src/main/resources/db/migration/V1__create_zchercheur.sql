CREATE TABLE zchercheur
(
    idche            INT                              NOT NULL,
    nom              VARCHAR(25)                      NOT NULL,
    prenom           VARCHAR(25)                      NOT NULL,
    titre            VARCHAR(25)                      NULL,
    matricule        VARCHAR(25)                      NULL,
    cpi              VARCHAR(8)                       NULL,
    telephone        VARCHAR(25)                      NULL,
    email            VARCHAR(120)                     NOT NULL,
    fax              VARCHAR(25)                      NULL,
    site             VARCHAR(255)                     NOT NULL,
    corps            VARCHAR(20)                      NOT NULL,
    corps_ordre      INT                              NOT NULL,
    ddig             datetime      DEFAULT NOW()      NOT NULL,
    fac_che          VARCHAR(50)   DEFAULT ' '        NOT NULL,
    pref_publication VARCHAR(5000) DEFAULT 'integree' NULL,
    CONSTRAINT pk_zchercheur PRIMARY KEY (idche)
);