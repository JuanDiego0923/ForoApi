CREATE TABLE `topicos` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `titulo` VARCHAR(100) NOT NULL,
    `mensaje` VARCHAR(300) NOT NULL,
    `fecha_creacion` DATE NOT NULL,
    `estatus_topico` tinyint NOT NULL,
    `autor` VARCHAR(100) NOT NULL,
    `curso` VARCHAR(100) NOT NULL,
    
    PRIMARY KEY (`id`)
);