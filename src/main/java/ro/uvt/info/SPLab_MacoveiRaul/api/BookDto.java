package ro.uvt.info.SPLab_MacoveiRaul.api;

// cartea expusa prin REST API; separata de model.Book (Composite), care descrie continutul unei carti
public record BookDto(Long id, String title, String author, String isbn) {

    public BookDto withId(Long newId) {
        return new BookDto(newId, title, author, isbn);
    }
}
