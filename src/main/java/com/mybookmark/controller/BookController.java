/**
 * 
 */
package com.mybookmark.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mybookmark.book.Book;
import com.mybookmark.book.BookRepository;

/**
 * @author Ganesh
 *
 */

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository ;
	
	private final String COVER_ID = "https://covers.openlibrary.org/b/id/"; 
	
	@GetMapping(value = "/books/{bookId}")
	public String getBook(@PathVariable String bookId, Model model) {
		System.out.println("... Inside Book Details ...");
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isPresent()) {
			Book book = optionalBook.get();
			if(book.getCoverIds()!= null && book.getCoverIds().size()>0) {
				String image = COVER_ID+book.getCoverIds().get(0)+"-L.jpg" ;
				model.addAttribute("coverid", image) ;
			}else {
				model.addAttribute("coverid", "/images/no-image.jpg");
			}
			model.addAttribute("book", book);
			return "book" ;
		}
		return "book-not-found" ;
	}
}
