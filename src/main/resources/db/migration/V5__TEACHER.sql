CREATE TABLE `TEACHER` (
	`id` SERIAL,
	`TEACHER_ID` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	`PASSWORD` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	`NAME` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	`SCHOOL_CD` CHAR(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB;