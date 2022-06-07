CREATE TABLE IF NOT EXISTS `warehouse`.`inventory_details` (
    `art_id` INT NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `stock` INT NOT NULL DEFAULT 0,
    PRIMARY KEY (`art_id`)
);
  
CREATE TABLE IF NOT EXISTS `warehouse`.`product_details` (
    `prd_id` INT AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL UNIQUE,
    `equal_amount_of` INT NOT NULL DEFAULT 0,
    `possible_amount_of` INT NOT NULL DEFAULT 0,
    `price` INT DEFAULT 0,
    PRIMARY KEY (`prd_id`)
);

CREATE TABLE IF NOT EXISTS `warehouse`.`products_inventory_details` (
  `id` INT AUTO_INCREMENT,
  `prd_id` INT NOT NULL,
  `art_id` INT NOT NULL,
  `amount_of` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `prd_id_fk_idx` (`prd_id` ASC) VISIBLE,
  INDEX `ard_id_fk_idx` (`art_id` ASC) VISIBLE,
  CONSTRAINT `prd_id_fk`
    FOREIGN KEY (`prd_id`)
    REFERENCES `warehouse`.`product_details` (`prd_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `art_id_fk`
    FOREIGN KEY (`art_id`)
    REFERENCES `warehouse`.`inventory_details` (`art_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);