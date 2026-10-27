package ro.uvt.info.SPLab_MacoveiRaul.command;

import ro.uvt.info.SPLab_MacoveiRaul.api.BookDto;

public class CreateBookCommand implements Command<BookDto> {
    private final CommandContext context;

    public CreateBookCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public BookDto execute() {
        return context.getBooksService().create(context.getBook());
    }
}
