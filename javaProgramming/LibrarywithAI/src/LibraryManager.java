import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/**
 * 도서관 관리 시스템의 핵심 로직을 담는 클래스
 * - 도서 및 회원 관리를 Map으로 수행
 * - 파일 저장 및 로드 기능을 포함
 */
public class LibraryManager {
    // 도서 ID를 키로, Book 객체를 값으로 저장
    private Map<String, Book> bookStore;
    // 회원 ID를 키로, Member 객체를 값으로 저장
    private Map<String, Member> memberStore;

    // 파일 경로 정의
    private static final String BOOK_FILE_PATH = "books.txt";
    private static final String MEMBER_FILE_PATH = "members.txt";

    public LibraryManager() {
        this.bookStore = new HashMap<>();
        this.memberStore = new HashMap<>();
        // 프로그램 시작 시 파일에서 데이터 로드
        loadData();
    }

    // --- 파일 입출력 기능 ---

    /**
     * 모든 데이터를 파일에 저장 (프로그램 종료 시 호출)
     */
    public void saveData() {
        saveBooks();
        saveMembers();
    }

    /**
     * 파일에서 모든 데이터를 로드 (프로그램 시작 시 호출)
     */
    private void loadData() {
        loadBooks();
        loadMembers();
    }

    /**
     * 1. 도서 데이터 파일 저장 (CSV 형식)
     */
    private void saveBooks() {
        // try-with-resources 구문으로 자동으로 close() 처리
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_FILE_PATH))) {
            // 헤더 작성
            writer.write("bookId,title,author,publicationYear,isLoaned\n");

            for (Book book : bookStore.values()) {
                // 제목/저자에 콤마가 있을 경우 데이터 파싱 오류를 막기 위해 공백으로 대체
                String line = String.format("%s,%s,%s,%d,%b",
                        book.getBookId(),
                        book.getTitle().replace(",", " "),
                        book.getAuthor().replace(",", " "),
                        book.getPublicationYear(),
                        book.isLoaned());
                writer.write(line + "\n");
            }
            System.out.println("💾 도서 데이터가 " + BOOK_FILE_PATH + "에 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("❌ 도서 데이터 저장 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 2. 도서 데이터 파일 로드
     */
    private void loadBooks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOK_FILE_PATH))) {
            reader.readLine(); // 헤더 줄 건너뛰기
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 5) {
                    try {
                        String bookId = parts[0];
                        String title = parts[1];
                        String author = parts[2];
                        int year = Integer.parseInt(parts[3]);
                        boolean isLoaned = Boolean.parseBoolean(parts[4]);

                        Book book = new Book(bookId, title, author, year);
                        book.setLoaned(isLoaned);
                        bookStore.put(bookId, book);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 도서 파일 파싱 오류 (숫자 형식): " + line);
                    }
                }
            }
            System.out.println("✅ 도서 데이터 " + bookStore.size() + "권이 " + BOOK_FILE_PATH + "에서 로드되었습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("ℹ️ " + BOOK_FILE_PATH + " 파일이 없어 새 데이터로 시작합니다.");
        } catch (IOException e) {
            System.out.println("❌ 도서 데이터 로드 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 3. 회원 데이터 파일 저장 (CSV 형식)
     */
    private void saveMembers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBER_FILE_PATH))) {
            // 헤더: memberId,name,contact,loanedBookIds (도서 ID는 세미콜론으로 구분)
            writer.write("memberId,name,contact,loanedBookIds\n");

            for (Member member : memberStore.values()) {
                // 대출 중인 도서 ID를 세미콜론(;)으로 구분하여 하나의 문자열로 저장
                String loanedIds = String.join(";", member.getLoanedBookIds());

                String line = String.format("%s,%s,%s,%s",
                        member.getMemberId(),
                        member.getName().replace(",", " "),
                        member.getContact(),
                        loanedIds);
                writer.write(line + "\n");
            }
            System.out.println("💾 회원 데이터가 " + MEMBER_FILE_PATH + "에 성공적으로 저장되었습니다.");
        } catch (IOException e) {
            System.out.println("❌ 회원 데이터 저장 중 오류 발생: " + e.getMessage());
        }
    }

    /**
     * 4. 회원 데이터 파일 로드
     */
    private void loadMembers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MEMBER_FILE_PATH))) {
            reader.readLine(); // 헤더 줄 건너뛰기
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 4) {
                    String memberId = parts[0];
                    String name = parts[1];
                    String contact = parts[2];
                    String loanedIdsString = parts[3];

                    Member member = new Member(memberId, name, contact);

                    // 대출 도서 ID를 Member 객체에 다시 로드
                    if (!loanedIdsString.isEmpty()) {
                        String[] loanedIds = loanedIdsString.split(";");
                        for (String bookId : loanedIds) {
                            if (!bookId.trim().isEmpty()) {
                                // Member 클래스의 loanBook 메소드를 사용하여 Set에 추가
                                member.loanBook(bookId.trim());
                            }
                        }
                    }
                    memberStore.put(memberId, member);
                }
            }
            System.out.println("✅ 회원 데이터 " + memberStore.size() + "명이 " + MEMBER_FILE_PATH + "에서 로드되었습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("ℹ️ " + MEMBER_FILE_PATH + " 파일이 없어 새 데이터로 시작합니다.");
        } catch (IOException e) {
            System.out.println("❌ 회원 데이터 로드 중 오류 발생: " + e.getMessage());
        }
    }

    // --- 도서 기능 ---

    /**
     * 도서 등록
     */
    public void registerBook(Book book) {
        if (bookStore.containsKey(book.getBookId())) {
            System.out.println("⚠️ 오류: 이미 존재하는 도서 ID입니다. 등록 실패.");
            return;
        }
        bookStore.put(book.getBookId(), book);
        System.out.println("✅ 도서가 성공적으로 등록되었습니다: " + book.getTitle());
    }

    /**
     * 도서 삭제 (ID 기준)
     */
    public void deleteBook(String bookId) {
        if (!bookStore.containsKey(bookId)) {
            System.out.println("⚠️ 오류: 해당 ID의 도서를 찾을 수 없습니다. 삭제 실패.");
            return;
        }
        Book book = bookStore.get(bookId);
        if (book.isLoaned()) {
            System.out.println("⚠️ 오류: 해당 도서는 대출 중이므로 삭제할 수 없습니다.");
            return;
        }
        bookStore.remove(bookId);
        System.out.println("✅ 도서가 성공적으로 삭제되었습니다: " + book.getTitle());
    }

    /**
     * 전체 도서 출력
     */
    public void displayAllBooks() {
        if (bookStore.isEmpty()) {
            System.out.println("ℹ️ 현재 등록된 도서가 없습니다.");
            return;
        }
        System.out.println("\n--- 전체 도서 목록 (" + bookStore.size() + "권) ---");
        for (Book book : bookStore.values()) {
            System.out.println(book);
        }
        System.out.println("---------------------------------");
    }

    /**
     * 도서 검색 (제목 또는 저자에 키워드 포함)
     */
    public List<Book> searchBooks(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        List<Book> foundBooks = bookStore.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(lowerKeyword) ||
                        b.getAuthor().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());

        System.out.println("\n🔍 '" + keyword + "' 검색 결과: " + foundBooks.size() + "건");
        if (foundBooks.isEmpty()) {
            System.out.println("ℹ️ 검색 결과가 없습니다.");
        } else {
            foundBooks.forEach(System.out::println);
        }
        return foundBooks;
    }


    // --- 회원 기능 ---

    /**
     * 새 회원 등록
     */
    public void registerMember(Member member) {
        if (memberStore.containsKey(member.getMemberId())) {
            System.out.println("⚠️ 오류: 이미 존재하는 회원 ID입니다. 등록 실패.");
            return;
        }
        memberStore.put(member.getMemberId(), member);
        System.out.println("✅ 회원이 성공적으로 등록되었습니다: " + member.getName());
    }

    /**
     * 회원 삭제 (ID 기준)
     */
    public void deleteMember(String memberId) {
        if (!memberStore.containsKey(memberId)) {
            System.out.println("⚠️ 오류: 해당 ID의 회원을 찾을 수 없습니다. 삭제 실패.");
            return;
        }
        Member member = memberStore.get(memberId);
        if (!member.getLoanedBookIds().isEmpty()) {
            System.out.println("⚠️ 오류: 대출 중인 도서가 있는 회원은 삭제할 수 없습니다.");
            return;
        }
        memberStore.remove(memberId);
        System.out.println("✅ 회원이 성공적으로 삭제되었습니다: " + member.getName());
    }

    /**
     * 전체 회원 목록 출력
     */
    public void displayAllMembers() {
        if (memberStore.isEmpty()) {
            System.out.println("ℹ️ 현재 등록된 회원이 없습니다.");
            return;
        }
        System.out.println("\n--- 전체 회원 목록 (" + memberStore.size() + "명) ---");
        for (Member member : memberStore.values()) {
            System.out.println(member);
        }
        System.out.println("---------------------------------");
    }

    // --- 대출/반납 기능 ---

    /**
     * 도서 대출
     */
    public void loanBook(String bookId, String memberId) {
        Book book = bookStore.get(bookId);
        Member member = memberStore.get(memberId);

        if (book == null) {
            System.out.println("⚠️ 오류: 도서 ID를 찾을 수 없습니다.");
            return;
        }
        if (member == null) {
            System.out.println("⚠️ 오류: 회원 ID를 찾을 수 없습니다.");
            return;
        }

        // 도서 1권은 동시에 한 명에게만 대출 가능 (규칙 2)
        if (book.isLoaned()) {
            System.out.println("⚠️ 오류: '" + book.getTitle() + "' 도서는 이미 대출 중입니다.");
            return;
        }

        book.setLoaned(true);
        member.loanBook(bookId); // 한 회원은 여러 도서 대출 가능 (규칙 1)
        System.out.println("✅ 대출 성공: '" + book.getTitle() + "'을(를) '" + member.getName() + "'에게 대출했습니다.");
    }

    /**
     * 도서 반납
     */
    public void returnBook(String bookId, String memberId) {
        Book book = bookStore.get(bookId);
        Member member = memberStore.get(memberId);

        if (book == null || member == null) {
            System.out.println("⚠️ 오류: 도서 ID 또는 회원 ID를 찾을 수 없습니다.");
            return;
        }

        if (!book.isLoaned() || !member.getLoanedBookIds().contains(bookId)) {
            System.out.println("⚠️ 오류: 해당 도서는 이 회원에게 대출되지 않았거나 이미 반납되었습니다.");
            return;
        }

        book.setLoaned(false);
        member.returnBook(bookId);
        System.out.println("✅ 반납 성공: '" + book.getTitle() + "'이(가) '" + member.getName() + "'으로부터 반납되었습니다.");
    }

    // --- 정렬 및 통계 기능 ---

    /**
     * 도서 정렬 기능
     * @param sortBy "title" (제목 오름차순), "year" (출판연도 내림차순)
     */
    public List<Book> sortBooks(String sortBy) {
        List<Book> sortedList = new ArrayList<>(bookStore.values());

        Comparator<Book> comparator;
        switch (sortBy.toLowerCase()) {
            case "title": // 제목 오름차순
                comparator = Comparator.comparing(Book::getTitle);
                System.out.println("\n➡️ 도서 목록: 제목 오름차순 정렬");
                break;
            case "year": // 출판연도 내림차순
                comparator = Comparator.comparing(Book::getPublicationYear).reversed();
                System.out.println("\n➡️ 도서 목록: 출판연도 내림차순 정렬");
                break;
            default:
                System.out.println("⚠️ 오류: 지원하지 않는 정렬 기준입니다.");
                return sortedList;
        }

        sortedList.sort(comparator);
        sortedList.forEach(System.out::println);
        return sortedList;
    }

    /**
     * 통계 기능: 현재 대출 중인 도서 수 반환
     */
    public long getLoanedBookCount() {
        long count = bookStore.values().stream()
                .filter(Book::isLoaned)
                .count();
        System.out.println("\n📊 통계: 현재 대출 중인 도서 수: " + count + "권");
        return count;
    }
}