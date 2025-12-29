import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManager {
    // field
    private Map<String, Book> bookStore; // <bookId, Book: class>로 구성된 Map
    private Map<String, Members> memberStore; // <memberId, Members: class>로 구성된 Map

    // 파일 경로 상수 지정
    private static final String BOOK_FILE_PATH = "books.txt";
    private static final String MEMBER_FILE_PATH = "members.txt";

    // constructor
    public LibraryManager() {
        this.bookStore = new HashMap<>();
        this.memberStore = new HashMap<>();

        loadData();
    }

    // books.txt와 members.txt를 로드하는 함수 묶음
    private void loadData(){
        loadBooks();
        loadMembers();
    }


    // books.txt와 members.txt를 갱신하고 저장하는 함수 묶음
    // saveBooks()와 saveMembers() 둘 중 하나라도 실패하면 오류 문구 출력
    public void saveData(){
        boolean bookSave = saveBooks();
        boolean memberSave = saveMembers();
        if (bookSave && memberSave) {
            System.out.println("save complete");
        }
        else {
            System.out.println("save error");
        }
    }

    // book file save
    // 경로의 파일에 book의 내용을 씀
    // 한 줄씩 저장(BufferedWriter)
    // java.io.Writer
    // 저장에 오류가 없다면 true 반환
    private boolean saveBooks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_FILE_PATH))) {
            writer.write("bookId,title,author,Year,holdings,isloaned\n");

            for (Book book : bookStore.values()) {
                String line = String.format("%s;%s;%s;%d;%d;%d", // 저장 형식
                        book.getBookId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getYear(),
                        book.getHoldings(),
                        book.getIsLoaned());
                writer.write(line + '\n');
            }
            return true;
        } catch (IOException e) { // 에러 처리 ( false 반환 )
            System.out.println("save error" + e.getMessage());
            return false;
        }
    }

    // book file load
    // 경로의 파일에서 내용을 읽어옴
    // 한 줄씩 읽어옴
    // 파일의 내용이 정상이라면(의도한 대로라면)
    // ';'를 구분자로 데이터를 배열에 저장
    private void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOK_FILE_PATH))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    String bookId = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    int holdings = Integer.parseInt(parts[4]);
                    int isLoaned = Integer.parseInt(parts[5]);
                    Book book = new Book(bookId, title, author, year, holdings); // 새로운 book 객체 생성
                    book.setIsLoaned(isLoaned); // 대출 정보는 따로 저장
                    bookStore.put(bookId, book); // bookID를 키로 해당하는 book객체를 value로 Map에 저장
                }

            }
        } catch (IOException e) { // 에러 처리
            System.out.println("File Not Found "+ e.getMessage());
            e.printStackTrace();
        }

    }
    
    // member file save
    // 기본적으로 saveBook()과 같음
    // 단, set으로 저장된 대출 도서 목록을 하나의 데이터로 결합하는 것이 선행
    // 저장에 오류가 없다면 true 반환
    private boolean saveMembers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBER_FILE_PATH))) {
            writer.write("memberId,name,contact,loanedBookIds\n");

            for (Members member : memberStore.values()) {
                // 대출목록 ';'구분자로 결합
                String loanedIds = String.join(";", member.getLoanedBookIds()); 

                String line = String.format("%s,%s,%s,%s",
                        member.getMemberId(),
                        member.getName(),
                        member.getContact(),
                        loanedIds);
                writer.write(line+'\n');
            }
            return true;
        } catch (IOException e) { // 에러 처리, false 반환
            System.out.println("save error"+e.getMessage());
            return false;
        }
    }

    // member file load
    // 기본적으로 loadBooks()와 같음
    // 단, 대출 도서 목록을 배열로서 나누어 저장
    private void loadMembers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MEMBER_FILE_PATH))) {
            reader.readLine();
            String line;

            while(((line = reader.readLine()) != null)) {
                // 대출 도서가 없더라도 parts의 길이를 유지하기 위해
                // split()의 limit 인자를 -1로 둠
                String[] parts = line.split(",", -1); 
                if(parts.length == 4) {
                    String memberId = parts[0];
                    String name = parts[1];
                    String contact = parts[2];
                    String loanedIdsString = parts[3];

                    Members member = new Members(memberId, name, contact);

                    if(!loanedIdsString.isEmpty()) { // loanedIdsString이 공백이 아니라면
                        String[] loanedIds = loanedIdsString.split(";"); // ';'를 구분자로 bookId를 배열로 저장
                        for (String bookId : loanedIds) {
                            if (!bookId.trim().isEmpty()) {
                                member.loanBook(bookId.trim());
                            }
                        }
                    }
                    memberStore.put(memberId, member);
                }
            }
            System.out.println("load complete");
        } catch (IOException e) {
            System.out.println("File Not Found " + e.getMessage());
        }
    }

    // book 등록
    // book객체를 인자로 받음
    // 인자의 bookID가 이미 bookStore에 존재한다면
    // 장서 수(holding)의 값을 증가시킨다.
    // 새로운 도서라면 bookStore에 저장
    public void registerBook(Book book) {
        String bookId = book.getBookId();

        if (bookStore.containsKey(bookId)) {
            Book existingBook = bookStore.get(bookId);

            existingBook.addHoldings();

            return;
        }
        bookStore.put(book.getBookId(), book);
        System.out.println("regist complete: "+ book.getTitle());
    }

    // book 삭제
    // bookID를 인잘 받음
    // bookID가 bookStore에 존재하지 않는다면
    // 에러 문구 출력
    // bookID가 bookStore에 존재한다면
    // 해당 bookId의 도서의 대출 상황을 검사
    // 해당 도서다 대출 중이라면
    // 삭제 불가
    // 앞선 상황에 해당되지 않는다면 bookStore에서 삭제
    public void deleteBook(String bookId) {
        if (!bookStore.containsKey(bookId)) {
            System.out.println("Error: Book ID Not Found");
            return;
        }
        Book book = bookStore.get(bookId);
        if (book.getIsLoaned() >0 ){
            System.out.println("Error: book is loaned, can't delete in list");
            return;
        }
        bookStore.remove(bookId);
        System.out.println("delete book complete: "+ book.getTitle());
    }

    // 도서 목록 출력
    // bookStore가 비었다면 오류 문구 출력
    // bookStore에 내용이 존재한다면 순차적으로 출력
    public void displayAllBooks() {
        if (bookStore.isEmpty()) {
            System.out.println("List Empty");
            return;
        }
        System.out.println("\n----------------------------------Book List("
                + bookStore.size() + ")----------------------------------");
        System.out.println("ID      Title                            Author                           H  L");
        System.out.println("-------|--------------------------------|--------------------------------|---|---");
        for (Book book : bookStore.values()) {
            System.out.printf("[%s] | %-30s | %-30s | %-1d | %d\n",
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getHoldings(),
                    book.getIsLoaned());
        }
        System.out.println("-------|--------------------------------|--------------------------------|---|---");
    }

    // 회원 등록
    // 기본적으로 registerBook()과 같음
    public void registerMember(Members member) {
        if (memberStore.containsKey(member.getMemberId())) {
            System.out.println("Error: member already exist");
            return;
        }
        memberStore.put(member.getMemberId(), member);
        System.out.println("regist complete: " + member.getName());
    }

    // 회원 삭제
    // 기본적으로 deleteBook()과 같음
    public void deleteMember(String memberId) {
        if (!memberStore.containsKey(memberId)) { // 입력받은 memberId가 memberStore에 없다면
            System.out.println("Error: Member Id Not Found");
        }
        Members member = memberStore.get(memberId);
        if(!member.getLoanedBookIds().isEmpty()) { // 해당 회원의 대출 도서 목록이 비어있지 않다면
            System.out.println("Error: Member has books loaned");
            return;
        }
        memberStore.remove(memberId);
        System.out.println("Delete complete: "+member.getName());
    }

    // 회원 목록 출력
    public void displayAllMembers() {
        // 회원 목록이 비어있다면 목록이 비었다는 구문 출력
        if(memberStore.isEmpty()) {
            System.out.println("Member List Empty");
            return;
        }
        System.out.println("\n-------------Member List(" + memberStore.size() + ")-------------");
        System.out.println("Id      Name         Contact           L");
        System.out.println("-------|------------|-----------------|--");
        for (Members member : memberStore.values()) {
            System.out.printf("[%s] | %-10s | %-15s | %-1d\n",
                    member.getMemberId(), member.getName(),member.getContact(), member.getLoanedBookIds().size());
        }
        System.out.println("-------|------------|-----------------|--");
    }

    // 도서 대출
    // 도서 Id와 멤버 Id를 인자로 받음
    public void loanBook(String bookId, String memberId) {
        // bookId에 해당하는 도서의 정보와 memberId에 해당하는 회원 정보 객체를 가져옴
        Book book = bookStore.get(bookId);
        Members member = memberStore.get(memberId);

        // 도서와 회원 둘 중 하나라도 없다면 오류 구문 출력
        if (book == null || member == null) {
            System.out.println("Error: Book Id Or Member Id Not Found");
            return;
        }

        // 해당 도서의 대출 가능한 도서가 0이라면 오류 구문 출력
        if(book.loanable() == 0) {
            System.out.printf("Error: '" + book.getTitle() + "' is all loaned");
            return;
        }

        // 도서의 대출 상태 갱신
        book.addIsLoaned();
        // 회원의 대출 도서 목록 갱신
        member.loanBook(bookId);
        System.out.println("Loan Complete: '" +book.getTitle() +
                "' is loaned by " + member.getName());
    }

    // 도서 반납
    public void returnBook(String bookId, String memberId) {
        Book book = bookStore.get(bookId);
        Members member = memberStore.get(memberId);

        if (book == null || member == null) {
            System.out.println("Error: Book Id Or Member Id Not Found");
            return;
        }

        // 해당 회원의 현재 대출 목록에 입력받은 도서Id가 없다면
        if (!member.getLoanedBookIds().contains(bookId)) {
            // 이미 반납했거나, 빌린적이 없음
            System.out.println("Error: Book is returned or has not be loaned before");
            return;
        }

        // 도서 대출 상태 갱신
        book.subIsLoaned();
        // 회원의 도서 대출 목록 갱신
        member.returnBook(bookId);
        System.out.println("Return complete: '" + book.getTitle() + "'is returned by " + member.getName());
    }

    // 전체 도서 중 대출 상태가 1 이상인 도서의 권 수
    // 도서 HashMap의 value 중 book 객체의 isLoaned가 1 이상인 것만 count
    public long getLoanedBookCount() {
        return bookStore.values().stream()
                .filter(book -> book.getIsLoaned() >= 1).count();
    }

    // sortId() 도서 Id기준 오름차순 정렬 함수
    // bookStore의 values의 Book객체의 bookID의 값을 기준으로 비교하여 오름차순으로 정렬한 결과를 List로 저장
    // 만들어진 리스트를 순회하며 결과 출력
    public void sortId() {
        if (bookStore.isEmpty()) {
            System.out.println("Book List Empty");
            return;
        }

        List<Book> sortedBooks = bookStore.values()
                .stream().sorted(Comparator.comparing(Book::getBookId))
                .toList();

        System.out.println("\n----------------------------Book List Sorted by Title----------------------------");
        System.out.println("ID      Title                            Author                           H  L");
        System.out.println("-------|--------------------------------|--------------------------------|---|---");
        for (Book book : sortedBooks) {
            System.out.printf("[%s] | %-30s | %-30s | %-1d | %d\n",
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getHoldings(),
                    book.getIsLoaned());
        }
        System.out.println("-------|--------------------------------|--------------------------------|---|---");

    }

    // sortTitle() 도서 제목 기준 오름차순 정렬 함수
    // bookStore의 values의 Book객체의 title의 값을 기준으로 비교하여 오름차순으로 정렬한 결과를 List로 저장
    // 만들어진 리스트를 순회하며 결과 출력
    public void sortTitle() {
        if (bookStore.isEmpty()) {
            System.out.println("Book List Empty");
            return;
        }

        List<Book> sortedBooks = bookStore.values()
                .stream().sorted(Comparator.comparing(Book::getTitle))
                .toList();

        System.out.println("\n----------------------------Book List Sorted by Title----------------------------");
        System.out.println("ID      Title                            Author                           H  L");
        System.out.println("-------|--------------------------------|--------------------------------|---|---");
        for (Book book : sortedBooks) {
            System.out.printf("[%s] | %-30s | %-30s | %-1d | %d\n",
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getHoldings(),
                    book.getIsLoaned());
        }
        System.out.println("-------|--------------------------------|--------------------------------|---|---");

    }

    // sortYear() 도서 출판연도 기준 오름차순 정렬 함수
    // bookStore의 values의 Book객체의 year의 값을 기준으로 비교하여 오름차순으로 정렬한 결과를 List로 저장
    // 만들어진 리스트를 순회하며 결과 출력
    public void sortYear() {
        if (bookStore.isEmpty()) {
            System.out.println("Book List Empty");
            return;
        }

        List<Book> sortedBooks = bookStore.values()
                .stream().sorted(Comparator.comparing(Book::getYear))
                .toList();

        System.out.println("\n-----------------------------Book List Sorted by Year-----------------------------");
        System.out.println("ID      Title                         Author                         Y      H  L");
        System.out.println("-------|-----------------------------|------------------------------|------|---|---");
        for (Book book : sortedBooks) {
            System.out.printf("[%s] | %-27s | %-28s | %d | %-1d | %d\n",
                    book.getBookId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getYear(),
                    book.getHoldings(),
                    book.getIsLoaned());
        }
        System.out.println("-------|-----------------------------|------------------------------|------|---|---");
    }

    // 도서 제목을 입력받으면 ID와 검색한 제목을 함께 출력
    public void searchBook(String keyword) {
        // 입력 값을 소문자로 변환
        String lowerKeyword = keyword.toLowerCase();
        // 도서 목록의 도서의 제목을 소문자로 변환한 것들 중 입력 값을 포함하고 있는 것만을 리스트로 모음
        List<Book> foundBooks = bookStore.values().stream()
                .filter(book->book.getTitle().toLowerCase()
                        .contains(lowerKeyword)).toList();

        System.out.println("\nSearch result: ");

        // 만들어진 리스트가 비어있다면 결과 없음을 나타내는 구문 출력
        if (foundBooks.isEmpty()) {
            System.out.println("Not Found");
        } else {
            // 만들어진 리스트를 순회하여 검색 결과 출력
            for (Book book : foundBooks) {
                System.out.printf("[%s] %s\n", book.getBookId(), book.getTitle());
            }
        }
    }

}
