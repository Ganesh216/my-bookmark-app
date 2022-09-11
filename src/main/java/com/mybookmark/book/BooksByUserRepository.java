/**
 * 
 */
package com.mybookmark.book;

/**
 * @author Ganesh
 *
 */

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BooksByUserRepository extends CassandraRepository<BooksByUser, String> {

    Slice<BooksByUser> findAllById(String id, Pageable pageable);
    
}
