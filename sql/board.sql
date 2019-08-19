CREATE TABLE `board` (
  `bno` int(11) NOT NULL AUTO_INCREMENT,
  `manager_classification` varchar(45) DEFAULT NULL,
  `manager_belong` varchar(45) DEFAULT NULL,
  `client_number` varchar(45) NOT NULL,
  `client_belong` varchar(45) DEFAULT NULL,
  `client_local` varchar(45) DEFAULT NULL,
  `client_name` varchar(45) DEFAULT NULL,
  `instrument` varchar(45) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `progress` varchar(45) NOT NULL,
  `completeId` varchar(45) DEFAULT NULL,
  `completeDate` datetime DEFAULT NULL,
  `regdate` datetime DEFAULT NULL,
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8