package com.techelevator.model;

import java.util.List;

public class Book {
	private long bookId;
	private String title;
	private String city;
	private String country;
	private String keyword;
	private String characterFirstNames;
	private List<String> characterLastNames;
	private List<String> authorFirstNames;
	private String authorLastNames;
	
	
	public long getBookId() {
		return bookId;
	}
	public void setBook_id(long book_id) {
		this.bookId = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCharacterFirstName() {
		return characterFirstNames;
	}
	public void setCharacterFirstName(String characterFirstNames) {
		this.characterFirstNames = characterFirstNames;
	}
	public List<String> getCharacterLastNames() {
		return characterLastNames;
	}
	public void setCharacterLastNames(List<String> characterLastNames) {
		this.characterLastNames = characterLastNames;
	}
	public List<String> getAuthorFirstNames() {
		return authorFirstNames;
	}
	public void setAuthorFirstNames(List<String> authorFirstNames) {
		this.authorFirstNames = authorFirstNames;
	}
	public String getAuthorLastNames() {
		return authorLastNames;
	}
	public void setAuthorLastNames(String authorLastNames) {
		this.authorLastNames = authorLastNames;
	}
	
	

}
