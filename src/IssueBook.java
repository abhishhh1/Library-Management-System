import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IssueBook {
    public static void issueBook(String bookID,String studentID, String date) throws Exception{
        try{
            ConnectDB tmp=new ConnectDB();
            Connection con = tmp.getConnection();
            Admin cls=new Admin();
            if(!cls.isStudent(studentID)){
                System.out.println("No Student in data with given Student ID\n\n");
                return;
            }
            if (!cls.isPresent(bookID)) {
                System.out.println("No Books in data with given Book ID\n\n");
                return;
            }
            if(!cls.isAvailable(bookID)){
                System.out.println("Book is Unavailable presently, Try after some days!!\n\n");
                return;
            }
            if(cls.totalBooks(studentID) == 4){
                System.out.println("You already have 4 books issued, Kindly submit one to issue new.\n\n");
                return;
            }

            String oldDate = date;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            try{
                c.setTime(sdf.parse(oldDate));
            }catch(ParseException e){
                e.printStackTrace();
            }
            c.add(Calendar.DAY_OF_MONTH, 15);
            String newDate = sdf.format(c.getTime());

            String upd = "UPDATE books SET availability ='Not Available'"+","+" issued_to ="+"'"+studentID+"'"+","
                    + "issue_date ="+"'"+date+"',"+"due_date="+"'"+newDate+"'"+" WHERE id ="+"'"+bookID+"';";

            PreparedStatement post = con.prepareStatement(upd);
            post.executeUpdate();


            String ins = "INSERT INTO issue_data(book_id,student_id,issue_date,due_date)"+
                    " VALUES("+"'"+bookID+"',"+"'"+studentID+"','"+date+"','"+newDate+"');";
            PreparedStatement pos = con.prepareStatement(ins);
            pos.executeUpdate();

            int no_book = cls.totalBooks(studentID);
            no_book++;

            String upd2 = "UPDATE students SET no_of_books ="+"'"+no_book+"'"+" WHERE id ="+"'"+studentID+"';";

            PreparedStatement post2 = con.prepareStatement(upd2);
            post2.executeUpdate();
            System.out.println("Book Issued Successfully!!\n\n");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
