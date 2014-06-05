DROP SEQUENCE IF EXISTS account_id_seq;
DROP SEQUENCE IF EXISTS board_id_seq;
DROP SEQUENCE IF EXISTS boardaccount_id_seq;
DROP SEQUENCE IF EXISTS card_id_seq;
DROP SEQUENCE IF EXISTS burndown_id_seq;

DROP TABLE IF EXISTS TODO;
DROP TABLE IF EXISTS CARDORDER;
DROP TABLE IF EXISTS CARD;
DROP TABLE IF EXISTS AUTHENSESSION;
DROP TABLE IF EXISTS BOARDACCOUNT;
DROP TABLE IF EXISTS BOARD;
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS HISTORY;
DROP TABLE IF EXISTS BURNDOWN;
DROP TABLE IF EXISTS PROPERTY;


CREATE TABLE ACCOUNT
(
  ID            integer,
  TYPEID        character(1) NOT NULL,
  ENABLEID      character(1) NOT NULL,
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

  CONSTRAINT ACCOUNT_PK PRIMARY KEY (ID),
  CONSTRAINT ACCOUNT_UNI0 UNIQUE (USERNAME),
  CONSTRAINT ACCOUNT_UNI1 UNIQUE (EMAIL)
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

CREATE TABLE AUTHENSESSION
(
  ID            character(36) NOT NULL,
  ACCOUNT       integer NOT NULL,
  REMEMBERME    boolean NOT NULL, -- T / F

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT AUTHENSESSION_PK PRIMARY KEY (ID),
  CONSTRAINT AUTHENSESSION_ACCOUNT_FK2 FOREIGN KEY (ACCOUNT)
      REFERENCES ACCOUNT (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE AUTHENSESSION     OWNER TO bgile;

CREATE TABLE BOARD
(
  ID            integer,
  STATUSID      character(1) NOT NULL, -- L / D
  ENABLEID      character(1) NOT NULL, -- T / F
  PRIVATEID     character(1) NOT NULL, -- T / F
  BOARDNAME	    character varying(128) NOT NULL,
  DESCRIPTION	character varying(512),
  LOGOPATH      character varying(256),
  MAXCARD		integer,

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
  ACCOUNT       integer NOT NULL,
  BOARD  		integer NOT NULL,
  PERMISSIONID  character(1) NOT NULL, -- A / W / R
  STATUSID      character(1) NOT NULL,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT BOARD_ACCOUNT_PK PRIMARY KEY (ID),
  CONSTRAINT BOARD_ACCOUNT_FK1 FOREIGN KEY (BOARD)
      REFERENCES BOARD (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT BOARD_ACCOUNT_FK2 FOREIGN KEY (ACCOUNT)
      REFERENCES ACCOUNT (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT BOARDACCOUNT_IND0 UNIQUE (account, board)
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


CREATE TABLE CARD
(
  ID            integer,
  BOARD         integer NOT NULL,
  STORY         character varying(128) NOT NULL,
  OWNER         integer,
  ESTIMATE      integer,
  STATEID       integer NOT NULL,
  STATUSID      character(1) NOT NULL,
  DESCRIPTION	text,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT CARD_PK PRIMARY KEY (ID),
  CONSTRAINT CARD_FK1 FOREIGN KEY (BOARD)
      REFERENCES BOARD (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT CARD_FK2 FOREIGN KEY (OWNER)
      REFERENCES ACCOUNT (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE CARD     OWNER TO bgile;


CREATE SEQUENCE card_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE card_id_seq
  OWNER TO bgile;


CREATE TABLE CARDORDER
(
  ID            serial,
  BOARD         integer NOT NULL,
  STATEID       integer NOT NULL,
  ORDERBY		text,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT CARDORDER_PK PRIMARY KEY (ID),
  CONSTRAINT CARDORDER_FK1 FOREIGN KEY (BOARD)
      REFERENCES BOARD (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE CARDORDER     OWNER TO bgile;

CREATE TABLE TODO
(
  ID            serial,
  CARD			integer NOT NULL,
  DESCRIPTION   character varying(512) NOT NULL,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT TODO_PK PRIMARY KEY (ID),
  CONSTRAINT TODO_FK1 FOREIGN KEY (CARD)
      REFERENCES CARD (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE TODO     OWNER TO bgile;


CREATE TABLE HISTORY
(
  ID            serial,
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


CREATE TABLE BURNDOWN
(
  ID serial NOT NULL,
  BOARD integer,
  STATUSDATE date,
  ESTIMATE integer,
  DONE integer,
  CONSTRAINT BURNDOWN_PK PRIMARY KEY (ID)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE BURNDOWN	OWNER TO bgile;


CREATE TABLE PROPERTY
(
  ID	 character varying(64) NOT NULL,
  STRING character varying(512) NOT NULL,
  DESCRIPTION   character varying(512),

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT PROPERTY_PK PRIMARY KEY (ID)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE PROPERTY	OWNER TO bgile;


INSERT INTO account(
            id, typeid, enableid, username, passwd, email, firstname, lastname, bio, avatarpath, created, updated, updateby)
    VALUES (1, 'S', 'T', 'admin',  '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'nuboat@gmail.com'
			, 'Admin', '@ Thai Java User Group', 'Programmer @ THJUG', 'avatar/000000000.jpg', current_date, current_date, 1);
