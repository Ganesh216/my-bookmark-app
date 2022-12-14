/**
 * 
 */
package com.mybookmark.book;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @author Ganesh
 *
 */

@Table(value = "book")
public class Book {

	@Id @PrimaryKeyColumn(name = "book_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String id;
	
	@Column("book_name")
	@CassandraType(type = Name.TEXT)
	private String name ;
	
	@Column("book_description")
	@CassandraType(type = Name.TEXT)
	private String description ;
	
	@Column("published_date")
	@CassandraType(type = Name.DATE)
	private LocalDate publishedDate;
	
	@Column("cover_id")
	@CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
	private List<String> coverIds ;
	
	@Column("author_names")
	@CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
	private List<String> authorNames  ;
	
	@Column("author_id")
	@CassandraType(type = Name.LIST, typeArguments = Name.TEXT)
	private List<String> authorIds ;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the publishedDate
	 */
	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	/**
	 * @return the coverIds
	 */
	public List<String> getCoverIds() {
		return coverIds;
	}

	/**
	 * @param coverIds the coverIds to set
	 */
	public void setCoverIds(List<String> coverIds) {
		this.coverIds = coverIds;
	}

	/**
	 * @return the authorNames
	 */
	public List<String> getAuthorNames() {
		return authorNames;
	}

	/**
	 * @param authorNames the authorNames to set
	 */
	public void setAuthorNames(List<String> authorNames) {
		this.authorNames = authorNames;
	}

	/**
	 * @return the authorIds
	 */
	public List<String> getAuthorIds() {
		return authorIds;
	}

	/**
	 * @param authorIds the authorIds to set
	 */
	public void setAuthorIds(List<String> authorIds) {
		this.authorIds = authorIds;
	}
	
}
