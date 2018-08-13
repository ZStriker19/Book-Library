-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

INSERT INTO book (book_id, title) VALUES (1, 'harry potter and the chamber of secrets');
INSERT INTO book (book_id, title) VALUES (2, 'harry potter and the prisoner of azkaban');
INSERT INTO book (book_id, title) VALUES (3, 'harry potter and the goblet of fire');
INSERT INTO book (book_id, title) VALUES (4, 'the great gatsby');
INSERT INTO book (book_id, title) VALUES (5, 'it''s not about the bike: my journey back to life');

SELECT setval(pg_get_serial_sequence('book', 'book_id'), 5);

INSERT INTO character (character_id, f_name, l_name) VALUES (1, 'harry', 'potter');
INSERT INTO character (character_id, f_name, l_name) VALUES (2, 'hermione', 'granger');
INSERT INTO character (character_id, f_name, l_name) VALUES (3, 'jay', 'gatsby');
INSERT INTO character (character_id, f_name, l_name) VALUES (4, 'nick', 'carraway');
INSERT INTO character (character_id, f_name, l_name) VALUES (5, 'lance', 'armstrong');

SELECT setval(pg_get_serial_sequence('character', 'character_id'), 5);

INSERT INTO location(location_id, city, country) VALUES (1, 'london', 'england');
INSERT INTO location(location_id, city, country) VALUES (2, 'new york city', 'united states');

SELECT setval(pg_get_serial_sequence('location', 'location_id'), 2);

INSERT INTO keyword (keyword_id, word) VALUES (1, 'fantasy');
INSERT INTO keyword (keyword_id, word) VALUES (2, 'fiction');
INSERT INTO keyword (keyword_id, word) VALUES (3, 'non-fiction');

SELECT setval(pg_get_serial_sequence('keyword', 'keyword_id'), 3);

INSERT INTO author (author_id, f_name, l_name) VALUES (1, 'j.k.', 'rowling');
INSERT INTO author (author_id, f_name, l_name) VALUES (2, 'f. scott', 'fitzgerald');
INSERT INTO author (author_id, f_name, l_name) VALUES (3, 'sally', 'jenkins');
INSERT INTO author (author_id, f_name, l_name) VALUES (4, 'lance', 'armstrong');

SELECT setval(pg_get_serial_sequence('author', 'author_id'), 4);

INSERT INTO book_character (book_id, character_id) VALUES (1, 1);
INSERT INTO book_character (book_id, character_id) VALUES (1, 2);
INSERT INTO book_character (book_id, character_id) VALUES (2, 1);
INSERT INTO book_character (book_id, character_id) VALUES (2, 2);
INSERT INTO book_character (book_id, character_id) VALUES (3, 1);
INSERT INTO book_character (book_id, character_id) VALUES (3, 2);
INSERT INTO book_character (book_id, character_id) VALUES (4, 3);
INSERT INTO book_character (book_id, character_id) VALUES (4, 4);
INSERT INTO book_character (book_id, character_id) VALUES (5, 5);

INSERT INTO book_location (book_id, location_id) VALUES (1, 1);
INSERT INTO book_location (book_id, location_id) VALUES (2, 1);
INSERT INTO book_location (book_id, location_id) VALUES (3, 1);
INSERT INTO book_location (book_id, location_id) VALUES (4, 2);
INSERT INTO book_location (book_id, location_id) VALUES (5, 2);

INSERT INTO book_keyword (book_id, keyword_id) VALUES (1, 1);
INSERT INTO book_keyword (book_id, keyword_id) VALUES (2, 1);
INSERT INTO book_keyword (book_id, keyword_id) VALUES (3, 1);
INSERT INTO book_keyword (book_id, keyword_id) VALUES (4, 2);
INSERT INTO book_keyword (book_id, keyword_id) VALUES (5, 3);

INSERT INTO book_author (book_id, author_id) VALUES (1, 1);
INSERT INTO book_author (book_id, author_id) VALUES (2, 1);
INSERT INTO book_author (book_id, author_id) VALUES (3, 1);
INSERT INTO book_author (book_id, author_id) VALUES (4, 2);
INSERT INTO book_author (book_id, author_id) VALUES (5, 3);
INSERT INTO book_author (book_id, author_id) VALUES (5, 4);




COMMIT;