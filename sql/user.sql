CREATE TABLE `user` (
  `id` varchar(10) NOT NULL,
  `pw` varchar(20) NOT NULL,
  `manager_belong` varchar(45) NOT NULL,
  `manager_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8