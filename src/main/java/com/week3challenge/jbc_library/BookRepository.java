package com.week3challenge.jbc_library;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
    //Iterable <Book> findAllByAvailabilityContaining(boolean availability);

    Iterable <Book> findAllByavailabilityContainingIgnoreCase(String availability);
    //Iterable<Book> findAllBy(boolean availability);
}
