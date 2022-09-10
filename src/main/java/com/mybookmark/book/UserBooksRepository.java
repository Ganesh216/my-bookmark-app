/**
 * 
 */
package com.mybookmark.book;

import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Ganesh
 *
 */
public interface UserBooksRepository extends CassandraRepository<UserBooks, UserBooksPrimaryKey>{

	
}
