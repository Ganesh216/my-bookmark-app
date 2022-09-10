/**
 * 
 */
package com.mybookmark.book;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ganesh
 *
 */
public class SearchResult {

	int numFound ;
	
	private List<SearchResultBook> docs = new ArrayList<>();

	/**
	 * @return the numFound
	 */
	public int getNumFound() {
		return numFound;
	}

	/**
	 * @param numFound the numFound to set
	 */
	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}

	/**
	 * @return the docs
	 */
	public List<SearchResultBook> getDocs() {
		return docs;
	}

	/**
	 * @param docs the docs to set
	 */
	public void setDocs(List<SearchResultBook> docs) {
		this.docs = docs;
	}
	
}
