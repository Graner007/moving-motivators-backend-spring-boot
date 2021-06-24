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