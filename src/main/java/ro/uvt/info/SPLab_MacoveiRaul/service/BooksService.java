package ro.uvt.info.SPLab_MacoveiRaul.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ro.uvt.info.SPLab_MacoveiRaul.api.BookDto;

@Service
public class BooksService {
    // stocare in memorie; ConcurrentHashMap pentru ca request-urile vin pe thread-uri diferite
    private final Map<Long, BookDto> books = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<BookDto> findAll() {
        List<BookDto> result = new ArrayList<>(books.values());
        result.sort((a, b) -> a.id().compareTo(b.id()));
        return result;
    }

    public Optional<BookDto> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    public BookDto create(BookDto book) {
        // id-ul il genereaza serverul; ignoram orice id trimis de client
        BookDto saved = book.withId(nextId.getAndIncrement());
        books.put(saved.id(), saved);
        return saved;
    }

    public Optional<BookDto> update(Long id, BookDto book) {
        if (!books.containsKey(id)) {
            return Optional.empty();
        }
        // PUT inlocuieste cartea in intregime, dar pastreaza id-ul din cale
        BookDto saved = book.withId(id);
        books.put(id, saved);
        return Optional.of(saved);
    }

    public boolean delete(Long id) {
        return books.remove(id) != null;
    }
}
