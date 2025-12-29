import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    LibraryManager manager = new LibraryManager();

    // 도서관 시스템의 메뉴 루프 메서드
    public void runMenu() {
        while (true) {
            System.out.println("\nLibrary System");
            System.out.println("Menu: 1.Display Book List, 2.Display Member List\n" +
                    "3.Loan book, 4.Return book, 5.Manage Books\n" +
                    "6.Manage Member, 7.Sort, 8.Exit");
            System.out.print("Input: ");
            int input = Integer.parseInt(sc.nextLine());

            if (input == 1) {
                // 도서 목록 출력
                manager.displayAllBooks();
            }
            else if (input == 2) {
                // 회원 목록 출력
                manager.displayAllMembers();
            }
            else if (input == 3) {
                // 도서 대출
                // 대출할 도서의 ID와 도서를 대출하는 회원의 ID를 입력받아
                // 도서 대출 메서드에 전달
                System.out.print("Input Book Id: ");
                String bookId = sc.nextLine();
                System.out.print("Input Loan Member: ");
                String memberId = sc.nextLine();
                manager.loanBook(bookId, memberId);
                manager.saveData();
            }
            else if (input == 4) {
                // 도서 반납
                // 반납할 도서의 ID와 그 도서를 반납하는 회원의 ID를 입력받아
                // 도서 반납 메서드에 전달
                System.out.print("Input Book Id: ");
                String bookId = sc.nextLine();
                System.out.print("Input Loan Member: ");
                String memberId = sc.nextLine();
                manager.returnBook(bookId, memberId);
                manager.saveData();
            }
            else if (input == 5) {
                // 도서 관리 메뉴 모음
                // 1. 도서 등록, 2. 도서 삭제, 3. 현재 대출된 도서 수, 4. 도서 검색
                System.out.println("Manage Book: 1.Regist Book, 2.Delete Book, 3.Present loaned books, 4.Search Book");
                System.out.print("Input: ");
                int bookInput = Integer.parseInt(sc.nextLine());
                
                // 도서 등록
                if (bookInput == 1) {
                    System.out.print("Book info(Plz input like this -> Id;title;author;year;holdings)\n:");
                    String bookInfo = sc.nextLine();
                    
                    // 입력된 문자열을 ';'를 구분자로 나누어 배열로 저장
                    String[] bifs = bookInfo.split(";");
                    // 그 배열의 길이가 5라면 => 입력 형식에 문제가 없다면
                    if (bifs.length == 5) {
                        // 입력된 정보를 가진 새 Book 객체를 생성하고 그 객체를 등록
                        manager.registerBook(new Book(
                                bifs[0].trim(),
                                bifs[1].trim(),
                                bifs[2].trim(),
                                Integer.parseInt(bifs[3].trim()),
                                Integer.parseInt(bifs[4].trim())
                        ));
                    } else{
                        System.out.println("Error: Book info format error");
                    }
                    manager.saveData();

                }
                // 도서 삭제
                else if (bookInput == 2) {
                    System.out.print("Input Book Id to delete: ");
                    String bookId = sc.nextLine();
                    manager.deleteBook(bookId);
                    manager.saveData();
                }
                // 현재 대출된 도서 수
                else if(bookInput == 3) {
                    long loanedBooks = manager.getLoanedBookCount();
                    System.out.println("Present loaned books: " + loanedBooks);
                }
                // 도서 검색
                else if(bookInput == 4) {
                    System.out.print("Search book title: ");
                    String title = sc.nextLine();
                    manager.searchBook(title);
                }
                else {
                    System.out.println("Menu Error");
                }
            }
            else if (input == 6) {
                // 회원 관리
                // 1.회원 등록, 2.회원 삭제
                System.out.println("Manage Member: 1.Regist Member, 2.Delete Member");
                System.out.print("Input: ");
                int memberInput = Integer.parseInt(sc.nextLine());

                // 회원 등록
                if (memberInput == 1) {
                    System.out.print("Member info(Plz input like this -> Id;name;Contact)\n:");
                    String memberInfo = sc.nextLine();
                    
                    // 입력 문자열을 ';'를 구분자로 나누어 배열에 저장
                    String[] mifs = memberInfo.split(";");
                    // 배열의 길이가 3이라면 => 입력 형식에 문제가 없다면
                    if(mifs.length == 3) {
                        // 입력된 정보를 가진 새 Member 객체를 생성하고 그 객체를 등록
                        manager.registerMember(new Members(
                                mifs[0].trim(),
                                mifs[1].trim(),
                                mifs[2].trim()
                        ));
                    } 
                    else {
                        System.out.println("Error: Member info format error");
                    }
                    manager.saveData();
                } 
                else if (memberInput == 2) {
                    // 회원 삭제
                    System.out.print("Input member Id to delete: ");
                    String memberId = sc.nextLine();
                    manager.deleteMember(memberId);
                    manager.saveData();
                }
                else {
                    System.out.println("Menu Error");
                }
            }
            else if (input == 7) {
                // 정렬 메뉴 모음
                // 1.도서 Id 기준 정렬, 2.도서 제목 기준 정렬, 3.도서 출판연도 기준 정렬
                System.out.println("Sort menu: 1.Book Id, 2.Book Title, 3.Book publish year");
                System.out.print("Input: ");
                int sortInput = Integer.parseInt(sc.nextLine());

                if (sortInput == 1) {
                    manager.sortId();
                }
                else if (sortInput == 2) {
                    manager.sortTitle();
                }
                else if (sortInput == 3) {
                    manager.sortYear();
                }
                else {
                    System.out.println("Menu Error");
                }
            }
            else if (input == 8) {
                // 도서관 시스템 루프 종료
                // 루프 종료 전 데이터 저장
                manager.saveData();
                break;
            }
            else {
                System.out.println("Menu Error");
            }
        }
    }
}
