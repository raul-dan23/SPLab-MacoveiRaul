package ro.uvt.info.SPLab_MacoveiRaul.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.uvt.info.SPLab_MacoveiRaul.api.BookDto;
import ro.uvt.info.SPLab_MacoveiRaul.entity.BookEntity;
import ro.uvt.info.SPLab_MacoveiRaul.repository.BookRepository;

@Service
@Transactional
public class BooksService {
    private final BookRepository repository;

    public BooksService(BookRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<BookDto> findAll() {
        return repository.findAllByOrderByIdAsc().stream().map(BooksService::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<BookDto> findById(Long id) {
        return repository.findById(id).map(BooksService::toDto);
    }

    public BookDto create(BookDto book) {
        // id-ul il genereaza baza de date; ignoram orice id trimis de client
        BookEntity saved = repository.save(new BookEntity(book.title(), book.author(), book.isbn()));
        return toDto(saved);
    }

    public Optional<BookDto> update(Long id, BookDto book) {
        // PUT inlocuieste cartea in intregime, dar pastreaza id-ul din cale
        return repository.findById(id).map(entity -> {
            entity.setTitle(book.title());
            entity.setAuthor(book.author());
            entity.setIsbn(book.isbn());
            return toDto(repository.save(entity));
        });
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    private static BookDto toDto(BookEntity e) {
        return new BookDto(e.getId(), e.getTitle(), e.getAuthor(), e.getIsbn());
    }
}
