package ro.uvt.info.SPLab_MacoveiRaul.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.uvt.info.SPLab_MacoveiRaul.api.BookDto;
import ro.uvt.info.SPLab_MacoveiRaul.command.CommandContext;
import ro.uvt.info.SPLab_MacoveiRaul.command.CommandInvoker;
import ro.uvt.info.SPLab_MacoveiRaul.command.CreateBookCommand;
import ro.uvt.info.SPLab_MacoveiRaul.command.DeleteBookCommand;
import ro.uvt.info.SPLab_MacoveiRaul.command.GetAllBooksCommand;
import ro.uvt.info.SPLab_MacoveiRaul.command.GetBookByIdCommand;
import ro.uvt.info.SPLab_MacoveiRaul.command.UpdateBookCommand;
import ro.uvt.info.SPLab_MacoveiRaul.service.BooksService;

// Client-ul din Command: creeaza comanda potrivita si o preda invoker-ului;
// nu apeleaza direct BooksService, doar il pune in CommandContext
@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final CommandInvoker invoker;

    // dependency injection prin constructor
    public BooksController(BooksService booksService, CommandInvoker invoker) {
        this.booksService = booksService;
        this.invoker = invoker;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        CommandContext context = new CommandContext(booksService);
        return ResponseEntity.ok(invoker.execute(new GetAllBooksCommand(context)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Long id) {
        CommandContext context = new CommandContext(booksService, id);
        return invoker.execute(new GetBookByIdCommand(context))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto book) {
        CommandContext context = new CommandContext(booksService, book);
        BookDto created = invoker.execute(new CreateBookCommand(context));
        return ResponseEntity.created(URI.create("/books/" + created.id())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto book) {
        CommandContext context = new CommandContext(booksService, id, book);
        return invoker.execute(new UpdateBookCommand(context))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        CommandContext context = new CommandContext(booksService, id);
        if (invoker.execute(new DeleteBookCommand(context))) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
