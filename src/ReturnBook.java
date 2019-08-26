import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class ReturnBook {
    public static void submitBook(String bookID, String studentID, String date)throws Exception{
        try{
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            Admin cls=new Admin();
            if(!cls.isPresent(bookID)) {
                System.out.println("No Book in data with given Book ID\n\n");
                return;
            }
            if(cls.isAvailable(bookID)){
                System.out.println("Wrong Information or Book is Not Issued\n\n");
                return;
            }
            String query = "SELECT due_date FROM books WHERE id = "+"'"+bookID+"';";
            PreparedStatement posted= con.prepareStatement(query);
            ResultSet rs = posted.executeQuery(query);
            rs.next();
            String dueDate = rs.getString("due_date");


            SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateBeforeString = dueDate;
            String dateAfterString = date;
            int day=0;
            try{
                java.util.Date dateBefore = myFormat.parse(dateBeforeString);
                java.util.Date dateAfter = myFormat.parse(dateAfterString);
                long difference = dateAfter.getTime() - dateBefore.getTime();
                float daysBetween = (difference / (1000*60*60*24))-1;
                day = Math.round(daysBetween);

            } catch (Exception e) {
                e.printStackTrace();
            }
            if(day>0){
                System.out.printf("You crossed due date by %d day\n",day);
                System.out.printf("Applicable fine for this is Rs. %d, i.e.(5rs per day)\n",(day)*5);
                String query2 = "SELECT fine FROM students WHERE id = '"+studentID+"';";
                PreparedStatement posted2 = con.prepareStatement(query2);
                ResultSet rs2 = posted2.executeQuery(query2);
                rs2.next();
                int prev_fine=rs2.getInt("fine");
                int temp = prev_fine+(day)*5;

                String upd3 = "UPDATE students SET fine ="+temp+" WHERE id ="+"'"+studentID+"';";
                PreparedStatement post3 = con.prepareStatement(upd3);
                post3.executeUpdate();
            }

            String upd = "UPDATE books SET availability ='Available', issued_to = NULL, issue_date = NULL, due_date = NULL where id ='"+bookID+"';";

            PreparedStatement post = con.prepareStatement(upd);
            post.executeUpdate();


            String ins ="DELETE FROM issue_data WHERE book_id ="+"'"+bookID+"';";
            PreparedStatement pos = con.prepareStatement(ins);
            pos.executeUpdate();
            int no_book = cls.totalBooks(studentID);
            no_book--;

            String upd2 = "UPDATE students SET no_of_books ="+"'"+no_book+"'"+" WHERE id ="+"'"+studentID+"';";
            PreparedStatement post2 = con.prepareStatement(upd2);
            post2.executeUpdate();
            System.out.println("Book Returned Successfully!!\n\n");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
