import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

public class Update {
    public static void updateBook() throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.println("\nPlease select the field you want to change:");
        System.out.println("1.Book Name");
        System.out.println("2.Author Name");
        System.out.println("3.Availability Status");
        System.out.println("4.Borrower Student's id");
        System.out.println("5.Issue Date");
        System.out.println("6.Due Date");
        System.out.println("7.Go to Previous Menu");
        int opt=sc.nextInt();
        sc.nextLine();
        while(opt>7 || opt<1){
            System.out.println("\nYou have entered an invalid number....Try Again\n");
            System.out.println("Please select the field you want to change:");
            System.out.println("1.Book Name");
            System.out.println("2.Author Name");
            System.out.println("3.Availability Status");
            System.out.println("4.Borrower Student's id");
            System.out.println("5.Issue Date");
            System.out.println("6.Due Date");
            System.out.println("7.Go to Previous Menu");
            opt=sc.nextInt();
            sc.nextLine();
        }
        System.out.println("Enter Book ID:");
        String bookID=sc.nextLine();
        if(opt==1){
            System.out.println("Enter New Book Name:");
            String bookName=sc.nextLine();
            bookUpdate(bookID, bookName);
        }
        else if(opt==2){
            System.out.println("Enter New Author's Name:");
            String author=sc.nextLine();
            authorUpdate(bookID, author);
        }
        else if(opt==3){
            System.out.println("Enter New Satus:");
            String status=sc.nextLine();
            statusUpdate(bookID, status);
        }
        else if(opt==4){
            System.out.println("Enter New Borrower's Name:");
            String name=sc.nextLine();
            borrowerUpdate(bookID, name);
        }
        else if(opt==5){
            System.out.println("Enter New Issue Date:");
            String date=sc.nextLine();
            issueDateUpdate(bookID, date);
        }
        else if(opt==6){
            System.out.println("Enter New Due Date:");
            String date=sc.nextLine();
            dueDateUpdate(bookID, date);
        }
        else{
            Admin tmp=new Admin();
            tmp.Options();
        }
    }
    public static void updateStudent() throws Exception{
        Scanner sc=new Scanner(System.in);
        System.out.println("\nPlease select the field you want to change:");
        System.out.println("1.Update Fine Amount");
        System.out.println("2.Go to previous Menu");
        int opt=sc.nextInt();
        sc.nextLine();
        while(opt>2 || opt<1){
            System.out.println("\nYou have entered an invalid number....Try Again\n");
            System.out.println("Please select the field you want to change:");
            System.out.println("1.Update Fine Amount");
            System.out.println("2.Go to previous Menu");
            opt=sc.nextInt();
            sc.nextLine();
        }
        if(opt==1){
            System.out.println("Enter Student ID:");
            String studentID=sc.nextLine();
            System.out.println("Enter New Value:");
            int val=sc.nextInt();
            sc.nextLine();
            fineUpdate(studentID,val);
        }
        else{
            Admin tmp=new Admin();
            tmp.Options();
        }
    }
    public static void fineUpdate(String studentID, int Val) throws Exception{
        try{
            Admin cls=new Admin();
            if(!cls.isStudent(studentID)){
                System.out.println("There is No student with given ID\n\n");
                return;
            }
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "UPDATE students SET fine = "+"'"+ Val +"'"+" WHERE id = "+"'"+studentID+"'";
            PreparedStatement posted = con.prepareStatement(query);
            posted.executeUpdate(query);
            System.out.println("Information Updated Successfully!!\n\n");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void authorUpdate(String bookID, String Author) throws Exception{
        try{
            Admin cls=new Admin();
            if(!cls.isPresent(bookID)){
                System.out.println("There is No Book with given ID\n\n");
                return;
            }
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "UPDATE books SET author = "+"'"+ Author +"'"+" WHERE id = "+"'"+bookID+"'";
            PreparedStatement posted = con.prepareStatement(query);
            posted.executeUpdate(query);
            System.out.println("Information Updated Successfully!!\n\n");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void bookUpdate(String bookID, String bookName) throws Exception{
        try{
            Admin cls=new Admin();
            if(!cls.isPresent(bookID)){
                System.out.println("There is No Book with given ID\n\n");
                return;
            }
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "UPDATE books SET book_name = "+"'"+ bookName +"'"+" WHERE id = "+"'"+bookID+"'";
            PreparedStatement posted = con.prepareStatement(query);
            posted.executeUpdate(query);
            System.out.println("Information Updated Successfully!!\n\n");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void statusUpdate(String bookID, String status) throws Exception{
        try{
            Admin cls=new Admin();
            if(!cls.isPresent(bookID)){
                System.out.println("There is No Book with given ID\n\n");
                return;
            }
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "UPDATE books SET availability = "+"'"+ status +"'"+" WHERE id = "+"'"+bookID+"'";
            PreparedStatement posted = con.prepareStatement(query);
            posted.executeUpdate(query);
            System.out.println("Information Updated Successfully!!\n\n");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void borrowerUpdate(String bookID, String name) throws Exception{
        try{
            Admin cls=new Admin();
            if(!cls.isPresent(bookID)){
                System.out.println("There is No Book with given ID\n\n");
                return;
            }
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "UPDATE books SET issued_to = "+"'"+ name +"'"+" WHERE id = "+"'"+bookID+"'";
            PreparedStatement posted = con.prepareStatement(query);
            posted.executeUpdate(query);
            System.out.println("Information Updated Successfully!!\n\n");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void issueDateUpdate(String bookID, String date) throws Exception{
        try{
            Admin cls=new Admin();
            if(!cls.isPresent(bookID)){
                System.out.println("There is No Book with given ID\n\n");
                return;
            }
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "UPDATE books SET issue_date = "+"'"+ date +"'"+" WHERE id = "+"'"+bookID+"'";
            PreparedStatement posted = con.prepareStatement(query);
            posted.executeUpdate(query);
            System.out.println("Information Updated Successfully!!\n\n");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void dueDateUpdate(String bookID, String date) throws Exception{
        try{
            Admin cls=new Admin();
            if(!cls.isPresent(bookID)){
                System.out.println("There is No Book with given ID\n\n");
                return;
            }
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String query = "UPDATE books SET due_date = "+"'"+ date +"'"+" WHERE id = "+"'"+bookID+"'";
            PreparedStatement posted = con.prepareStatement(query);
            posted.executeUpdate(query);
            System.out.println("Information Updated Successfully!!\n\n");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
