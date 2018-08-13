-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

-- CREATE statements go here
DROP TABLE IF EXISTS book_author;
DROP TABLE IF EXISTS book_keyword;
DROP TABLE IF EXISTS book_location;
DROP TABLE IF EXISTS book_character;
DROP TABLE IF EXISTS keyword;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS character;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
  app_user_id SERIAL PRIMARY KEY,
  user_name varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  role varchar(32),
  salt varchar(255) NOT NULL
);

CREATE TABLE book (
  book_id SERIAL PRIMARY KEY,
  title varchar(150) NOT NULL UNIQUE
);

CREATE TABLE author (
  author_id SERIAL PRIMARY KEY,
  f_name varchar(50) NOT NULL,
  l_name varchar(50) NOT NULL
);

CREATE TABLE character (
  character_id SERIAL PRIMARY KEY,
  f_name varchar(50) NOT NULL,
  l_name varchar(50) NOT NULL
);

CREATE TABLE location (
  location_id SERIAL PRIMARY KEY,
  city varchar(150) NOT NULL,
  district varchar(150) NOT NULL, 
  country varchar(150) NOT NULL 
);

CREATE TABLE keyword (
  keyword_id SERIAL PRIMARY KEY,
  word varchar(50) NOT NULL
);

CREATE TABLE book_character (
    book_id integer NOT NULL,
    character_id integer NOT NULL,
    CONSTRAINT pk_book_character_book_id_character_id PRIMARY KEY (book_id, character_id),
    CONSTRAINT fk_book_characterBook_id foreign key (book_id) references book(book_id),
    CONSTRAINT fk_book_characterCharacter_id foreign key (character_id) references character(character_id)
);

CREATE TABLE book_location (
    book_id integer NOT NULL,
    location_id integer NOT NULL,
    CONSTRAINT pk_book_location_book_id_location_id PRIMARY KEY (book_id, location_id),
    CONSTRAINT fk_book_locationBook_id foreign key (book_id) references book(book_id),
    CONSTRAINT fk_book_locationLocation_id foreign key (location_id) references location(location_id)

);

CREATE TABLE book_keyword (
    book_id integer NOT NULL,
    keyword_id integer NOT NULL,
    CONSTRAINT pk_book_keyword_book_id_keyword_id PRIMARY KEY (book_id, keyword_id),
    CONSTRAINT fk_book_keywordBook_id foreign key (book_id) references book(book_id),
    CONSTRAINT fk_book_keywordKeyword_id foreign key (keyword_id) references keyword(keyword_id)
);

CREATE TABLE book_author (
    book_id integer NOT NULL,
    author_id integer NOT NULL,
    CONSTRAINT pk_book_author_book_id_author_id PRIMARY KEY (book_id, author_id),
    CONSTRAINT fk_book_authorBook_id foreign key (book_id) references book(book_id),
    CONSTRAINT fk_book_authorAuthor_id foreign key (author_id) references author(author_id)
);




COMMIT;