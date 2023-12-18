CREATE TABLE `user_auth` (
      auth_no int NOT NULL AUTO_INCREMENT
    , user_id varchar(100) NOT NULL
    , auth varchar(100) NOT NULL
    , PRIMARY KEY(auth_no)
);