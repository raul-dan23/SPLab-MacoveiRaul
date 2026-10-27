package ro.uvt.info.SPLab_MacoveiRaul.command;

// rezultatul spune daca a existat o carte de sters
public class DeleteBookCommand implements Command<Boolean> {
    private final CommandContext context;

    public DeleteBookCommand(CommandContext context) {
        this.context = context;
    }

    @Override
    public Boolean execute() {
        return context.getBooksService().delete(context.getBookId());
    }
}
