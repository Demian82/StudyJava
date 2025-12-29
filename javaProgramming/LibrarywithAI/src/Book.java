import java.util.Objects;

/**
 * 도서 정보를 담는 클래스
 */
public class Book {
    private String bookId;
    private String title;
    private String author;
    private int publicationYear;
    private boolean isLoaned; // 대출 여부

    // 생성자
    public Book(String bookId, String title, String author, int publicationYear) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isLoaned = false; // 초기에는 대출 안 됨
    }

    // Getter
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isLoaned() { return isLoaned; }

    // Setter
    public void setLoaned(boolean loaned) { this.isLoaned = loaned; }

    @Override
    public String toString() {
        return "Book{" +
                "ID='" + bookId + '\'' +
                ", 제목='" + title + '\'' +
                ", 저자='" + author + '\'' +
                ", 연도=" + publicationYear +
                ", 대출 상태=" + (isLoaned ? "대출 중" : "가능") +
                '}';
    }

    // BookId를 기준으로 동일성을 판단하기 위해 오버라이드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }
}