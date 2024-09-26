/**************************
 * @file Parser.java
 * @brief This program is for creating a BST from the csv Health_Sleep_Statistics file. It also has function for reading and parsing user input and associating different input commands with their respective intended functions
 * @author John Kaelber
 * @date September 26, 2024
 **************************/
import java.io.*;
import java.util.Scanner;

public class Parser {

    //Create a BST tree of Integer type
    private BST<SleepData> mybst = new BST<>(); // changed object type to SleepData

    public Parser(String filename) throws FileNotFoundException {
        processCSV(new File(filename)); //creates tree from csv data calling processCSV method
        processMyCommands(); //calls the processMyCommands method for the commands
    }

    // Implement the process method
    // Remove redundant spaces for each input command
    public void processCSV(File input) throws FileNotFoundException { //changed to CSV so can read the csv and make the BST, separate process from the commands
        try (Scanner sc = new Scanner(input)) {
            if (sc.hasNextLine()) {
                sc.nextLine(); //needed to skip the top line of the csv because it's all strings no data
            }
            while ((sc.hasNextLine())) {
                String line = sc.nextLine().trim();
                if(!line.isEmpty()) { //no need to do any blank lines
                    String[] sleeper = line.split(",");//when scanning through csv file split by commas
                    if (sleeper.length == 12) { //if there are 12 split data for the sleeper line
                        //create an object SleepData with the respective data
                        SleepData sleepData = new SleepData(Integer.parseInt(sleeper[0]), Integer.parseInt(sleeper[1]), sleeper[2], Integer.parseInt(sleeper[3]), sleeper[4], sleeper[5], Integer.parseInt(sleeper[6]), Integer.parseInt(sleeper[7]), sleeper[8], sleeper[9], sleeper[10], sleeper[11]);
                        mybst.insert(sleepData); //insert the object
                    }
                }
            }
        }
    }

    public void processMyCommands() { //separate process method for commands
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter commands... if you want you don't have to... you can also type exit to quit: "); //prompting command
        while(true) {
            String command = sc.nextLine().trim(); //assigning command to String that I trim to avoid errors
            if(command.equals("exit")) {//for stopping the command when you type exit
                break;
            }
            String [] yourCommand = command.split("\\s+"); //putting your commands into string split by any whitespaces
            operate_BST(yourCommand); //calling operate_BST to do the commands
        }
    }

    // Implement the operate_BST method
    // Determine the incoming command and operate on the BST
    public void operate_BST(String[] command) {
        switch (command[0]) {
            // add your cases here
            case "insert" -> {
                if (command.length == 13) {
                    try{
                        SleepData sleepData = new SleepData(Integer.parseInt(command[1]), Integer.parseInt(command[2]), command[3], Integer.parseInt(command[4]), command[5], command[6], Integer.parseInt(command[7]), Integer.parseInt(command[8]), command[9], command[10], command[11], command[12]);
                        mybst.insert(sleepData); //changed to sleepData of SleepData type
                        writeToFile("Inserted " + sleepData + " successfully", "./result.txt");
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
                        int userID = Integer.parseInt(command[1]);
                        SleepData foundUser = mybst.find(new SleepData(userID, null, null, null, null, null, null, null, null, null, null, null));
                        String result;
                        if (foundUser != null) {
                            result = "Search for user ID: " + foundUser + " found";
                        }
                        else {
                            result = "Search for user ID: " + userID + " not found";
                        }
                        writeToFile(result, "./result.txt");
                    }
                    catch (NumberFormatException e) {
                        writeToFile("Invalid number format for search", "./result.txt");
                    }
                }
                else{
                    writeToFile("Invalid command", "./result.txt");
                }
            }
            case "remove" -> {
                if (command.length == 2) {
                    try{
                        int userID = Integer.parseInt(command[1]);
                        SleepData removedSleepData = mybst.remove(new SleepData(userID, null, null, null, null, null, null, null, null, null, null, null));
                        if (removedSleepData != null) {
                            writeToFile("User " + userID + ": found, data removed", "./result.txt");
                        }
                        else {
                            writeToFile("User " + userID + ": not found", "./result.txt");
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
            case "print" -> { //it does as it is called
                StringBuilder result = new StringBuilder("Printing result");
                for(SleepData sleepData : mybst){
                    result.append(" ").append(sleepData);
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
