DROP TABLE IF EXISTS topics;
 
CREATE TABLE topics (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  message VARCHAR(250) NOT NULL
);
 
INSERT INTO topics (message) VALUES
  ('Java Webflux no-blocking'),
  ('Java Webflux with h2 database no-blocking');