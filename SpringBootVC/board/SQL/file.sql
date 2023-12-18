CREATE TABLE `file` (
  `file_no` int NOT NULL AUTO_INCREMENT,
  `file_name` text NOT NULL,
  `file_path` text NOT NULL,
  `parent_table` varchar(45) NOT NULL,
  `parent_no` int NOT NULL,
  `file_size` int NOT NULL DEFAULT '0',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_code` int NOT NULL DEFAULT '0',
  `origin_name` text,
  PRIMARY KEY (`file_no`)
) COMMENT='파일';
