/**************************
 * @file Parser.java
 * @brief This program is for Parsing user input and associating different input commands with their respective intended functions
 * @author John Kaelber
 * @date September 17, 2024
 **************************/
import java.io.*;
import java.util.Scanner;

public class Parser {

    //Create a BST tree of Integer type
    private BST<Integer> mybst = new BST<>();

    public Parser(String filename) throws FileNotFoundException {
        process(new File(filename));
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void process(File input) throws FileNotFoundException {
        try (Scanner sc = new Scanner(input)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim(); //gets rid of spaces on each side of line
                if(!line.isEmpty()) { //no need to do any blank lines
                    String[] command = line.split("\\s+");//after scanning through the input file, I used a regular expression to split the Strings as elements in the array by whitespace(s)
                    operate_BST(command); //calling operate_BST method
                }
            }
        }
    }
    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        switch (command[0]) {
            // add your cases here
            case "insert" -> {
                if (command.length == 2) {
                    try{
                        int key = Integer.parseInt(command[1]);
                        mybst.insert(key);
                        writeToFile("Insert " + key, "./result.txt");
                    }
                    catch (NumberFormatException e) {
                        writeToFile("Invalid number format insert", "./result.txt");
                    }
                }
                else{
                    writeToFile("Invalid command", "./result.txt");
                }
            }
            case "search" -> {
                if (command.length == 2) {
                    try{
                        int key = Integer.parseInt(command[1]);
                        Integer foundint = mybst.find(key);
                        String result;
                        if (foundint != null) {
                            result = "Search " + key + ": found";
                        }
                        else {
                            result = "Search " + key + ": not found";
                        }
                        writeToFile(result, "./result.txt");
                    }
                    catch (NumberFormatException e) {
                        writeToFile("Invalid number format search", "./result.txt");
                    }
                }
                else{
                    writeToFile("Invalid command", "./result.txt");
                }
            }
            case "remove" -> {
                if (command.length == 2) {
                    try{
                        int key = Integer.parseInt(command[1]);
                        Integer removedint = mybst.remove(key);
                        if (removedint != null) {
                            writeToFile("Remove " + key + ": found", "./result.txt");
                        }
                        else {
                            writeToFile("Remove " + key + ": not found", "./result.txt");
                        }
                    }
                    catch (NumberFormatException e) {
                        writeToFile("Invalid number format for remove", "./result.txt");
                    }
                }
                else{
                    writeToFile("Invalid command", "./result.txt");
                }
            }
            case "print" -> { //note*** my output isn't in ascending order because I did a pre-order iterator...
                StringBuilder result = new StringBuilder("Printing result");
                for(Integer key : mybst){
                    result.append(" ").append(key);
                }
                writeToFile(result.toString(), "./result.txt");
            }
            // default case for Invalid Command
            default -> writeToFile("Invalid Command", "./result.txt");
        }
    }

    // Implement the writeToFile method
    // Generate the result file
    public void writeToFile(String content, String filePath) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(content);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
