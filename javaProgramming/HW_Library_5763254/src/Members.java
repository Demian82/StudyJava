import java.util.HashSet;
import java.util.Set;

class Members {
    // field
    private String memberId; // 회원 ID
    private String name; // 회원 이름
    private String contact; // 회원 연락처
    private Set<String> loanedBookIds; // 회원이 대출한 도서 목록, 책은 회원에게 1권씩만 대출될 수 있음(set)

    // constructor
    public Members(String memberId, String name, String contact)  {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        this.loanedBookIds = new HashSet<>();
    }

    // getter
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public Set<String> getLoanedBookIds() { return loanedBookIds; }

    // setter

    // method
    // 회원이 대출한 도서 추가
    public void loanBook(String bookId) {
        this.loanedBookIds.add(bookId);
    }

    // 회원의 대출 목록 요소 제거
    public void returnBook(String bookId) {
        this.loanedBookIds.remove(bookId);
    }
}
