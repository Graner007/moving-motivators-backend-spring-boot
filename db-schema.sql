ALTER TABLE IF EXISTS ONLY public.person DROP CONSTRAINT IF EXISTS pk_person_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.board DROP CONSTRAINT IF EXISTS pk_board_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.board DROP CONSTRAINT IF EXISTS fk_question_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.board DROP CONSTRAINT IF EXISTS fk_person_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.card DROP CONSTRAINT IF EXISTS pk_card_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.card DROP CONSTRAINT IF EXISTS fk_board_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.card DROP CONSTRAINT IF EXISTS fk_card_type_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.card_type DROP CONSTRAINT IF EXISTS pk_card_type_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.question DROP CONSTRAINT IF EXISTS pk_question_id CASCADE;

DROP TABLE IF EXISTS public.person;
CREATE TABLE person (
                        id SERIAL NOT NULL,
                        username TEXT NOT NULL,
                        email TEXT NOT NULL,
                        password TEXT NOT NULL,
                        registration_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS public.board;
CREATE TABLE board (
                       id SERIAL NOT NULL,
                       question_id INTEGER NOT NULL,
                       person_id INTEGER NOT NULL
);

DROP TABLE IF EXISTS public.card;
CREATE TABLE card (
                      id SERIAL NOT NULL,
                      order_number INTEGER NOT NULL,
                      vertical_status_name TEXT NOT NULL,
                      board_id INTEGER NOT NULL,
                      card_type_id INTEGER NOT NUll
);

DROP TABLE IF EXISTS public.card_type;
CREATE TABLE card_type (
                           id SERIAL NOT NULL,
                           description TEXT NOT NULL,
                           image_name TEXT NOT NULL
);

DROP TABLE IF EXISTS public.question;
CREATE TABLE question (
                          id SERIAL NOT NULL,
                          question_text TEXT NOT NULL
);

ALTER TABLE ONLY person
    ADD CONSTRAINT pk_person_id PRIMARY KEY (id);

ALTER TABLE ONLY board
    ADD CONSTRAINT pk_board_id PRIMARY KEY (id);

ALTER TABLE ONLY card_type
    ADD CONSTRAINT pk_card_type_id PRIMARY KEY (id);

ALTER TABLE ONLY question
    ADD CONSTRAINT pk_question_id PRIMARY KEY (id);

ALTER TABLE ONLY card
    ADD CONSTRAINT pk_card_id PRIMARY KEY (id);

ALTER TABLE ONLY board
    ADD CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES person(id);

ALTER TABLE ONLY board
    ADD CONSTRAINT fk_question_id FOREIGN KEY (question_id) REFERENCES question(id);

ALTER TABLE ONLY card
    ADD CONSTRAINT fk_board_id FOREIGN KEY (board_id) REFERENCES board(id);

ALTER TABLE ONLY card
    ADD CONSTRAINT fk_card_type_id FOREIGN KEY (card_type_id) REFERENCES card_type(id);

INSERT INTO person VALUES (DEFAULT, 'user', 'user@gmail.com', '12345', CURRENT_TIMESTAMP);

INSERT INTO card_type VALUES (DEFAULT, 'freedom', 'freedom.png');
INSERT INTO card_type VALUES (DEFAULT, 'goal', 'goal.png');
INSERT INTO card_type VALUES (DEFAULT, 'honor', 'honor.png');
INSERT INTO card_type VALUES (DEFAULT, 'order', 'order.png');
INSERT INTO card_type VALUES (DEFAULT, 'acceptance', 'acceptance.png');
INSERT INTO card_type VALUES (DEFAULT, 'curiosity', 'curiosity.png');
INSERT INTO card_type VALUES (DEFAULT, 'mastery', 'mastery.png');
INSERT INTO card_type VALUES (DEFAULT, 'power', 'power.png');
INSERT INTO card_type VALUES (DEFAULT, 'relatedness', 'relatedness.png');
INSERT INTO card_type VALUES (DEFAULT, 'status', 'status.png');

INSERT INTO question VALUES (DEFAULT, 'What do you want to do?');

INSERT INTO board VALUES (DEFAULT, 1, 1);

INSERT INTO card VALUES (DEFAULT, 1, 'natural', 1, 1);
INSERT INTO card VALUES (DEFAULT, 2, 'natural', 1, 2);
INSERT INTO card VALUES (DEFAULT, 3, 'natural', 1, 3);
INSERT INTO card VALUES (DEFAULT, 4, 'natural', 1, 4);
INSERT INTO card VALUES (DEFAULT, 5, 'natural', 1, 5);
INSERT INTO card VALUES (DEFAULT, 6, 'natural', 1, 6);
INSERT INTO card VALUES (DEFAULT, 7, 'natural', 1, 7);
INSERT INTO card VALUES (DEFAULT, 8, 'natural', 1, 8);
INSERT INTO card VALUES (DEFAULT, 9, 'natural', 1, 9);
INSERT INTO card VALUES (DEFAULT, 10, 'natural', 1, 10);