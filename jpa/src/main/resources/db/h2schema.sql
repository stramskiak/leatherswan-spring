
-- ----------------------------
-- Table structure for authorities
-- ----------------------------
CREATE TABLE authorities (
  authority_id bigint(20) NOT NULL AUTO_INCREMENT,
  authority varchar(50) NOT NULL,
  is_locked tinyint(1) NOT NULL,
  PRIMARY KEY (authority_id)
);


-- ----------------------------
-- Table structure for users
-- ----------------------------
CREATE TABLE users (
  user_id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL,
  email varchar(150) NOT NULL,
  first_name varchar(40) NOT NULL,
  last_name varchar(40) NOT NULL,
  enabled tinyint(1) NOT NULL,
  account_expired tinyint(1) NOT NULL,
  account_locked tinyint(1) NOT NULL,
  credentials_expired tinyint(1) NOT NULL,
  has_avatar tinyint(1) NOT NULL,
  user_key varchar(25) NOT NULL DEFAULT '0000000000000000',
  provider_id varchar(25) NOT NULL DEFAULT 'SITE',
  password varchar(255) NOT NULL,
  version int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (user_id)
);


-- ----------------------------
-- Table structure for hobbies
-- ----------------------------
CREATE TABLE hobbies (
  hobby_id bigint(20) NOT NULL AUTO_INCREMENT,
  hobby_title varchar(20) NOT NULL,
  PRIMARY KEY (hobby_id)
);


-- ----------------------------
-- Table structure for contacts
-- ----------------------------
CREATE TABLE contacts (
  contact_id bigint(20) NOT NULL AUTO_INCREMENT,
  first_name varchar(40) NOT NULL,
  last_name varchar(40) NOT NULL,
  birth_date date DEFAULT NULL,
  email varchar(100) NOT NULL,
  created_by_user varchar(50) NOT NULL,
  creation_time timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  modified_by_user varchar(50) NOT NULL,
  modification_time timestamp NULL DEFAULT NULL,
  version int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (contact_id)
);


-- ----------------------------
-- Table structure for contact_hobby_ids
-- ----------------------------
CREATE TABLE contact_hobby_ids (
  contact_hobby_id bigint(20) NOT NULL AUTO_INCREMENT,
  contact_id bigint(20) NOT NULL,
  hobby_id bigint(20) NOT NULL,
  PRIMARY KEY (contact_hobby_id),
  CONSTRAINT fk_hobby_contact_id FOREIGN KEY (contact_id) REFERENCES contacts (contact_id) ON DELETE CASCADE,
  CONSTRAINT fk_hobby_hobby_id FOREIGN KEY (hobby_id) REFERENCES hobbies (hobby_id)
);

-- ----------------------------
-- Table structure for contact_phones
-- ----------------------------
CREATE TABLE contact_phones (
  contact_phone_id bigint(20) NOT NULL AUTO_INCREMENT,
  contact_id bigint(20) NOT NULL,
  phone_type varchar(20) NOT NULL,
  phone_number varchar(20) NOT NULL,
  version int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (contact_phone_id),
  CONSTRAINT fk_contact_phones_contact_id FOREIGN KEY (contact_id) REFERENCES contacts (contact_id)
);

-- ----------------------------
-- Table structure for site_options
-- ----------------------------
CREATE TABLE site_options (
  option_id bigint(20) NOT NULL AUTO_INCREMENT,
  option_name varchar(50) NOT NULL,
  option_value text,
  PRIMARY KEY (option_id)
);

-- ----------------------------
-- Table structure for user_authorities
-- ----------------------------
CREATE TABLE user_authorities (
  user_id bigint(20) NOT NULL,
  authority_id bigint(20) NOT NULL,
  UNIQUE KEY uc_user_authorities (user_id,authority_id),
  CONSTRAINT fk_user_authorities_1 FOREIGN KEY (user_id) REFERENCES users (user_id),
  CONSTRAINT fk_user_authorities_2 FOREIGN KEY (authority_id) REFERENCES authorities (authority_id)
);

-- ----------------------------
-- Table structure for user_profiles
-- ----------------------------
CREATE TABLE user_profiles (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  address2 varchar(255) DEFAULT NULL,
  city varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  zip varchar(10) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- ----------------------------
-- Table structure for userconnection
-- ----------------------------
CREATE TABLE userconnection (
  userId varchar(255) NOT NULL,
  providerId varchar(255) NOT NULL,
  providerUserId varchar(255) NOT NULL DEFAULT '',
  rank int(11) NOT NULL,
  displayName varchar(255) DEFAULT NULL,
  profileUrl varchar(512) DEFAULT NULL,
  imageUrl varchar(512) DEFAULT NULL,
  accessToken varchar(255) NOT NULL,
  secret varchar(255) DEFAULT NULL,
  refreshToken varchar(255) DEFAULT NULL,
  expireTime bigint(20) DEFAULT NULL,
  PRIMARY KEY (userId,providerId,providerUserId)
);

-- ----------------------------
-- Table structure for posts
-- ----------------------------
CREATE TABLE posts (
  post_id bigint(20) NOT NULL AUTO_INCREMENT,
  user_id bigint(20) NOT NULL,
  post_title varchar(200) NOT NULL,
  post_name varchar(200) NOT NULL,
  post_link varchar(255) DEFAULT NULL,
  post_date timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  post_modified timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  post_type varchar(20) NOT NULL DEFAULT 'LINK',
  display_type varchar(20) NOT NULL DEFAULT 'LINK',
  is_published tinyint(1) NOT NULL DEFAULT '0',
  post_content text NOT NULL,
  post_source varchar(50) NULL,
  post_image varchar(200) DEFAULT NULL,
  click_count int(11) NOT NULL DEFAULT '0',
  likes_count int(11) NOT NULL DEFAULT '0',
  value_rating int(11) NOT NULL DEFAULT '0',
  version int(11) NOT NULL DEFAULT '0',
  UNIQUE KEY posts_post_id_uindex (post_id),
  UNIQUE KEY posts_post_name_pk (post_name),
  PRIMARY KEY (post_id),
  CONSTRAINT posts_users_user_id_fk FOREIGN KEY (user_id) REFERENCES users (user_id),

);

CREATE TABLE tags
(
  tag_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  tag_value VARCHAR(50) NOT NULL,
  PRIMARY KEY (tag_id)
);


CREATE TABLE post_tag_ids
(
  post_tag_id BIGINT(20) NOT NULL AUTO_INCREMENT,
  post_id BIGINT(20) NOT NULL,
  tag_id BIGINT(20) NOT NULL,
  PRIMARY KEY (post_tag_id),
  CONSTRAINT fk_posts_post_id FOREIGN KEY (post_id) REFERENCES posts (post_id),
  CONSTRAINT fk_tags_tag_id FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
);
