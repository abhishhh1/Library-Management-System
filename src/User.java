import java.util.*;
import java.sql.*;

public class User {
    Scanner sc = new Scanner(System.in);
    public static void Options()  throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWhich option do you want to perform?");
        System.out.println("1.Show all available Books");
        System.out.println("2.Search for a Book");
        System.out.println("3.Check all Books issued to your ID");
        System.out.println("4.Calculate Fine");
        System.out.println("5.Go to previous Menu");
        int opt=sc.nextInt();
        sc.nextLine();
        while(opt>5 || opt<1){
            System.out.println("\nYou have entered an invalid number....Try Again\n");
            System.out.println("Which option do you want to perform?");
            System.out.println("1.Show all available Books");
            System.out.println("2.Search for a Book");
            System.out.println("3.Check all Books issued to your ID");
            System.out.println("4.Calculate Fine");
            System.out.println("5.Go to previous Menu");
            opt=sc.nextInt();
            sc.nextLine();
        }
        if(opt==1){
            show();
        }
        if(opt==2){
            System.out.println("Enter Book Id:");
            String bookID=sc.nextLine();
            User tmp=new User();
            tmp.searchBook(bookID);
        }
        else if(opt==3){
            System.out.println("Enter Student Id:");
            String studentID=sc.nextLine();
            User tmp=new User();
            tmp.issuedBook(studentID);
        }
        else  if(opt==4){
            System.out.println("Enter Student Id:");
            String studentID=sc.nextLine();
            User tmp=new User();
            tmp.calculateFine(studentID);
        }
        Login tmp=new Login();
        String args[]=new String[10];
        tmp.main(args);
    }
    public static void show(){
        try{
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String status="Available";
            String query = "SELECT id, book_name,author FROM books WHERE availability = '"+status+"';";
            PreparedStatement posted = con.prepareStatement(query);
            ResultSet rs = posted.executeQuery(query);
            if (rs.next() == false) {
                System.out.println("No Books in data with given Book ID\n\n");
            }
            else {
                do {
                    String name = rs.getString("id");
                    String author = rs.getString("book_name");
                    String sttus = rs.getString("Author");
                    System.out.println("Book ID: "+name+"      Book Name: "+author+"     Author: "+sttus+"\n");
                } while (rs.next());
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void searchBook(String bookID) throws Exception{
        try{
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "SELECT book_name,author,availability FROM books WHERE id = '"+bookID+"';";
            PreparedStatement posted = con.prepareStatement(query);
            ResultSet rs = posted.executeQuery(query);
            if (rs.next() == false) {
                System.out.println("No Books in data with given Book ID\n\n");
            }
            else {
                do {
                    String name = rs.getString("book_name");
                    String author = rs.getString("author");
                    String status = rs.getString("availability");
                    System.out.println("Book Name: "+name+"     Author's Name: "+author+"      Availability: "+status+"\n");
                } while (rs.next());
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void issuedBook(String studentID) throws Exception{
        try{
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "SELECT book_name,issue_date,due_date FROM books WHERE issued_to = '"+studentID+"';";
            PreparedStatement posted = con.prepareStatement(query);
            ResultSet rs = posted.executeQuery(query);
            if (rs.next() == false) {
                System.out.println("No Books in data with given Student ID\n\n");
            }
            else {
                System.out.println("Your ID has following books issued:\n");
                do {
                    String name = rs.getString("book_name");
                    String author = rs.getString("issue_date");
                    String status = rs.getString("due_date");
                    System.out.println("Book Name: "+name+"          Issue Date: "+author+"        Due Date: "+status+"\n");
                } while (rs.next());
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void calculateFine(String  studentID) throws Exception{
        try{
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "SELECT fine FROM students WHERE id = '"+studentID+"';";
            PreparedStatement posted = con.prepareStatement(query);
            ResultSet rs = posted.executeQuery(query);
            if(rs.next()==false){
                System.out.println("No Student found with given ID\n\n");
            }
            else {
                System.out.println("Your total fine amount till today is " + rs.getInt("fine") + " rupees.\n\n");
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
