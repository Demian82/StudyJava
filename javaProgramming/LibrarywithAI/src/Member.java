import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * 회원 정보를 담는 클래스
 */
public class Member {
    private String memberId;
    private String name;
    private String contact;
    // 한 회원은 여러 도서를 대출 가능 (대출한 도서 ID 목록)
    private Set<String> loanedBookIds;

    // 생성자
    public Member(String memberId, String name, String contact) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        this.loanedBookIds = new HashSet<>(); // HashSet 사용
    }

    // Getter
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public Set<String> getLoanedBookIds() { return loanedBookIds; }

    /**
     * 도서 ID를 대출 목록에 추가
     */
    public void loanBook(String bookId) {
        this.loanedBookIds.add(bookId);
    }

    /**
     * 도서 ID를 대출 목록에서 제거
     */
    public void returnBook(String bookId) {
        this.loanedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return "Member{" +
                "ID='" + memberId + '\'' +
                ", 이름='" + name + '\'' +
                ", 연락처='" + contact + '\'' +
                ", 대출 도서 수=" + loanedBookIds.size() +
                '}';
    }

    // memberId를 기준으로 동일성을 판단하기 위해 오버라이드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}