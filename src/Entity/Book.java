package Entity;

/**
 * @title: Book
 * @Auther: Yun
 * @Date: 5/8/23 17:16
 * @Version 1.0
 */

public class Book implements java.io.Serializable{
    private String title;
    private String author;
    private int price;

    private Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    /*
    Setter and Getter
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author= author;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /*
    Methods:
     */

    // 用于创建一个BookBuilder对象
    public static BookBuilder builder(){
        return new BookBuilder();
    }

    // 用于返回Book对象的信息
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Price($): " + price;
    }


    /*
    BookBuilder: 内部类
     */
    public static class BookBuilder {
        private String title;
        private String author;
        private int price;

        /*
        constructor
         */
        private BookBuilder() {}

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder price(int price) {
            this.price = price;
            return this;
        }

        /*
        inner class methods: build
         */
        public Book build() {
            return new Book(title, author, price);
        }
    }
}
