public class LibrarySystemMain {
    public static void main(String[] args) {
        // LibraryManager 생성자에서 loadData()가 호출됩니다.
        LibraryManager manager = new LibraryManager();

        // 파일이 없는 경우, 초기 데이터를 등록 (파일이 있는 경우, 이 데이터는 추가됩니다.)
        System.out.println("--- 1. 초기 도서 및 회원 등록 ---");
        manager.registerBook(new Book("B005", "새로운 책", "작가1", 2024));
        manager.registerMember(new Member("M003", "박영희", "010-5555-6666"));

        // ... (이전의 대출/반납, 정렬, 통계 등의 로직 실행) ...

        manager.loanBook("B005", "M003"); // 새로운 책 대출

        manager.displayAllBooks();
        manager.displayAllMembers();

        // --- 6. 프로그램 종료 전 데이터 저장 ---
        System.out.println("\n--- 6. 데이터 저장 ---");
        manager.saveData(); // 프로그램 종료 직전에 모든 변경 사항을 파일에 저장

        System.out.println("프로그램을 종료합니다.");
    }
}