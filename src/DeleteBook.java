import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeleteBook {
    public static void deleteBook(String bookID) throws Exception{
        try{
            Admin adm=new Admin();
            if(!adm.isPresent(bookID)){
                System.out.println("Opps!! Book is doesn't exist in our Library!!\n\n");
                return;
            }
            else{
                ConnectDB tmp=new ConnectDB();
                Connection con = tmp.getConnection();
                String query1 = "DELETE FROM books WHERE id = "+"'"+bookID+"'";
                PreparedStatement posted1 = con.prepareStatement(query1);
                posted1.executeUpdate();

                String query2 = "SELECT student_id FROM issue_data WHERE book_id = "+"'"+bookID+"'";
                PreparedStatement posted2 = con.prepareStatement(query2);
                ResultSet rs = posted2.executeQuery(query2);
                if(rs.next()){
                    do{
                        String query4 = "SELECT no_of_books FROM students WHERE id = "+"'"+rs.getString("student_id")+"'";
                        PreparedStatement posted3 = con.prepareStatement(query4);
                        ResultSet nob=posted3.executeQuery(query4);
                        int tmpp=nob.getInt("no_of_books");
                        String query5 = "UPDATE students SET no_of_books = "+"+ tmpp-1 +"+" WHERE id = "+"'"+rs.getString("student_id")+"'";
                        PreparedStatement posted4 = con.prepareStatement(query5);
                        posted4.executeUpdate(query5);
                    }while(rs.next());
                }

                String query3 = "DELETE FROM issue_data WHERE book_id = "+"'"+bookID+"'";
                PreparedStatement posted5 = con.prepareStatement(query3);
                posted5.executeUpdate();
                System.out.println("All data related to Book deleted successfully!!\n\n");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
