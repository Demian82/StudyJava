class Book {
    // field
    private String bookId; // 도서 id
    private String title; // 도서 재목
    private String author; // 작가
    private int year; // 출판연도
    private int holdings; // 도서 권 수
    private int isLoaned; // 대출된 도서 권 수

    // constructor
    Book(String bookId, String title, String author, int year, int holdings) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
        this.holdings = holdings;
    }

    // getter
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public int getHoldings() { return holdings; }
    public int getIsLoaned() { return isLoaned; }

    // setter
    public void setIsLoaned(int isLoaned) { this.isLoaned = isLoaned;}
    public void setHoldings(int holdings) { this.holdings = holdings;}

    // method
    public int loanable() { // 대출 가능 권수
        int h_books = getHoldings();
        int l_books = getIsLoaned();
        return h_books - l_books;
    }

    // 대출
    public void addIsLoaned() {
        this.isLoaned++;
    }

    // 반납
    public void subIsLoaned() {
        this.isLoaned--;
    }

    // 도서 추가
    public void addHoldings() {
        int h_books = getHoldings();
        h_books++;
        setHoldings(h_books);
    }
}