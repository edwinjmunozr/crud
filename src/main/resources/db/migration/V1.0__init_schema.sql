DROP TABLE IF EXISTS users;

CREATE TABLE users(
  id                 varchar (100) not null,
  name               varchar (100) not null,
  email              varchar (100) not null,
  password           varchar (100) not null,
  token              varchar (250),
  active             boolean not null,
  created_at         timestamp not null,
  modified_at        timestamp not null,
  last_login         timestamp,
  role               varchar(10)
);

insert into users(id,name,email,password,token,active,created_at,modified_at,last_login,role)
values('5ad033e2c7874d12b987ea874c0a7f1d','Admin','admin@youdomain.com','$2a$10$k68S1.LYOHD8fG38UlfCKO51pvpC2yt/QIH8hxUHE7iVfTudxAqB6','token','t',current_timestamp,current_timestamp,current_timestamp,'ADMIN');

ALTER TABLE users ADD CONSTRAINT usr_pk PRIMARY KEY(id);
CREATE UNIQUE INDEX idx_usr_email ON users(email);
CREATE INDEX idx_usr_created ON users(created_at);

COMMENT ON TABLE  users IS 'User Information';
COMMENT ON COLUMN users.id IS 'Register Id unique';
COMMENT ON COLUMN users.name IS 'User name';
COMMENT ON COLUMN users.email IS 'User email';
COMMENT ON COLUMN users.password IS 'Password';
COMMENT ON COLUMN users.token IS 'Token';
COMMENT ON COLUMN users.active IS 'Is user active';
COMMENT ON COLUMN users.created_at IS 'Creation date';
COMMENT ON COLUMN users.modified_at IS 'Modification date';
COMMENT ON COLUMN users.last_login IS 'Last login date';
COMMENT ON COLUMN users.role IS 'User role: [admin, user]';


DROP TABLE IF EXISTS users_phones;

CREATE TABLE users_phones(
  id                 varchar (100) not null,
  user_id            varchar (100) not null,
  phone_number       varchar  (30) not null,
  city_code          varchar  (10) not null,
  country_code       varchar  (10) not null
);

ALTER TABLE users_phones ADD CONSTRAINT phn_pk PRIMARY KEY(id);
ALTER TABLE users_phones ADD CONSTRAINT phone_fk01 FOREIGN KEY (user_id) REFERENCES users (id);
CREATE INDEX idx_pnh_userid ON users_phones(user_id);

COMMENT ON TABLE  users_phones IS 'Phone User Information';
COMMENT ON COLUMN users_phones.id IS 'Register Id unique';
COMMENT ON COLUMN users_phones.user_id IS 'UserId code';
COMMENT ON COLUMN users_phones.phone_number IS 'Phone number';
COMMENT ON COLUMN users_phones.city_code IS 'City code';
COMMENT ON COLUMN users_phones.country_code IS 'Country code';

