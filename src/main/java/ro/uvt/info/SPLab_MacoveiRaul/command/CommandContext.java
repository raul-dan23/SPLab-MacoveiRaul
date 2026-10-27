package ro.uvt.info.SPLab_MacoveiRaul.command;

import ro.uvt.info.SPLab_MacoveiRaul.api.BookDto;
import ro.uvt.info.SPLab_MacoveiRaul.service.BooksService;

// tot ce are nevoie o comanda ca sa ruleze: receiver-ul (BooksService) si parametrii din request
public class CommandContext {
    private final BooksService booksService;
    private final Long bookId;
    private final BookDto book;

    public CommandContext(BooksService booksService) {
        this(booksService, null, null);
    }

    public CommandContext(BooksService booksService, Long bookId) {
        this(booksService, bookId, null);
    }

    public CommandContext(BooksService booksService, BookDto book) {
        this(booksService, null, book);
    }

    public CommandContext(BooksService booksService, Long bookId, BookDto book) {
        this.booksService = booksService;
        this.bookId = bookId;
        this.book = book;
    }

    public BooksService getBooksService() {
        return booksService;
    }

    public Long getBookId() {
        return bookId;
    }

    public BookDto getBook() {
        return book;
    }
}
