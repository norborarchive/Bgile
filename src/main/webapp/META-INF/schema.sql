DROP SEQUENCE IF EXISTS account_id_seq;
DROP SEQUENCE IF EXISTS board_id_seq;
DROP SEQUENCE IF EXISTS boardaccount_id_seq;
DROP SEQUENCE IF EXISTS card_id_seq;
DROP SEQUENCE IF EXISTS storyorder_id_seq;
DROP SEQUENCE IF EXISTS todo_id_seq;
DROP SEQUENCE IF EXISTS history_id_seq;

DROP TABLE IF EXISTS TODO;
DROP TABLE IF EXISTS STORYORDER;
DROP TABLE IF EXISTS CARD;
DROP TABLE IF EXISTS BOARDACCOUNT;
DROP TABLE IF EXISTS BOARD;
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS HISTORY;

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
  ENABLEID      character(1) NOT NULL, -- T / F
  PERMISSIONID  character(1) NOT NULL, -- P / S
  BOARDNAME	    character varying(128) NOT NULL,
  DESCRIPTION	character varying(512),
  LOGOPATH      character varying(256),
  MAXCARD		integer

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
  PERMISSIONID  character(1) NOT NULL,

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
  STORY         character varying(512) NOT NULL,
  OWNER         integer,
  ESTIMATE      integer,
  STATEID       character(1) NOT NULL,
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


CREATE TABLE STORYORDER
(
  ID            integer,
  BOARD         integer NOT NULL,
  STATEID       character varying(1) NOT NULL,
  ORDERBY		text,

  CREATED       timestamp with time zone,
  UPDATED       timestamp with time zone,
  UPDATEBY      integer,

  CONSTRAINT STORYORDER_PK PRIMARY KEY (ID),
  CONSTRAINT STORYORDER_FK1 FOREIGN KEY (BOARD)
      REFERENCES BOARD (ID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS = FALSE
) TABLESPACE pg_default;
ALTER TABLE STORYORDER     OWNER TO bgile;


CREATE SEQUENCE storyorder_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE storyorder_id_seq
  OWNER TO bgile;


CREATE TABLE TODO
(
  ID            integer,
  CARD     integer NOT NULL,
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
            id, typeid, enableid, username, passwd, email, firstname, lastname, bio, avatarpath)
    VALUES (1, 'A', 'Y', 'admin',  '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'nuboat@gmail.com', 'Admin', '@ SIGNATURE', 'Default Admin of System', 'avatar/000000000.jpg');

INSERT INTO account(
            id, typeid, enableid, username, passwd, email, firstname, lastname, bio, avatarpath)
    VALUES (2, 'S', 'T', 'nuboat', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'nuboat@gmail.com', 'Peerapat', 'A', 'Trust me, I am engineer.', 'avatar/000000001.jpg');

INSERT INTO board(
            id, statusid, enableid, permissionid, boardname, description, logopath)
    VALUES (1, 'L', 'T', 'Bgile', 'P', 'Bgile not Agile', 'board/000000000.jpg');

INSERT INTO boardaccount(
            id, board, account, permissionid)
    VALUES (1, 1, 2, 'O');

INSERT INTO card(
            id, board, stateid, statusid, story)
    VALUES (1, 1, '0', 'L', 'User Login Page');

INSERT INTO card(
            id, board, stateid, statusid, story)
    VALUES (2, 1, '0', 'L', 'User Profile Page');

INSERT INTO storyorder(
            id, board, stateid, orderby)
    VALUES (1, 1, '0', '1, 2,');
