--liquibase formatted sql

--changeset author:1
create table location (
  id text,
  person_id varchar,
  device_id varchar,
  longitude varchar,
  latitude varchar,
  bearing varchar,
  altitude varchar
);

--rollback drop table location;