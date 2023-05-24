import Entity.Book;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /*
    create a list to store book information
     */
    private static List<Book> list;

    /*
    Main function
     */
    public static void main(String[] args) {
        /*
        UI
         */
        System.out.println("Welcome to the Library Management System!");
        System.out.println("Loading book information...");

        /*
        case 0 : load book information, check if there is a file named "data"
         */
        load();

        //Create a Scanner object
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============Library Management System===========");
            System.out.println("1. Enter book information");
            System.out.println("2. Access to book information");
            System.out.println("3. Delete book information");
            System.out.println("4. Modify book information");
            System.out.println("5. Exiting");
            System.out.println("=================================================");

            switch (scanner.nextInt()) {
                case 1 ->
                    /*
                    Entering book information
                     */{
                    insert(scanner);
                    break;
                }
                case 2 ->
                    /*
                    Access to book information
                     */
                        list(list);
                case 3 ->
                    /*
                    Deleting book information
                     */
                        delete(scanner);
                case 4 -> {
                    /*
                    Modify book information
                     */
                    modify(scanner);
                }
                case 5 -> {
                    /*
                    Exiting the system
                     */
                    System.out.println("Save book information...");
                    save();
                    System.out.println("Exiting the system, bye bye!");
                    return;
                }
                default -> {
                    System.out.println("Invalid input, press enter to continue");
                    //System.out.println("Press Enter to continue");
                    System.out.println("===============================================");
                }
            }
        }
    }

    /*
    case 0 : load book information, check if there is a file named "data"
     */
    @SuppressWarnings("unchecked")
    private static void load() {
        File file = new File("data");
        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))){
                list = (List<Book>) stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            list = new LinkedList<>();
        }
    }

    /*
    Case 1: Entering book information
     */
    public static void insert(Scanner scanner){
        System.out.print("Entering book's title: ");
        scanner.nextLine(); // Consume the newline character
        String title = scanner.nextLine();

        System.out.print("Entering book's author: ");
        String author = scanner.nextLine();

        System.out.print("Entering book's price: ");
        int price = scanner.nextInt();

        /*
        创建一个Book对象
         */
//        scanner.nextLine(); // 用于接收输入的回车
        Book book = Book.builder()
                .title(title)
                .author(author)
                .price(price)
                .build(); // 用于创建一个Book对象
        //scanner.nextLine(); // 用于接收输入的回车
        list.add(book);
        System.out.println("Successfully added: " + book);  // 用于返回Book对象的信息
    }

    /*
    Case 2: Access to book information
     */
    public static void list(List<Book> list){
        //System.out.println("Access to book information");
        for (int i = 0; i < list.size(); i++){
            System.out.println((i+1) + "." + list.get(i));
        }
    }

    /*
    Case 3: Deleting book information
     */
    public static void delete(Scanner scanner){
        //System.out.println("Deleting book information");
        scanner.nextLine();
        System.out.print("Please enter the ID of the book you want to delete: ");
        int index = 1;
        scanner.nextLine();
        while (index < 1 || index > list.size()){
            System.out.println("Invalid input, please try again");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        list.remove(index - 1);
        System.out.println("Successfully deleted");
    }

    /*
    Case 4: Modify book information
     */
    public static void modify(Scanner scanner){
        System.out.print("Please enter the ID of the book you want to modify: ");
        scanner.nextLine();
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index < 1 || index > list.size()){
            System.out.println("Invalid input, please try again");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        //modify
        Book book = list.get(index - 1);
        System.out.print("Please enter the new title of the book: ");
        book.setTitle(scanner.nextLine());
        System.out.print("Please enter the new author of the book: ");
        book.setAuthor(scanner.nextLine());
        System.out.print("Please enter the new price of the book: ");
        book.setPrice(scanner.nextInt());
        scanner.nextLine(); // 用于吃掉上一次输入的回车

        System.out.println("Successfully modified !");

    }

    /*
    case 5 :Save book information
     */
    public static void save() {
        //System.out.println("Save book information");
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("data"))){
            stream.writeObject(list);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}