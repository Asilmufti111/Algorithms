
//assignment title: Binary search tree
//date: 10 NOV 2022
package UberSystemProgram;

import java.io.*;
import java.util.*;

public class MainProgram {

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File("input.txt");//create a file object to read from input file

        if (!inputFile.exists()) { //check that the file is exist
            System.out.println("The intial Information file does not exist");//display that the file does not exist
            System.exit(0);//exit from the program
        }

        Scanner input = new Scanner(inputFile);//craete a scanner object to read by it from input file
        File outputFile = new File("output.txt");//create a file object to write the output in it

        PrintWriter output = new PrintWriter(outputFile); //craete a PrintWriter object to write by it in  the output file
        output.println("--------------- Welcome to Uber Booking System ---------------");
        
        String command = "";//variable to read the commands from the input file 
        UberTree uber = new UberTree();//uber tree 

        while (!command.equals("Quit")) {//stop when command = quit
            command = input.next();// read the command from the input file 
            
            if (command.equals("Add_Captain")) {//check the command 
                int id = input.nextInt();//read the id
                String name = input.next();//read the name 
                CaptainNode captain = new CaptainNode(id, name);//create a new captain node
                uber.add_captains(captain);//add the captain to the tree
                output.println("Command Add_Captain: Add a new captain record in the System\n");//print statment 
                uber.display_captain_by_ID(captain, output);//print captain information

            } else if (command.equals("BOOK_RIDE")) {//check the command 
                int id = input.nextInt();//read the id
                CaptainNode captain = uber.search(id);//search about the captain using his/her ID
                uber.book(captain, output, id);//book the captain to a new ride
                
            } else if (command.equals("DISPLAY_CAPTAIN_INFO")) {//check the command 
                int id = input.nextInt();//read the id
                if (uber.search(id) != null) {//search about the captain using his/her ID,to know if it is exist in the tree or not 
                    output.println("Command DISPLAY_CAPTAIN_INFO: ");
                    uber.display_captain_by_ID(uber.search(id), output);//print captain information
                } else {// the captain not found
                    output.println("Command DISPLAY_CAPTAIN_INFO: Couldn’t  find any captain with ID number " + id + "\n");
                    output.println("----------------------------------------------------------------");
                }
                
            } else if (command.equals("FINISH_RIDE")) {//check the command 

                int id = input.nextInt();// read the ID
                int star = input.nextInt();// read the star if it is (0 or 1)
                CaptainNode captain = uber.search(id);//find the captain
                uber.finish(captain, star, output, id);// finish the ride of the captain 

            } else if (command.equals("DELETE_CAPTAIN")) {//check the command 
                int id = input.nextInt();// read the ID
                CaptainNode captain = uber.search(id);//find the captain
                if (captain != null) {//the captain is exist in the tree
                    uber.delete(id);// remove the captain from the tree
                    output.println("Command  DELETE_CAPTAIN:The captain " + captain.getCaptainName() + " left Uber  \n"
                            + "----------------------------------------------------------------");
                } else {//the captain does not exist in the tree
                    output.println("Command  DELETE_CAPTAIN:  Couldn’t  find any captain with ID number " + id);
                    output.println("----------------------------------------------------------------");

                }

            } else if (command.equals("DISPLAY_ALL_CAPTAINS")) {//check the command 
                output.println("Command DISPLAY_ALL_CAPTAINS:\n");
                uber.display_all_captains(output);//print all captains information 
            }
        }

        output.println("Thank you for using Uber System, Good Bye!");//print statment 
        output.flush();//print everything now 
        output.close();//close the output file 
    }

}
