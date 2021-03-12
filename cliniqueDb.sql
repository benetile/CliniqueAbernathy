
CREATE TABLE praticien (
                idPraticien SERIAL,
                firstName VARCHAR(40) NOT NULL,
                lastName VARCHAR(40) NOT NULL,
                speciality VARCHAR(40) NOT NULL,
                sex VARCHAR(20) NOT NULL,
                phone VARCHAR(20) NOT NULL,
                CONSTRAINT praticien_pk PRIMARY KEY (idPraticien)
);


CREATE TABLE patient (
                idPatient SERIAL,
                firstName VARCHAR(40) NOT NULL,
                lastName VARCHAR(40) NOT NULL,
                birthday DATE NOT NULL,
                gender VARCHAR(20) NOT NULL,
                zipCode VARCHAR(10) NOT NULL,
                phone VARCHAR(20) NOT NULL,
                CONSTRAINT patient_pk PRIMARY KEY (idPatient)
);


CREATE TABLE recommandation (
                id SERIAL,
                idPatient INTEGER NOT NULL,
                observation VARCHAR(100) NOT NULL,
                dateObservation DATE NOT NULL,
                CONSTRAINT recommandation_pk PRIMARY KEY (id)
);


ALTER TABLE recommandation ADD CONSTRAINT patient_recommandation_fk
FOREIGN KEY (idPatient)
REFERENCES patient (idPatient)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;
