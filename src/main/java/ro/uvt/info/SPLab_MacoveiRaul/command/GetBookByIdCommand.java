package ro.uvt.info.SPLab_MacoveiRaul.command;

import java.util.Optional;

import ro.uvt.info.SPLab_MacoveiRaul.api.BookDto;

public class GetBookByIdCommand implements Command<Optional<BookDto>> {
    private final CommandContext context;

    public GetBookByIdCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public Optional<BookDto> execute() {
        return context.getBooksService().findById(context.getBookId());
    }
}
