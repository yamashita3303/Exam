CREATE TABLE `logins` (
	`LOGIN_ID` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	`PASSWORD` VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
	PRIMARY KEY (`LOGIN_ID`)
) ENGINE=InnoDB;