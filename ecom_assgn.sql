-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ecom_assgn
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ecom_assgn
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecom_assgn` DEFAULT CHARACTER SET utf8 ;
USE `ecom_assgn` ;

-- -----------------------------------------------------
-- Table `ecom_assgn`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom_assgn`.`customer` (
  `cust_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`cust_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecom_assgn`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom_assgn`.`category` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecom_assgn`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom_assgn`.`product` (
  `prod_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL,
  `category_category_id` INT NOT NULL,
  PRIMARY KEY (`prod_id`, `category_category_id`),
  INDEX `fk_product_category1_idx` (`category_category_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`category_category_id`)
    REFERENCES `ecom_assgn`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ecom_assgn`.`purchase`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecom_assgn`.`purchase` (
  `purchase_id` INT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(255) NOT NULL,
  `customer_cust_id` INT NOT NULL,
  `product_prod_id` INT NOT NULL,
  `coupon_used` VARCHAR(45) NULL,
  `amount_paid` DOUBLE NULL,
  PRIMARY KEY (`purchase_id`, `customer_cust_id`, `product_prod_id`),
  INDEX `fk_purchase_customer_idx` (`customer_cust_id` ASC) VISIBLE,
  INDEX `fk_purchase_product1_idx` (`product_prod_id` ASC) VISIBLE,
  CONSTRAINT `fk_purchase_customer`
    FOREIGN KEY (`customer_cust_id`)
    REFERENCES `ecom_assgn`.`customer` (`cust_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_purchase_product1`
    FOREIGN KEY (`product_prod_id`)
    REFERENCES `ecom_assgn`.`product` (`prod_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
