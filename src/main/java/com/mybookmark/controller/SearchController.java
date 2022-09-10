/**
 * 
 */
package com.mybookmark.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.mybookmark.book.SearchResult;
import com.mybookmark.book.SearchResultBook;

import reactor.core.publisher.Mono;

/**
 * @author Ganesh
 *
 */
@Controller
public class SearchController {

	private final WebClient webClient;
	
	private final String COVER_ID = "https://covers.openlibrary.org/b/id/"; 
	

	public SearchController(WebClient.Builder webClientBuilder) {

		this.webClient = webClientBuilder
				.exchangeStrategies(ExchangeStrategies.builder()
						.codecs(config -> config.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)).build())
				.baseUrl("http://openlibrary.org/search.json").build();

	}

	@GetMapping(value = "/search")
	public String getSearchResult(@RequestParam String searchKey, Model model) {
		System.out.println("... Inside search result ...");
		Mono<SearchResult> mono = this.webClient.get().uri("?q={searchKey}", searchKey).retrieve()
				.bodyToMono(SearchResult.class);

		SearchResult result = mono.block();
		List<SearchResultBook> books = result
				.getDocs()
				.stream()
				.limit(10)
				.map(bookResult ->{
					bookResult.setKey(bookResult.getKey().replace("/works", ""));
					String coverID = bookResult.getCover_i();
					if(StringUtils.hasText(coverID)) {
						coverID = COVER_ID + coverID +"-M.jpg" ;
					}else {
						coverID = "/images/no-image.jpg" ;
					}
					bookResult.setCover_i(coverID);
					return bookResult ;
				})
				.collect(Collectors.toList());
		if(books!=null && books.size()>0) {
			model.addAttribute("searchResult", books);
			return "search" ;
		}
		return "book-not-found";
	}
}
