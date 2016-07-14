USE fuber_db;

--
-- Table structure for table bookings
--
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS bookings;

CREATE TABLE bookings (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  created_time datetime NOT NULL,
  updated_time datetime NOT NULL,
  end_lat double DEFAULT NULL,
  end_lon double DEFAULT NULL,
  end_time datetime DEFAULT NULL,
  fare float DEFAULT NULL,
  start_lat double DEFAULT NULL,
  start_lon double DEFAULT NULL,
  start_time datetime DEFAULT NULL,
  taxi_id bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (taxi_id) REFERENCES taxis (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table taxi_types
--

DROP TABLE IF EXISTS taxi_types;

CREATE TABLE taxi_types (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  created_time datetime NOT NULL,
  updated_time datetime NOT NULL,
  base_fare float NOT NULL,
  name varchar(255) NOT NULL UNIQUE,
  rate_per_km float NOT NULL,
  rate_per_min float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table taxis
--

DROP TABLE IF EXISTS taxis;

CREATE TABLE taxis (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  created_time datetime NOT NULL,
  updated_time datetime NOT NULL,
  driver_name varchar(255) NOT NULL,
  email varchar(255) NOT NULL UNIQUE,
  lat double DEFAULT NULL,
  lon double DEFAULT NULL,
  mobile_number bigint(20) NOT NULL UNIQUE,
  occupied bit(1) NOT NULL,
  registration_number varchar(255) NOT NULL UNIQUE,
  taxi_type_id bigint(20) NOT NULL,
  FOREIGN KEY (taxi_type_id) REFERENCES taxi_types (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table users
--

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  created_time datetime NOT NULL,
  updated_time datetime NOT NULL,
  email varchar(255) NOT NULL UNIQUE,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) DEFAULT NULL,
  mobile_number bigint(20) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=1;
