package com.polarbookshop.catalogservice.demo;

import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;

@Component
@Profile("testdata")
public class BookDataLoader {

    Logger log = LoggerFactory.getLogger(BookDataLoader.class);

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData() {
        bookRepository.deleteAll();;
        var now = Instant.now();
        var book1 = Book.of("1234567891", "Northern Lights", "Lyra Silver", 9.90, now, now);
        var book2 = Book.of("1234567892", "Polar Journey", "Iorek Polarson", 12.90, now, now);
        // bookRepository.save(book1);
        // bookRepository.save(book2);
        bookRepository.saveAll(List.of(book1, book2));
    }
}
