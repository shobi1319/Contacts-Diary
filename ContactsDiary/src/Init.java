import java.util.Scanner;

public class Init {




    public static void displayMenu(){
        Scanner sc= new Scanner(System.in);
        int choice;
        System.out.println("1. Search a Contact.");
        System.out.println("2. Add a new Contact");
        System.out.println("3. See Recent Contacts");
        System.out.println("4. Delete a Contact");
        System.out.println("5. See all Contacts");
        System.out.println("6. Delete all Contacts");

        System.out.println("7. Exit");
        choice= sc.nextInt();
        if(choice==1)
        {
            sc.nextLine();
             String n= "";
            System.out.print("Enter Name to Search: ");
              n=sc.nextLine();
        }
        else if (choice==2) {
          String n="";
          String num="";
            System.out.print("Enter Name: ");
            n=sc.nextLine();
            System.out.print("Enter Number: ");
            num=sc.nextLine();


            //do filing and save this along with sorting

        }
        else if (choice==3){
   // implement any data structure
            //show recent contacts added.
        }

        else if (choice==4){
            String n="";
            System.out.print("Enter Name: ");
            n=sc.nextLine();

            //search if exist then delete.
        }
        else if (choice==5){
            System.out.println("All Contacts: ");


            //show all contacts like a proper table.
        }
        else if (choice==6){
            System.out.println("Are you sure to Delete All Contacts. y/n");
            String choose = sc.next();
            if(choose.equals("y") || choose.equals("Y")){
                //delete all contacts
            }
            else{
                //come back
            }

        }

        else if (choice==7){
            System.exit(0);
        }

        else {
           System.out.flush();
            System.out.println("Invalid Choice\nEnter Again:");
            displayMenu();
        }

        //   System.out.println("1. Place a call.");
    }
}
