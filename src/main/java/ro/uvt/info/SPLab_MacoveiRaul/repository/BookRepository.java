package ro.uvt.info.SPLab_MacoveiRaul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.uvt.info.SPLab_MacoveiRaul.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllByOrderByIdAsc();
}
