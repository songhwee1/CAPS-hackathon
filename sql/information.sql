CREATE TABLE `information` (
  `client_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `client_belong` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `client_number` varchar(45) COLLATE utf8_bin NOT NULL,
  `client_local` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`client_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin