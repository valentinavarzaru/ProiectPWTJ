CREATE TABLE `demo_proiect1`.`clienti` (
  `idClient` INT NOT NULL AUTO_INCREMENT,
  `numeClient` VARCHAR(45) NULL,
  `prenumeClient` VARCHAR(45) NULL,
  `gen` VARCHAR(45) NULL,
  `nrTelefon` INT NULL,
  PRIMARY KEY (`idClient`));


CREATE TABLE `demo_proiect1`.`mylogger` (
  `idLogger` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NULL,
  `mesajLogger` VARCHAR(45) NULL,
  PRIMARY KEY (`idLogger`));


CREATE TABLE `demo_proiect1`.`angajati` (
  `idAngajat` INT NOT NULL AUTO_INCREMENT,
  `numeAngajat` VARCHAR(45) NULL,
  `prenumeAngajat` VARCHAR(45) NULL,
  `taxaPerServiciu` DOUBLE NULL,
  PRIMARY KEY (`idAngajat`));

CREATE TABLE `demo_proiect1`.`servicii` (
  `idServiciu` INT NOT NULL AUTO_INCREMENT,
  `numeServiciu` VARCHAR(45) NULL,
  `descriereServiciu` VARCHAR(45) NULL,
  `pretServiciu` DOUBLE NULL,
  PRIMARY KEY (`idServiciu`));


CREATE TABLE `demo_proiect1`.`programari` (
  `idProgramare` INT NOT NULL AUTO_INCREMENT,
  `idClient` INT NULL,
  `idServiciu` INT NULL,
  `idAngajat` INT NULL,
  `data` VARCHAR(45) NULL,
  `ora` VARCHAR(45) NULL,
  PRIMARY KEY (`idProgramare`),
  INDEX `idClient_idx` (`idClient` ASC),
  INDEX `idServiciu_idx` (`idServiciu` ASC),
  INDEX `idAngajat_idx` (`idAngajat` ASC),
  CONSTRAINT `idClient`
    FOREIGN KEY (`idClient`)
    REFERENCES `demo_proiect1`.`clienti` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idServiciu`
    FOREIGN KEY (`idServiciu`)
    REFERENCES `demo_proiect1`.`servicii` (`idServiciu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idAngajat`
    FOREIGN KEY (`idAngajat`)
    REFERENCES `demo_proiect1`.`angajati` (`idAngajat`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `demo_proiect1`.`plati` (
  `idPlata` INT NOT NULL AUTO_INCREMENT,
  `idProgramare` INT NULL,
  `metodaDePlata` VARCHAR(45) NULL,
  `pretFinal` DOUBLE NULL,
  PRIMARY KEY (`idPlata`),
  INDEX `idProgramare_idx` (`idProgramare` ASC),
  CONSTRAINT `idProgramare`
    FOREIGN KEY (`idProgramare`)
    REFERENCES `demo_proiect1`.`programari` (`idProgramare`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE `demo_proiect1`.`mylogger` (
  `idLogger` INT NOT NULL AUTO_INCREMENT,
  `dataLogger` DATE NULL,
  `oraLogger` VARCHAR(450) NULL,
  `repoLogger` VARCHAR(450) NULL,
  `contentLogger` VARCHAR(65400) NULL,
  PRIMARY KEY (`idLogger`));