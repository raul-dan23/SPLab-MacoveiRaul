package ro.uvt.info.SPLab_MacoveiRaul.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.uvt.info.SPLab_MacoveiRaul.model.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
