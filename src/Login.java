import java.util.*;

public class Login {
    public static void main(String args[])throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nKindly choose your role");
        System.out.println("1.User");
        System.out.println("2.Admin");
        System.out.println("3.Stop Process");
        int role = sc.nextInt();
        sc.nextLine();
        while(role>3 || role <1){
            System.out.println("\nYou have entered an invalid number....Try Again\n");
            System.out.println("Kindly choose your role");
            System.out.println("1.User");
            System.out.println("2.Admin");
            System.out.println("3.Stop Process");
            role = sc.nextInt();
            sc.nextLine();
        }
        if (role == 1) {
            User temp=new User();
            temp.Options();
        }
        else if(role==2) {
            Admin temp=new Admin();
            temp.Options();
        }
        else if(role==3){
            System.out.println("\nThanks for using.....Good Bye!!\n");
        }
    }
}
