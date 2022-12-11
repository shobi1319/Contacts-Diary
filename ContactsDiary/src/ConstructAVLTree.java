import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
class ConstructAVLTree
{
    Node rootNode;
    int count;
    Node inserted;
    contactNode recentH, recentT; //recent track record
    int r_size;

    //Constructor to set null value to the rootNode
    public ConstructAVLTree()
    {
        rootNode = null;
        inserted=null;
        r_size=0;
        recentT=null;
        recentH=null;

    }
    void Operation(Node temp){
        Scanner sc= new Scanner(System.in);

        if(temp==null){
            System.out.println("not found");
        }else if(temp.name.equalsIgnoreCase("como estas cha hal aa how are you")){}

        else {

            int i = 1;
            int x=-1;
            while(x!=i+1){
                i=1;

                System.out.println(temp.name);
                for (contactNode t = temp.head; t != null; t = t.next, i++) {
                     if(!t.number.isEmpty())
                    System.out.println(i + " :  select a number : " + t.number);
                }
                i--;
                System.out.println();
                System.out.println(i+1+" : back");
                System.out.println(i+2+" : exit");
                System.out.println(i+3+" : delete the contact");
                System.out.println(i+4+" : add a number to this contact");
                System.out.print("press a key to select : ");
                x= sc.nextInt();
                if(x==i+1){
                    break;
                }
                else if(x==i+4)
                {
                    System.out.print("enter number : ");
                    try{
                        contactNode formality=temp.head.next.next.next;
                        System.out.println("sorry ,number can,t be added because of full size");
                        break;
                    }catch(Exception e) {
                        temp.insertNumber(sc.next());
                    }


                } else if (x==i+2) {
                    treetofile();
                    System.exit(0);
                }else if(x==i+3){
                    deleteNode(this.rootNode,temp.name);
                    System.out.println("contact deleted ......");
                    x=i+2;
                }
                else if(x>i)
                {
                    System.out.println("invalid input");
                    System.out.println("enter again  ");
                }
                else
                {

                    System.out.println("1:place a call to that number");
                    System.out.println("2:delete that number");
                    System.out.println("press any key for back");
                    System.out.print("press key to select option : ");
                    i=sc.nextInt();
                    if(i==1){
                        System.out.println("1:call is placing");
                        add_rec(temp.name);
                        System.out.println("press any key to cancel call");
                        sc.next();
                        break;

                    }else if(i==2)
                    {

                        if(x==1){temp.head=temp.head.next;temp.head.prev=null;}
                        else if(x==2){temp.head.next=temp.head.next.next;temp.head.next.prev=temp.head;}else {temp.head.next.next=null;}
                        System.out.println("number deleted");
                        break;
                    }
                    else {
                        break;
                    }}}}}


    //contact addition
    Node addContact(){
        Scanner sc=new Scanner(System.in);

        Node temp=null;
        String n = "";
        String num = "";

        System.out.print("Enter Name: ");
        n = sc.nextLine();
        if(search(rootNode,n)==null) {
            System.out.print("Enter Number: ");
            num = sc.nextLine();
            temp=insertElement(n);
            temp.insertNumber(num);
            System.out.println("saved");
        }else {System.out.println("this name already exist");}


        return(temp);
    }

    Node indexSearch(int index){
        count=-1;
        return(indexSearch(rootNode,index));
    }
    Node indexSearch(Node root,int index){
        Node find=null;
        if(root!=null){
            find=indexSearch(root.leftChild,index);
            if(find!=null){return(find);}
            count++;
            if(count==index){
                return(root);
            }else {
                find= indexSearch(root.rightChild, index);
                return(find);
            }
        }else{return(null);}
    }
    void goToContact(){
        fileToTree();
        Scanner sc= new Scanner(System.in);
        int choice;
        do {

            System.out.println("1. Search a Contact.");
            System.out.println("2. Add a new Contact");
            System.out.println("3. See Recent Contacts");

            System.out.println("5. See all Contacts");
            System.out.println("6. Delete all Contacts");
            System.out.println("7. Exit");
            System.out.print("Select a option: ");

            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.print("insert name for search: ");
                String n = sc.nextLine();
                Node temp=search(this.rootNode,n);
                Operation(temp);

            } else if (choice == 2) {

                addContact();
                //do filing and save this along with sorting

            } else if (choice == 3) {
                Operation(select_recent());
            } else if (choice == 4) {
                String n = "";
                System.out.print("Enter Name: ");
                n = sc.nextLine();

                //search if exist then delete.
                sc.nextLine();
            } else if (choice == 5) {
                System.out.println("All Contacts: ");
                inorderTraversal();
                System.out.println("select a contact");
                int s=sc.nextInt();
                Operation(indexSearch(s-1));



                //show all contacts like a proper table.
            } else if (choice == 6) {
                System.out.println("Are you sure to Delete All Contacts. y/n");
                String choose = sc.next();
                if (choose.equals("y") || choose.equals("Y")) {
                    rootNode=null;
                }



            } else if (choice == 7) {
                treetofile();
                System.exit(0);
            } else {
                System.out.flush();
                System.out.println("Invalid Choice\nEnter Again:");

            }
        }while(true);


    }



    //create removeAll() method to make AVL Tree empty
    public void removeAll()
    {
        rootNode = null;
    }




    // create insertElement() to insert element to the AVL Tree
    public Node insertElement(String element)
    {
        this.rootNode = insertElement(element, rootNode);

        return inserted;
    }

    //create getHeight() method to get the height of the AVL Tree
    private int getHeight(Node node )
    {
        return node == null ? -1 : node.h;
    }

    //create maxNode() method to get the maximum height from left and right node
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight)
    {
        return Math.max(leftNodeHeight, rightNodeHeight);
        //edited by shoaib. math.max
      // replaced with:  leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
    }


    //create insertElement() method to insert data in the AVL Tree recursively
    private Node insertElement(String element, Node node)
    {
        //check whether the node is null or not
        if (node == null)
        {
            node = new Node(element);
            inserted=node;
        }
        //insert a node in case when the given element is lesser than the element of the root node
        else if (element.compareToIgnoreCase(node.name)<0)
        {
            node.leftChild = insertElement( element, node.leftChild );
            if( getHeight( node.leftChild ) - getHeight( node.rightChild ) == 2 )
                if( element.compareToIgnoreCase(node.leftChild.name)<0 )
                    node = rotateWithLeftChild( node );
                else
                    node = doubleWithLeftChild( node );
        }
        else if( element.compareToIgnoreCase(node.name)>0  )
        {
            node.rightChild = insertElement( element, node.rightChild );
            if( getHeight( node.rightChild ) - getHeight( node.leftChild ) == 2 )
                if( element.compareToIgnoreCase(node.rightChild.name)>0)
                    node = rotateWithRightChild( node );
                else
                    node = doubleWithRightChild( node );
        }
        else
            ;  // if the element is already present in the tree, we will do nothing
        node.h = getMaxHeight( getHeight( node.leftChild ), getHeight( node.rightChild ) ) + 1;

        return node;

    }

    // creating rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private Node rotateWithLeftChild(Node node2)
    {
        Node node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        node2.h = getMaxHeight( getHeight( node2.leftChild ), getHeight( node2.rightChild ) ) + 1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), node2.h ) + 1;
        return node1;
    }

    // creating rotateWithRightChild() method to perform rotation of binary tree node with right child
    private Node rotateWithRightChild(Node node1)
    {
        Node node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.h = getMaxHeight( getHeight( node1.leftChild ), getHeight( node1.rightChild ) ) + 1;
        node2.h = getMaxHeight( getHeight( node2.rightChild ), node1.h ) + 1;
        return node2;
    }

    //create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private Node doubleWithLeftChild(Node node3)
    {
        node3.leftChild = rotateWithRightChild( node3.leftChild );
        return rotateWithLeftChild( node3 );
    }

    //create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private Node doubleWithRightChild(Node node1)
    {
        node1.rightChild = rotateWithLeftChild( node1.rightChild );
        return rotateWithRightChild( node1 );
    }

    //create getTotalNumberOfNodes() method to get total number of nodes in the AVL Tree
    public int getTotalNumberOfNodes()
    {
        return getTotalNumberOfNodes(rootNode);
    }
    int getBalance(Node root){
        return(getHeight(root.leftChild)-getHeight(root.rightChild));
    }
    Node leftmost(Node root){
        if(root.leftChild==null){return(root);}else{return(leftmost(root.leftChild));}
    }
    Node deleteNode(Node root, String key)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return null;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key.compareToIgnoreCase(root.name)<0 )
            root.leftChild = deleteNode(root.leftChild, key);

            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
        else if (key.compareToIgnoreCase(root.name)>0)
            root.rightChild = deleteNode(root.rightChild, key);

            // if key is same as root's key, then this is the node
            // to be deleted
        else
        {

            // node with only one child or no child
            if ((root.leftChild == null) || (root.rightChild == null))
            {
                Node temp = null;
                if (temp == root.leftChild)
                    temp = root.rightChild;
                else
                    temp = root.leftChild;

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = leftmost(root.rightChild);

                // Copy the inorder successor's data to this node
                root.name = temp.name;
                root.head=null;

                for(contactNode t=temp.head;t!=null;t=t.next){
                    root.insertNumber(t.number);
                }

                // Delete the inorder successor
                root.rightChild = deleteNode(root.rightChild, temp.name);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.h = Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Case

        if (balance > 1 && getBalance(root.leftChild) >= 0)
            return rotateWithRightChild(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.leftChild) < 0)
        {
            root.leftChild = rotateWithLeftChild(root.leftChild);
            return rotateWithRightChild(root);
        }

        //  Right Case
        if (balance < -1 && getBalance(root.rightChild) <= 0)
            return rotateWithLeftChild(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.rightChild) > 0)
        {
            root.rightChild = rotateWithRightChild(root.rightChild);
            return rotateWithLeftChild(root);
        }

        return root;
    }
    private int getTotalNumberOfNodes(Node head)
    {
        if (head == null)
            return 0;
        else
        {
            int length = 1;
            length = length + getTotalNumberOfNodes(head.leftChild);
            length = length + getTotalNumberOfNodes(head.rightChild);
            return length;
        }
    }

    // create inorderTraversal() method for traversing AVL Tree in in-order form
    public void inorderTraversal()
    {count=1;
        inorderTraversal(rootNode);
    }
    private void inorderTraversal(Node head)
    {
        if (head != null)
        {
            inorderTraversal(head.leftChild);
            System.out.println(count+":"+head.name);
            count++;
//          for(contactNode temp=head.head;temp!=null;temp=temp.next){
//              System.out.println("    "+temp.number);
//          }
            inorderTraversal(head.rightChild);
        }
    }


    void print_r()
    {
        contactNode temp=recentT;
        for (int i=1 ; temp!=null ; i++) {
            System.out.println(i+" : "+temp.number);
            temp=temp.prev;
        }
    }
    Node select_recent(){
        Scanner sc=new Scanner(System.in);
        System.out.println("0 : for back");

        print_r();
        System.out.println("select a name");

        int s=sc.nextInt();
        if(s==0)
        {
            return(new Node("como estas cha hal aa how are you"));
        }
        if(s<=r_size){
            contactNode temp=recentT;
            for(int i=1;i<s;i++){
                System.out.println(temp.number);
                temp=temp.prev;
            }return(search(rootNode,temp.number));

        }else{return(null);}
    }


    void add_rec(String name)
    {

        if(recentH==null)
        {
            recentT=new contactNode(name);
            recentH=recentT;
            r_size++;
        }
        else
        {
            recentT.next=new contactNode(name);
            recentT.next.prev=recentT;
            recentT=recentT.next;
            r_size++;
            if(r_size>30)
            {
                recentH=recentH.next;
                recentH.prev=null;
                r_size--;
            }
        }
    }

    public void fileToTree(){

        try {
            File rec = new File("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\recent.txt");
            Scanner myReader4 = new Scanner(rec);
            while(myReader4.hasNextLine())
            {
                add_rec(myReader4.nextLine());
            }
            myReader4.close();
            File myObj = new File("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\Name.txt");
            Scanner myReader = new Scanner(myObj);
            File C1 = new File("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\con1.txt");
            Scanner myReader1 = new Scanner(C1);
            File C2 = new File("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\con2.txt");
            Scanner myReader2 = new Scanner(C2);
            File C3 = new File("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\con3.txt");
            Scanner myReader3 = new Scanner(C3);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Node curr= insertElement(data);
                if(myReader1.hasNextLine())
                    curr.insertNumber(myReader1.nextLine());

                if(myReader2.hasNextLine())
                    curr.insertNumber(myReader2.nextLine());

                if(myReader3.hasNextLine())
                    curr.insertNumber(myReader3.nextLine());
            }

            myReader.close();
            myReader1.close();
            myReader2.close();
            myReader3.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    void treetofile()
    {try{
        FileWriter r=new FileWriter("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\recent.txt");
        //FileWriter r
        contactNode temp=recentH;
        for (int i=0;i<r_size;i++)
        {
            r.write( temp.number+"\n");
            temp= temp.next;
        }
        r.close();
        FileWriter n=new FileWriter("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\Name.txt");
        FileWriter c1=new FileWriter("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\Con1.txt");
        FileWriter c2=new FileWriter("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\Con2.txt");
        FileWriter c3=new FileWriter("C:\\Users\\Shoaib\\Desktop\\java\\Files\\home\\developer\\Desktop\\StudentWork\\solutions\\ContactsDiary\\Con3.txt");
        treetofile(rootNode,n,c1,c2,c3);
        n.close();
        c1.close();
        c2.close();
        c3.close();
    }catch(Exception e){}
    }
    void treetofile(Node root,FileWriter n,FileWriter c1,FileWriter c2,FileWriter c3){
        try {
            if (root != null) {

                treetofile(root.leftChild, n, c1, c2, c3);
                n.write(root.name+"\n");
                   contactNode cur=root.head;
                if(cur!=null){
                    c3.write(cur.number+"\n");
                    cur=cur.next;
                }else{
                    c3.write(" \n");
                }if(cur!=null){
                    c2.write(cur.number+"\n");
                    cur=cur.next;
                }else{ c2.write(" \n");}
                if(cur!=null){
                    c1.write(cur.number+"\n");
                }else{ c1.write(" \n");}
                treetofile(root.rightChild, n, c1, c2, c3);
            }
        }catch(Exception e){}
    }
    public Node  search(Node root, String val_to_find)
    {
        if(root == null) // NULL encountered (node not found)
        {
            return null;
        }

        else if(root.name.equalsIgnoreCase( val_to_find)) // node found
        {

            return root;
        }
        else
        {
            if(val_to_find.compareToIgnoreCase(root.name)>0) // search in right subtree
            {
                return search(root.rightChild, val_to_find) ;
            }
            else if(val_to_find.compareToIgnoreCase(root.name) < 0) // search in left subtree
            {
                return search(root.leftChild, val_to_find) ;
            }
            return null;
        }

    }
}
