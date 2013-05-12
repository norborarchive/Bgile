DROP SEQUENCE IF EXISTS account_id_seq;
DROP SEQUENCE IF EXISTS project_id_seq;
DROP SEQUENCE IF EXISTS projectaccount_id_seq;
DROP SEQUENCE IF EXISTS userstory_id_seq;
DROP SEQUENCE IF EXISTS iteration_id_seq;
DROP SEQUENCE IF EXISTS todo_id_seq;

DROP TABLE IF EXISTS TODO;
DROP TABLE IF EXISTS USERSTORY;
DROP TABLE IF EXISTS ITERATION;
DROP TABLE IF EXISTS PROJECTACCOUNT;
DROP TABLE IF EXISTS PROJECT;
DROP TABLE IF EXISTS ACCOUNT;

CREATE TABLE ACCOUNT
(
  ID            integer,
  TYPEID        character(1) NOT NULL,
  ISENABLE      character(1) NOT NULL,
  USERNAME      character varying(64) NOT NULL,
  PASSWD        character varying(64) NOT NULL,
  EMAIL	        character varying(128) NOT NULL,
  FIRSTNAME     character varying(64),
  LASTNAME      character varying(64),
  TWITTER       character varying(32),
  BIO           character varying(128),
  AVATARPATH    character varying(256),

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT ACCOUNT_PK PRIMARY KEY (ID)
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE ACCOUNT     OWNER TO bgile;

CREATE SEQUENCE account_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE account_id_seq
  OWNER TO bgile;


CREATE TABLE PROJECT
(
  ID            integer,
  STATUSID      character(1) NOT NULL,
  ISENABLE      character(1) NOT NULL,
  PGNAME	    character varying(128) NOT NULL,
  DESCRIPTION	character varying(512),
  LOGOPATH      character varying(256),

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT PROJECT_PK PRIMARY KEY (ID)
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE PROJECT     OWNER TO bgile;

CREATE SEQUENCE project_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE project_id_seq
  OWNER TO bgile;

CREATE TABLE PROJECTACCOUNT
(
  ID            integer,
  ACCOUNTID     integer NOT NULL,
  PROJECTID     integer NOT NULL,
  PERMISSIONID  character(1) NOT NULL,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT PROJECTACCOUNT_PK PRIMARY KEY (ID),
  CONSTRAINT PROJECT_ACCOUNT_FK1 FOREIGN KEY (PROJECTID)
      REFERENCES PROJECT (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT PROJECT_ACCOUNT_FK2 FOREIGN KEY (ACCOUNTID)
      REFERENCES ACCOUNT (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT PROJECTACCOUNT_IND0 UNIQUE (accountid, projectid)
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE PROJECTACCOUNT     OWNER TO bgile;


CREATE SEQUENCE projectaccount_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE projectaccount_id_seq
  OWNER TO bgile;


CREATE TABLE ITERATION
(
  ID            integer,
  TOPIC         character varying(64) NOT NULL,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT ITERATION_PK PRIMARY KEY (ID)
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE ITERATION     OWNER TO bgile;


CREATE SEQUENCE iteration_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE iteration_id_seq
  OWNER TO bgile;

CREATE TABLE USERSTORY
(
  ID            integer,
  PROJECTID     integer NOT NULL,
  ITERATIONID   integer,
  STORY         character varying(512) NOT NULL,
  OWNERID       integer,
  SORTORDER		integer,
  ESTIMATE      integer NOT NULL,
  STATEID       character(1) NOT NULL,
  STATUSID      character(1) NOT NULL,
  DESCRIPTION	text,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT USERSTORY_PK PRIMARY KEY (ID),
  CONSTRAINT USERSTORY_FK1 FOREIGN KEY (PROJECTID)
      REFERENCES PROJECT (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT USERSTORY_FK2 FOREIGN KEY (ITERATIONID)
      REFERENCES ITERATION (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT USERSTORY_FK3 FOREIGN KEY (OWNERID)
      REFERENCES ACCOUNT (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE USERSTORY     OWNER TO bgile;

CREATE SEQUENCE userstory_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE userstory_id_seq
  OWNER TO bgile;


CREATE TABLE TODO
(
  ID            integer,
  USERSTORYID   integer NOT NULL,
  DESCRIPTION   character varying(512) NOT NULL,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT TODO_PK PRIMARY KEY (ID),
  CONSTRAINT TODO_FK1 FOREIGN KEY (USERSTORYID)
      REFERENCES USERSTORY (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE TODO     OWNER TO bgile;


CREATE SEQUENCE todo_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE todo_id_seq
  OWNER TO bgile;


INSERT INTO account(
            id, typeid, isenable, username, passwd, email, firstname, lastname, bio, avatarpath)
    VALUES (1, 'A', 'Y', 'admin', 'password', 'nuboat@gmail.com', 'Admin', '@ SIGNATURE', 'Default Admin of System', 'avatar/000000000.jpg');

INSERT INTO account(
            id, typeid, isenable, username, passwd, email, firstname, lastname, bio, avatarpath)
    VALUES (2, 'S', 'Y', 'nuboat', 'password', 'Admin', 'Peerapat', 'A', 'Trust me, I am engineer.', 'avatar/000000001.jpg');

INSERT INTO project(
            id, statusid, isenable, pgname, description, logopath)
    VALUES (1, '0', 'Y', 'Bgile', 'Bgile not Agile', 'pg/000000000.jpg');

INSERT INTO projectaccount(
            id, projectid, accountid, permissionid)
    VALUES (1, 1, 2, '0');
