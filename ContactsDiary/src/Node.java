import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
//node class
class Node
{
    String name;
    int h;  //for height
    Node leftChild;
    Node rightChild;

    contactNode head;


//constructor
    public Node()
    {
        leftChild = null;
        rightChild = null;
        name = "";
        h = 0;
    }
    // parameterized constructor
    public Node(String element)
    {
        leftChild = null;
        rightChild = null;
        name = element;
        h = 0;
    }


    //Method to insert a number.
    public void insertNumber(String num){

        if(head==null)
            head= new contactNode(num);
        else {
            contactNode newNode=new contactNode(num);
            newNode.next=head;
            head.prev=newNode;
            head=newNode;
        }

    }
}


