package com.techelevator.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.text.WordUtils;

public class Book {
	private long bookId;
	private String title;
	private String section;
	private Date dateAdded;
	private List<String> genres;
	private List<String> characterFirstNames;
	private List<String> characterLastNames;
	private List<String> authorFirstNames;
	private List<String> authorLastNames;
	
	
	public long getBookId() {
		return bookId;
	}
	public void setBook_id(long book_id) {
		this.bookId = book_id;
	}
	public String getTitle() {
		return WordUtils.capitalize(title);
	}	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSection() {
		return WordUtils.capitalize(section);
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public Date getDateAdded() {
		return dateAdded;
	}
	
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public List<String> getCharacterFirstNames() {
		for(int i = 0; i < characterFirstNames.size(); i++) {
	        characterFirstNames.set(i, WordUtils.capitalize(characterFirstNames.get(i)));
	      }
		return characterFirstNames;
	}
	public void setCharacterFirstNames(List<String> characterFirstNames) {
		this.characterFirstNames = characterFirstNames;
	}
	public List<String> getCharacterLastNames() {
		for(int i = 0; i < characterLastNames.size(); i++) {
	        characterLastNames.set(i, WordUtils.capitalize(characterLastNames.get(i)));
	      }
		return characterLastNames;
	}
	public void setCharacterLastNames(List<String> characterLastNames) {
		this.characterLastNames = characterLastNames;
	}
	public List<String> getAuthorFirstNames() {
		for(int i = 0; i < authorFirstNames.size(); i++) {
	        authorFirstNames.set(i, WordUtils.capitalize(authorFirstNames.get(i)));
	      }
		return authorFirstNames;
	}
	public void setAuthorFirstNames(List<String> authorFirstNames) {
		this.authorFirstNames = authorFirstNames;
	}
	public List<String> getAuthorLastNames() {
		for(int i = 0; i < authorLastNames.size(); i++) {
	        authorLastNames.set(i, WordUtils.capitalize(authorLastNames.get(i)));
	      }
		return authorLastNames;
	}
	public void setAuthorLastNames(List<String> authorLastNames) {
		this.authorLastNames = authorLastNames;
	}
	
	public String getAuthorFullNames() {
		String authors = "";
		for (int i = 0; i < authorFirstNames.size(); i++) {
			authors += WordUtils.capitalize(authorFirstNames.get(i)) + " " + WordUtils.capitalize(authorLastNames.get(i));
			if (i != authorFirstNames.size() -1) {
				authors += ", ";
			}
		}
		return authors;
	}
	
//	public boolean equals(Book otherBook) {
//		
//		if (otherBook.getBookId() == this.bookId) {
//			return true;
//		} 
//		return false;
//	}
//	
//	
//	public int hashCode() {
//		int bookIdInt = (int) this.bookId;
//        return bookIdInt;
//    }
	
	
}
