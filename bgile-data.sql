
TRUNCATE TABLE ACCOUNT CASCADE;
TRUNCATE TABLE BOARD CASCADE;
TRUNCATE TABLE BOARDACCOUNT CASCADE;
TRUNCATE TABLE CARD CASCADE;

INSERT INTO account(
            id, typeid, enableid, username, passwd, email, firstname, lastname, bio, avatarpath, created, updated, updateby)
    VALUES (2, 'S', 'T', 'nuboat', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'nuboat@gmail.com'
			, 'Peerapat', 'A', 'I''m Bgileboard Engineer.', 'avatar/000000001.jpg', current_date, current_date, 1);

INSERT INTO board(
            id, statusid, enableid, privateid, boardname, description, logopath, created, updated, updateby)
    VALUES (1, 'L', 'T', 'T', 'Bgile', 'Bgile not Agile', 'board/000000000.jpg', current_date, current_date, 2);

INSERT INTO boardaccount(
            id, board, account, permissionid, statusid, created, updated, updateby)
    VALUES (1, 1, 2, 'W', 'L', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (1, 1, 3, 'L', 2, 'Login Page by Bgile', 'Login with Bgile account.', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (2, 1, 1, 'L', 2, 'Login Page by Twitter', 'Login with Twitter account.', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, story, description, created, updated, updateby)
    VALUES (3, 1, 0, 'L', 'Login Page by Facebook', 'Login with Facebook account.', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, story, description, created, updated, updateby)
    VALUES (4, 1, 0, 'L', 'Login Page by Google+', 'Login with Google+ account.', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (5, 1, 2, 'L', 2, 'Board Page', 'Create Edit & Delete Board <br />Validation data', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (6, 1, 2, 'L', 2, 'Card Page', 'Create Edit & Delete Card <br />Validation data', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (7, 1, 3, 'L', 2, 'Dashboard Page', '1. Show Board', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (8, 1, 3, 'L', 2, 'Dashboard Page', '2. Link to Edit page', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (9, 1, 3, 'L', 2, 'Dashboard Page', '3. Link to Grant page', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (10, 1, 3, 'L', 2, 'Dashboard Page', '4. Link to Bgileboard', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (11, 1, 3, 'L', 2, 'Bgileboard Page', 'View Card', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, owner, story, description, created, updated, updateby)
    VALUES (12, 1, 2, 'L', 2, 'Bgileboard Page', 'Reorder Card', current_date, current_date, 2);

INSERT INTO card(
            id, board, stateid, statusid, story, description, created, updated, updateby)
    VALUES (13, 1, 0, 'L', 'Responsive Design', 'Support Responsive Design', current_date, current_date, 2);
