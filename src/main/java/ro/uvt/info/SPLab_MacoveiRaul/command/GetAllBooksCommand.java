package ro.uvt.info.SPLab_MacoveiRaul.command;

import java.util.List;

import ro.uvt.info.SPLab_MacoveiRaul.api.BookDto;

public class GetAllBooksCommand implements Command<List<BookDto>> {
    private final CommandContext context;

    public GetAllBooksCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public List<BookDto> execute() {
        return context.getBooksService().findAll();
    }
}
