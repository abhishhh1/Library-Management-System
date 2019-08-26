import java.util.*;
import java.sql.*;

public class Admin {
    public static void Options() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWhich option do you want to perform?");
        System.out.println("1.Add a new Book");
        System.out.println("2.Delete a Book");
        System.out.println("3.Update data");
        System.out.println("4.Issue a Book");
        System.out.println("5.Return a Book");
        System.out.println("6.Go to previous Menu");
        int opt=sc.nextInt();
        sc.nextLine();
        while(opt>6 || opt <1){
            System.out.println("\nYou have entered an invalid number....Try Again\n");
            System.out.println("Which option do you want to perform?");
            System.out.println("1.Add a new Book");
            System.out.println("2.Delete a Book");
            System.out.println("3.Update a Book");
            System.out.println("4.Issue a Book");
            System.out.println("5.Return a Book");
            System.out.println("6.Go to previous Menu");
            opt = sc.nextInt();
            sc.nextLine();
        }
        if(opt==1){
            AddBook tmp=new AddBook();
            System.out.println("Enter Book Name:");
            String bookName=sc.nextLine();
            System.out.println("Enter Book Author Name:");
            String authorName=sc.nextLine();
            tmp.addBook(bookName,authorName);
        }
        else if(opt==2){
            DeleteBook tmp=new DeleteBook();
            System.out.println("Enter Book ID:");
            String bookID=sc.nextLine();
            tmp.deleteBook(bookID);
        }
        else if(opt==3){
            Update tmp=new Update();
            System.out.println("Select what you want to update:");
            System.out.println("1.Borrower Student data");
            System.out.println("2.Book Data");
            System.out.println("Press Anything else For previous Menu");
            int inp=sc.nextInt();
            sc.nextLine();
            if(inp==1){
                tmp.updateStudent();
            }
            else if(inp==2){
                tmp.updateBook();
            }
        }
        else if(opt==4){
            IssueBook tmp=new IssueBook();
            System.out.println("Enter Book ID:");
            String bookID=sc.nextLine();
            System.out.println("Enter Student ID:");
            String studentID=sc.nextLine();
            System.out.println("Enter Todays Date in DD/MM/YYYY format:");
            String date=sc.nextLine();
            tmp.issueBook(bookID,studentID,date);
        }
        else if(opt==5){
            ReturnBook tmp=new ReturnBook();
            System.out.println("Enter Book ID:");
            String bookID=sc.nextLine();
            System.out.println("Enter Student ID:");
            String studentID=sc.nextLine();
            System.out.println("Enter Todays Date in DD/MM/YYYY format:");
            String date=sc.nextLine();
            tmp.submitBook(bookID,studentID,date);
        }
        Login tmp=new Login();
        String args[]=new String[10];
        tmp.main(args);
    }

    public static boolean isPresent(String bookID) throws Exception{
        ConnectDB tmp = new ConnectDB();
        Connection con = tmp.getConnection();
        String query = "SELECT book_name FROM books WHERE id = " + "'" + bookID + "';";
        PreparedStatement posted = con.prepareStatement(query);
        ResultSet rs = posted.executeQuery(query);
        return rs.next();
    }

    public static boolean isStudent(String studentID) throws Exception{
        ConnectDB tmp = new ConnectDB();
        Connection con = tmp.getConnection();
        String query = "SELECT student_name FROM students WHERE id = " + "'" + studentID + "';";
        PreparedStatement posted = con.prepareStatement(query);
        ResultSet rs = posted.executeQuery(query);
        return rs.next();
    }

    public static boolean isAvailable(String bookID) throws Exception{
        try {
            ConnectDB tmp = new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "SELECT availability FROM books WHERE id = " + "'" + bookID + "';";
            PreparedStatement posted = con.prepareStatement(query);
            ResultSet rs = posted.executeQuery(query);
            if(rs.next()) {
                String sts = rs.getString("availability");
                return sts.equals("Available");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return false;
    }

    public static int totalBooks(String studentID) throws Exception{
        int total=0;
        try{
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "SELECT no_of_books FROM students WHERE id = '"+studentID+"';";
            PreparedStatement posted = con.prepareStatement(query);
            ResultSet rs = posted.executeQuery(query);
            if(rs.next()==false){
                System.out.println("No Student found with given ID\n\n");
            }
            else {
                total=rs.getInt("no_of_books");
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
        return total;
    }
}
