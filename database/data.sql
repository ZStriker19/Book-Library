-- *****************************************************************************
-- This script contains INSERT statements for populating tables with seed data
-- *****************************************************************************

BEGIN;

INSERT INTO book (book_id, title) VALUES (1, 'harry potter and the sorcerer''s stone');
INSERT INTO book (book_id, title) VALUES (2, 'harry potter and the chamber of secrets');
INSERT INTO book (book_id, title) VALUES (3, 'harry potter and the prisoner of azkaban');
INSERT INTO book (book_id, title) VALUES (4, 'harry potter and the goblet of fire');
INSERT INTO book (book_id, title) VALUES (5, 'harry potter and the order of the phoenix');
INSERT INTO book (book_id, title) VALUES (6, 'harry potter and the half-blood prince');
INSERT INTO book (book_id, title) VALUES (7, 'harry potter and the deathly hallows');
INSERT INTO book (book_id, title) VALUES (8, 'the great gatsby');
INSERT INTO book (book_id, title) VALUES (9, 'it''s not about the bike: my journey back to life');
INSERT INTO book (book_id, title) VALUES (10, 'it''s all about the bike: the pursuit of happiness on two wheels');

SELECT setval(pg_get_serial_sequence('book', 'book_id'), 10);

INSERT INTO character (character_id, f_name, l_name) VALUES (1, 'harry', 'potter');
INSERT INTO character (character_id, f_name, l_name) VALUES (2, 'hermione', 'granger');
INSERT INTO character (character_id, f_name) VALUES (3, 'voldemort');
INSERT INTO character (character_id, f_name, l_name) VALUES (4, 'jay', 'gatsby');
INSERT INTO character (character_id, f_name, l_name) VALUES (5, 'nick', 'carraway');
INSERT INTO character (character_id, f_name, l_name) VALUES (6, 'lance', 'armstrong');
INSERT INTO character (character_id, f_name, l_name) VALUES (7, 'robert', 'penn');

SELECT setval(pg_get_serial_sequence('character', 'character_id'), 7);

INSERT INTO location (location_id, section) VALUES (1, 'A1');
INSERT INTO location (location_id, section) VALUES (2, 'A2');
INSERT INTO location (location_id, section) VALUES (3, 'A3');
INSERT INTO location (location_id, section) VALUES (4, 'A4');
INSERT INTO location (location_id, section) VALUES (5, 'A5');
INSERT INTO location (location_id, section) VALUES (6, 'A6');
INSERT INTO location (location_id, section) VALUES (7, 'A7');
INSERT INTO location (location_id, section) VALUES (8, 'A8');
INSERT INTO location (location_id, section) VALUES (9, 'A9');
INSERT INTO location (location_id, section) VALUES (10, 'A10');


SELECT setval(pg_get_serial_sequence('location', 'location_id'), 10);

INSERT INTO genre (genre_id, genre) VALUES (1, 'fantasy');
INSERT INTO genre (genre_id, genre) VALUES (2, 'fiction');
INSERT INTO genre (genre_id, genre) VALUES (3, 'autobiography');
INSERT INTO genre (genre_id, genre) VALUES (4, 'sci-fi');
INSERT INTO genre (genre_id, genre) VALUES (5, 'mystery');
INSERT INTO genre (genre_id, genre) VALUES (6, 'thriller');
INSERT INTO genre (genre_id, genre) VALUES (7, 'children');
INSERT INTO genre (genre_id, genre) VALUES (8, 'horror');
INSERT INTO genre (genre_id, genre) VALUES (9, 'romance');
INSERT INTO genre (genre_id, genre) VALUES (10, 'comic books');

SELECT setval(pg_get_serial_sequence('genre', 'genre_id'), 10);

INSERT INTO author (author_id, f_name, l_name) VALUES (1, 'j.k.', 'rowling');
INSERT INTO author (author_id, f_name, l_name) VALUES (2, 'f. scott', 'fitzgerald');
INSERT INTO author (author_id, f_name, l_name) VALUES (3, 'sally', 'jenkins');
INSERT INTO author (author_id, f_name, l_name) VALUES (4, 'lance', 'armstrong');
INSERT INTO author (author_id, f_name, l_name) VALUES (5, 'robert', 'penn');

SELECT setval(pg_get_serial_sequence('author', 'author_id'), 5);

INSERT INTO book_character (book_id, character_id) VALUES (1, 1);
INSERT INTO book_character (book_id, character_id) VALUES (1, 2);
INSERT INTO book_character (book_id, character_id) VALUES (1, 3);
INSERT INTO book_character (book_id, character_id) VALUES (2, 1);
INSERT INTO book_character (book_id, character_id) VALUES (2, 2);
INSERT INTO book_character (book_id, character_id) VALUES (2, 3);
INSERT INTO book_character (book_id, character_id) VALUES (3, 1);
INSERT INTO book_character (book_id, character_id) VALUES (3, 2);
INSERT INTO book_character (book_id, character_id) VALUES (3, 3);
INSERT INTO book_character (book_id, character_id) VALUES (4, 1);
INSERT INTO book_character (book_id, character_id) VALUES (4, 2);
INSERT INTO book_character (book_id, character_id) VALUES (4, 3);
INSERT INTO book_character (book_id, character_id) VALUES (5, 1);
INSERT INTO book_character (book_id, character_id) VALUES (5, 2);
INSERT INTO book_character (book_id, character_id) VALUES (5, 3);
INSERT INTO book_character (book_id, character_id) VALUES (6, 1);
INSERT INTO book_character (book_id, character_id) VALUES (6, 2);
INSERT INTO book_character (book_id, character_id) VALUES (6, 3);
INSERT INTO book_character (book_id, character_id) VALUES (7, 1);
INSERT INTO book_character (book_id, character_id) VALUES (7, 2);
INSERT INTO book_character (book_id, character_id) VALUES (7, 3);
INSERT INTO book_character (book_id, character_id) VALUES (8, 4);
INSERT INTO book_character (book_id, character_id) VALUES (8, 5);
INSERT INTO book_character (book_id, character_id) VALUES (9, 6);
INSERT INTO book_character (book_id, character_id) VALUES (10, 7);

INSERT INTO book_location (book_id, location_id) VALUES (1, 1);
INSERT INTO book_location (book_id, location_id) VALUES (2, 1);
INSERT INTO book_location (book_id, location_id) VALUES (3, 1);
INSERT INTO book_location (book_id, location_id) VALUES (4, 1);
INSERT INTO book_location (book_id, location_id) VALUES (5, 1);
INSERT INTO book_location (book_id, location_id) VALUES (6, 1);
INSERT INTO book_location (book_id, location_id) VALUES (7, 1);
INSERT INTO book_location (book_id, location_id) VALUES (8, 2);
INSERT INTO book_location (book_id, location_id) VALUES (9, 3);
INSERT INTO book_location (book_id, location_id) VALUES (10, 3);

INSERT INTO book_genre (book_id, genre_id) VALUES (1, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (2, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (3, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (4, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (5, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (6, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (7, 1);
INSERT INTO book_genre (book_id, genre_id) VALUES (8, 2);
INSERT INTO book_genre (book_id, genre_id) VALUES (9, 3);
INSERT INTO book_genre (book_id, genre_id) VALUES (10, 3);

INSERT INTO book_author (book_id, author_id) VALUES (1, 1);
INSERT INTO book_author (book_id, author_id) VALUES (2, 1);
INSERT INTO book_author (book_id, author_id) VALUES (3, 1);
INSERT INTO book_author (book_id, author_id) VALUES (4, 1);
INSERT INTO book_author (book_id, author_id) VALUES (5, 1);
INSERT INTO book_author (book_id, author_id) VALUES (6, 1);
INSERT INTO book_author (book_id, author_id) VALUES (7, 1);
INSERT INTO book_author (book_id, author_id) VALUES (8, 2);
INSERT INTO book_author (book_id, author_id) VALUES (9, 3);
INSERT INTO book_author (book_id, author_id) VALUES (9, 4);
INSERT INTO book_author (book_id, author_id) VALUES (10, 5);

COMMIT;