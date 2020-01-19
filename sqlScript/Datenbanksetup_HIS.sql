SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema informationssystem
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `informationssystem` ;

-- -----------------------------------------------------
-- Schema informationssystem
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `informationssystem` DEFAULT CHARACTER SET utf8 ;
USE `informationssystem` ;

-- -----------------------------------------------------
-- Table `informationssystem`.`studiengang`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `informationssystem`.`studiengang` ;

CREATE TABLE IF NOT EXISTS `informationssystem`.`studiengang` (
  `studiengang_id` INT(11) NOT NULL AUTO_INCREMENT,
  `studiengang_name` VARCHAR(30) NOT NULL,
  `numerus_clausus_note` DOUBLE,
  `studiengang_platzzahl` INT(11) NOT NULL,
  `studiengang_freie_plaetze` INT(11) NOT NULL,
  `vorraussetzung_test` TINYINT(1) NOT NULL,
  `beschreibung_voraussetzung` VARCHAR(120),
  `nc_notwendig` BOOLEAN NOT NULL,
  `zulassungszeitraum` DATE NOT NULL,
  PRIMARY KEY (`studiengang_id`),
  UNIQUE INDEX `studiengang_id_UNIQUE` (`studiengang_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `informationssystem`.`bewerber`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `informationssystem`.`bewerber` ;

CREATE TABLE IF NOT EXISTS `informationssystem`.`bewerber` (
  `bewerber_id` INT(11) NOT NULL,
  `nachname` VARCHAR(30) NOT NULL,
  `vorname` VARCHAR(30) NOT NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  `geburtsort` VARCHAR(30) NOT NULL,
  `nationalitaet` VARCHAR(30) NOT NULL,
  `wohnort` VARCHAR(30) NOT NULL,
  `adresse` VARCHAR(30) NOT NULL,
   `abiturnote` double NOT NULL,
  `geburtsdatum` DATE NOT NULL,
  `studiengang_id` INT(11) NOT NULL,
  `mat_nr` INT(11) NULL DEFAULT NULL,
  `sonstige_informationen` VARCHAR(400) NULL DEFAULT NULL,
  PRIMARY KEY (`bewerber_id`),
  INDEX `studiengang` (`studiengang_id` ASC) VISIBLE,
  UNIQUE INDEX `bewerber_id_UNIQUE` (`bewerber_id` ASC) VISIBLE,
  CONSTRAINT `studiengang`
    FOREIGN KEY (`studiengang_id`)
    REFERENCES `informationssystem`.`studiengang` (`studiengang_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `informationssystem`.`bewerbungsunterlagen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `informationssystem`.`bewerbungsunterlagen` ;

CREATE TABLE IF NOT EXISTS `informationssystem`.`bewerbungsunterlagen` (
  `unterlagen_id` INT(11) NOT NULL,
  `hochschulreife` TINYINT(1) NOT NULL,
  `krankenversicherung` TINYINT(1) NOT NULL,
  `immatrikulationsantrag` TINYINT(1) NOT NULL,
  `bewerbungsschreiben` TINYINT(1) NULL DEFAULT NULL,
  `hochschulreifeLocation` varchar(80),
  `krankenversicherungLocation` varchar(80),
  `immatrikulationsantragLocation` varchar(80),
  `bewerbungsschreibenLocation` varchar(80),
  PRIMARY KEY (`unterlagen_id`),
  UNIQUE INDEX `unterlagen_id_UNIQUE` (`unterlagen_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `informationssystem`.`immatrikulationsverfahren_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `informationssystem`.`immatrikulationsverfahren_status` ;

CREATE TABLE IF NOT EXISTS `informationssystem`.`immatrikulationsverfahren_status` (
  `unterlagen_vollstaendig` TINYINT(1) NOT NULL,
  `immatrikulation_id` INTEGER NOT NULL,
  `zahlung_status` TINYINT(1) NOT NULL,
  `zulassung_status` TINYINT(1) NOT NULL,
  `bewerbungseingang` DATE NOT NULL,
  `status_informationen` VARCHAR(100) NULL DEFAULT NULL,
  `bewerber_id` INT(11) NOT NULL,
  `unterlagen_id` INT(11) NOT NULL,
  PRIMARY KEY (`immatrikulation_id`),
  INDEX `fk_user_detail_user` (`bewerber_id` ASC) VISIBLE,
  INDEX `fk_immatrikulationsverfahren_status_bewerbungsunterlagen1_idx` (`unterlagen_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_detail_user`
    FOREIGN KEY (`bewerber_id`)
    REFERENCES `informationssystem`.`bewerber` (`bewerber_id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_immatrikulationsverfahren_status_bewerbungsunterlagen1`
    FOREIGN KEY (`unterlagen_id`)
    REFERENCES `informationssystem`.`bewerbungsunterlagen` (`unterlagen_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `informationssystem`.`student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `informationssystem`.`student` ;

CREATE TABLE IF NOT EXISTS `informationssystem`.`student` (
  `mat_nr` INT(11) NOT NULL,
  `vorname` VARCHAR(30) NOT NULL,
  `nachname` VARCHAR(30) NOT NULL,
  `adresse` VARCHAR(40) NOT NULL,
  `geburtsdatum` DATE NOT NULL,
  `geburtsort` VARCHAR(30) NOT NULL,
  `wohnort` VARCHAR(30) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`mat_nr`),
  UNIQUE INDEX `mat_nr_UNIQUE` (`mat_nr` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `informationssystem`.`student_studiengang`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `informationssystem`.`student_studiengang` ;

CREATE TABLE IF NOT EXISTS `informationssystem`.`student_studiengang` (
  `mat_nr` INT(11) NOT NULL,
  `studiengang_id` INT(11) NOT NULL,
  `aktives_studium` TINYINT(1) NOT NULL,
  `semeser` INT NOT NULL,
  PRIMARY KEY (`mat_nr`, `studiengang_id`),
  INDEX `studiengang_id` (`studiengang_id` ASC) VISIBLE,
  UNIQUE INDEX `mat_nr_UNIQUE` (`mat_nr` ASC) VISIBLE,
  UNIQUE INDEX `studiengang_id_UNIQUE` (`studiengang_id` ASC) VISIBLE,
  CONSTRAINT `student_studiengang_ibfk_1`
    FOREIGN KEY (`mat_nr`)
    REFERENCES `informationssystem`.`student` (`mat_nr`)
    ON DELETE CASCADE,
  CONSTRAINT `student_studiengang_ibfk_2`
    FOREIGN KEY (`studiengang_id`)
    REFERENCES `informationssystem`.`studiengang` (`studiengang_id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
