import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddBook {
    public static void addBook(String book_name, String author) throws Exception{
        try{
            Admin adm=new Admin();
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            String status="Available";
            String bookID=null;
            for(int i=100; i<=1000; i++){
                if(!adm.isPresent(Integer.toString(i))){
                    bookID=Integer.toString(i);
                    break;
                }
            }
            PreparedStatement posted = con.prepareStatement("INSERT INTO books(book_name,author,id,availability,issued_to,"
                    +"issue_date,due_date)"+" VALUES('"+book_name+"','"+author+"','"+bookID+"','"+status+"',NULL,NULL,NULL);");
            posted.executeUpdate();
            System.out.println("Your book has been successfully added with Book ID: \n\n"+bookID);
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
