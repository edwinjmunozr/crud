DROP TABLE IF EXISTS certificate;

CREATE TABLE certificate(
  id                 bigserial not null,
  partner_id         varchar (30) not null,
  status             varchar (20) not null,
  startdate_at       timestamp not null,
  deadline_at        timestamp not null,
  filename           varchar (100),
  storage_id         varchar (100),
  created_at         timestamp not null,
  updated_at         timestamp not null,
  deleted_at         timestamp,
  user_id            varchar (100),
  comments           varchar (100)
);

ALTER TABLE certificate ADD CONSTRAINT crt_pk PRIMARY KEY(id);
CREATE UNIQUE INDEX idx_crt_01_partner ON certificate(partner_id);
CREATE INDEX idx_crt_02 ON certificate(created_at);
CREATE INDEX idx_crt_03 ON certificate(status);

COMMENT ON TABLE  certificate IS 'Certificate Information';
COMMENT ON COLUMN certificate.id IS 'Register Id unique';

