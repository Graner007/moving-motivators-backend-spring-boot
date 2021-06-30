ALTER TABLE IF EXISTS ONLY public.person DROP CONSTRAINT IF EXISTS pk_person_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.question_group DROP CONSTRAINT IF EXISTS pk_question_group_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.question_group DROP CONSTRAINT IF EXISTS fk_person_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.card DROP CONSTRAINT IF EXISTS pk_card_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.card DROP CONSTRAINT IF EXISTS fk_question_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.card DROP CONSTRAINT IF EXISTS fk_empty_card_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.card DROP CONSTRAINT IF EXISTS fk_card_type_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.empty_card DROP CONSTRAINT IF EXISTS pk_empty_card_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.empty_card DROP CONSTRAINT IF EXISTS fk_card_type_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.card_type DROP CONSTRAINT IF EXISTS pk_card_type_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.question DROP CONSTRAINT IF EXISTS pk_question_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.question DROP CONSTRAINT IF EXISTS fk_question_group_id CASCADE;

ALTER TABLE IF EXISTS ONLY public.auth_role DROP CONSTRAINT IF EXISTS pk_auth_role_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.auth_role DROP CONSTRAINT IF EXISTS fk_person_id CASCADE;

DROP TABLE IF EXISTS public.person;
CREATE TABLE person (
                        id SERIAL NOT NULL,
                        username TEXT NOT NULL,
                        password TEXT NOT NULL,
                        registration_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS public.question_group;
CREATE TABLE question_group (
                                id SERIAL NOT NULL,
                                group_name TEXT NOT NULL,
                                person_id INTEGER NOT NULL
);

DROP TABLE IF EXISTS public.card;
CREATE TABLE card (
                      id SERIAL NOT NULL,
                      order_number INTEGER NOT NULL,
                      vertical_status_name TEXT NOT NULL,
                      question_id INTEGER NOT NULL,
                      empty_card_id INTEGER NOT NULL,
                      card_type_id INTEGER NOT NUll
);

DROP TABLE IF EXISTS public.empty_card;
CREATE TABLE empty_card (
                      id SERIAL NOT NULL,
                      vertical_status_name TEXT NOT NULL,
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
                          question_text TEXT NOT NULL,
                          question_group_id INTEGER NOT NULL,
                          finalized BOOLEAN NOT NULL DEFAULT FALSE
);

DROP TABLE IF EXISTS public.auth_role;
CREATE TABLE auth_role (
                           person_id INTEGER NOT NULL,
                           user_role TEXT NOT NULL
);

ALTER TABLE ONLY person
    ADD CONSTRAINT pk_person_id PRIMARY KEY (id);

ALTER TABLE ONLY question_group
    ADD CONSTRAINT pk_question_group_id PRIMARY KEY (id);

ALTER TABLE ONLY card_type
    ADD CONSTRAINT pk_card_type_id PRIMARY KEY (id);

ALTER TABLE ONLY question
    ADD CONSTRAINT pk_question_id PRIMARY KEY (id);

ALTER TABLE ONLY card
    ADD CONSTRAINT pk_card_id PRIMARY KEY (id);

ALTER TABLE ONLY empty_card
    ADD CONSTRAINT pk_empty_card_id PRIMARY KEY (id);

ALTER TABLE ONLY auth_role
    ADD CONSTRAINT pk_auth_role_id PRIMARY KEY (person_id, user_role);

ALTER TABLE ONLY question
    ADD CONSTRAINT fk_question_group_id FOREIGN KEY (question_group_id) REFERENCES question_group(id);

ALTER TABLE ONLY question_group
    ADD CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES person(id);

ALTER TABLE ONLY card
    ADD CONSTRAINT fk_question_id FOREIGN KEY (question_id) REFERENCES question(id);

ALTER TABLE ONLY card
    ADD CONSTRAINT fk_empty_card_id FOREIGN KEY (empty_card_id) REFERENCES empty_card(id);

ALTER TABLE ONLY card
    ADD CONSTRAINT fk_card_type_id FOREIGN KEY (card_type_id) REFERENCES card_type(id);

ALTER TABLE ONLY empty_card
    ADD CONSTRAINT fk_card_type_id FOREIGN KEY (card_type_id) REFERENCES card_type(id);

ALTER TABLE ONLY auth_role
    ADD CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES person(id);

INSERT INTO card_type VALUES (DEFAULT, 'curiosity', 'curiosity.png');
INSERT INTO card_type VALUES (DEFAULT, 'honor', 'honor.png');
INSERT INTO card_type VALUES (DEFAULT, 'acceptance', 'acceptance.png');
INSERT INTO card_type VALUES (DEFAULT, 'mastery', 'mastery.png');
INSERT INTO card_type VALUES (DEFAULT, 'power', 'power.png');
INSERT INTO card_type VALUES (DEFAULT, 'freedom', 'freedom.png');
INSERT INTO card_type VALUES (DEFAULT, 'relatedness', 'relatedness.png');
INSERT INTO card_type VALUES (DEFAULT, 'order', 'order.png');
INSERT INTO card_type VALUES (DEFAULT, 'goal', 'goal.png');
INSERT INTO card_type VALUES (DEFAULT, 'status', 'status.png');
INSERT INTO card_type VALUES (DEFAULT, 'default-image', 'default-image.png');