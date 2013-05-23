DROP SEQUENCE IF EXISTS account_id_seq;
DROP SEQUENCE IF EXISTS board_id_seq;
DROP SEQUENCE IF EXISTS boardaccount_id_seq;
DROP SEQUENCE IF EXISTS userstory_id_seq;
DROP SEQUENCE IF EXISTS todo_id_seq;
DROP SEQUENCE IF EXISTS history_id_seq;

DROP TABLE IF EXISTS TODO;
DROP TABLE IF EXISTS USERSTORY;
DROP TABLE IF EXISTS BOARDACCOUNT;
DROP TABLE IF EXISTS BOARD;
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS HISTORY;

CREATE TABLE ACCOUNT
(
  ID            integer,
  TYPEID        character(1) NOT NULL,
  ENABLE        character(1) NOT NULL,
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


CREATE TABLE BOARD
(
  ID            integer,
  STATUSID      character(1) NOT NULL,
  ENABLE        character(1) NOT NULL,
  BOARDNAME	    character varying(128) NOT NULL,
  DESCRIPTION	character varying(512),
  LOGOPATH      character varying(256),

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT BOARD_PK PRIMARY KEY (ID)
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE BOARD     OWNER TO bgile;

CREATE SEQUENCE board_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE board_id_seq
  OWNER TO bgile;

CREATE TABLE BOARDACCOUNT
(
  ID            integer,
  ACCOUNTID     integer NOT NULL,
  BOARDID		integer NOT NULL,
  PERMISSIONID  character(1) NOT NULL,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT BOARD_ACCOUNT_PK PRIMARY KEY (ID),
  CONSTRAINT BOARD_ACCOUNT_FK1 FOREIGN KEY (BOARDID)
      REFERENCES BOARD (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT BOARD_ACCOUNT_FK2 FOREIGN KEY (ACCOUNTID)
      REFERENCES ACCOUNT (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT BOARDACCOUNT_IND0 UNIQUE (accountid, boardid)
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE BOARDACCOUNT     OWNER TO bgile;


CREATE SEQUENCE boardaccount_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE boardaccount_id_seq
  OWNER TO bgile;


CREATE TABLE USERSTORY
(
  ID            integer,
  BOARDID       integer NOT NULL,
  STORY         character varying(512) NOT NULL,
  OWNERID       integer,
  SORTORDER		integer,
  LOWEREST      character(1),
  UNDERID		integer,
  ESTIMATE      integer,
  STATEID       character(1) NOT NULL,
  STATUSID      character(1) NOT NULL,
  DESCRIPTION	text,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT USERSTORY_PK PRIMARY KEY (ID),
  CONSTRAINT USERSTORY_FK1 FOREIGN KEY (BOARDID)
      REFERENCES BOARD (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT USERSTORY_FK2 FOREIGN KEY (OWNERID)
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


CREATE TABLE HISTORY
(
  ID            integer,
  ACCOUNTID		integer,
  REFID			integer,
  ACTIONID		integer,
  REASON		text,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT HISTORY_PK PRIMARY KEY (ID)
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE history     OWNER TO bgile;


CREATE SEQUENCE history_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE history_id_seq
  OWNER TO bgile;


INSERT INTO account(
            id, typeid, enable, username, passwd, email, firstname, lastname, bio, avatarpath)
    VALUES (1, 'A', 'Y', 'admin', 'password', 'nuboat@gmail.com', 'Admin', '@ SIGNATURE', 'Default Admin of System', 'avatar/000000000.jpg');

INSERT INTO account(
            id, typeid, enable, username, passwd, email, firstname, lastname, bio, avatarpath)
    VALUES (2, 'S', 'T', 'nuboat', 'password', 'Admin', 'Peerapat', 'A', 'Trust me, I am engineer.', 'avatar/000000001.jpg');

INSERT INTO board(
            id, statusid, enable, boardname, description, logopath)
    VALUES (1, 'L', 'T', 'Bgile', 'Bgile not Agile', 'board/000000000.jpg');

INSERT INTO boardaccount(
            id, boardid, accountid, permissionid)
    VALUES (1, 1, 2, 'O');

INSERT INTO userstory(
            id, boardid, stateid, statusid, ownerid, LOWEREST, story)
    VALUES (1, 1, '0', 'L', 2, 'T','Test Story');
