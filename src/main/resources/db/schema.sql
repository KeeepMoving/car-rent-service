CREATE DATABASE crs DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

CREATE TABLE `t_car_info` (
  `id`                  BIGINT(11)              NOT NULL AUTO_INCREMENT    COMMENT 'ID',
  `license_number`      VARCHAR(7)              NOT NULL                   COMMENT 'License Number',
  `car_model`           VARCHAR(16)             NOT NULL                   COMMENT 'Car Model(Toyota Camry, BMW650)',
  `daily_rent`          DECIMAL(8,2)            NOT NULL                   COMMENT 'Daily Rent',
  `currency`            VARCHAR(16)             NOT NULL                   COMMENT 'Currency(RMB)',
  `image_url`           VARCHAR(256)            NOT NULL                   COMMENT 'Image Url',
  `comments`            VARCHAR(256)                                       COMMENT 'Comments',
  `created_time`        DATETIME                DEFAULT CURRENT_TIMESTAMP  COMMENT 'Created Time',
  `modified_time`       DATETIME                                           COMMENT 'Modified Time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT = 'Table of Car Info';
create index index_car_info_license_number on t_car_info(license_number);
create index index_car_info_created_time on t_car_info(created_time);

INSERT INTO `t_car_info` VALUES (1, '粤B123AD', 'Toyota_Camry', 300.00, 'RMB', 'https://www.gac-toyota.com.cn/vehicles/camry/images/carTabs/camryxle/car01.png', 'c1', '2020-07-26 20:52:14', '2020-07-26 20:52:18');
INSERT INTO `t_car_info` VALUES (2, '粤B123AE', 'Toyota_Camry', 300.00, 'RMB', 'https://www.gac-toyota.com.cn/vehicles/camry/images/carTabs/camryxle/car01.png', 'c2', '2020-07-26 20:54:29', '2020-07-26 20:54:28');
INSERT INTO `t_car_info` VALUES (3, '粤B123AF', 'BMW650', 600.00, 'RMB', 'https://www.bmw.com.cn/content/dam/bmw/marketCN/common/HomepageTeaser/Medium/2020/x7.jpg/_jcr_content/renditions/cq5dam.resized.img.585.low.time1590740206778.jpg', 'c3', '2020-07-26 20:55:39', '2020-07-26 20:55:36');
INSERT INTO `t_car_info` VALUES (4, '粤B123AG', 'BMW650', 600.00, 'RMB', 'https://www.bmw.com.cn/content/dam/bmw/marketCN/common/HomepageTeaser/Medium/2020/x7.jpg/_jcr_content/renditions/cq5dam.resized.img.585.low.time1590740206778.jpg', 'c4', '2020-07-26 20:56:02', '2020-07-26 20:56:00');


CREATE TABLE `t_rent_order` (
  `id`                  BIGINT(11)              NOT NULL AUTO_INCREMENT    COMMENT 'ID',
  `user_id`             BIGINT(11)              NOT NULL                   COMMENT 'User ID',
  `car_info_id`         BIGINT(11)              NOT NULL                   COMMENT 'Car Info ID',
  `currency`            VARCHAR(16)             NOT NULL                   COMMENT 'Currency(RMB)',
  `price`               DECIMAL(8,2)            NOT NULL                   COMMENT 'Pirce',
  `quantity`            INT                     NOT NULL                   COMMENT 'Quantity',
  `amount`              DECIMAL(8,2)            NOT NULL                   COMMENT 'Amount',
  `order_status`        VARCHAR(16)             NOT NULL                   COMMENT 'Order Status(ON_GOING, FINISHED, CANCELED, EXPIRED)',
  `start_time`          DATETIME                NOT NULL                   COMMENT 'Start Time',
  `end_time`            DATETIME                NOT NULL                   COMMENT 'End Time',
  `comments`            VARCHAR(256)                                       COMMENT 'Comments',
  `created_time`        DATETIME                DEFAULT CURRENT_TIMESTAMP  COMMENT 'Created Time',
  `modified_time`       DATETIME                                           COMMENT 'Modified Time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT = 'Table of Rent Order Info';
create index index_rent_order_user_id on t_rent_order(user_id);
create index index_rent_order_car_info_id on t_rent_order(car_info_id);
create index index_rent_order_end_time on t_rent_order(start_time);
create index index_rent_order_start_time on t_rent_order(end_time);
create index index_rent_order_created_time on t_rent_order(created_time);