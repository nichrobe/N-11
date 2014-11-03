//package n.pkg11;
import java.io.*;
import java.util.*;
public class N11 {
    
    public enum State {
        START_COMMENT,
        END_COMMENT,
        NON_COMMENT
    }

    public static void main(String[] args) {
        // In case the user needs to enter the file from the terminal.
        String file = "";
        if (args.length == 0) {
            // The user did not provide a file.
            Scanner in = new Scanner(System.in);
            System.out.println("Please provide the file you wish to inspect: ");
            file = in.nextLine();
        } 
        
        if ((args.length == 0) && (file.length() == 0)) {
            System.out.println("A file has not been entered. Please try the application"
                    + " again.");
        } else {
            try {
                String input;
                // Find out what way the user entered the file.
                if (args.length == 0) {
                    input = file;
                } else {
                    input = args[0];
                }
                
                FileReader fr = new FileReader(input);
                BufferedReader br = new BufferedReader(fr);
                State state = State.NON_COMMENT;
                String comment = "";
                
                for (String line; (line = br.readLine()) != null; ) {
                    int start_index = 0;
                    int stop_index = 0;
                    
                    // Output if completed.
                    if (state == State.END_COMMENT) {
                        System.out.println(comment);
                        state = State.NON_COMMENT;
                        comment = "";
                    }
                    
                    // Grabs the start.
                    if (line.contains("/*")) {
                        state = State.START_COMMENT;
                        start_index = line.indexOf("/*");
                    }
                    
                    // If we have found an end comment and we have already found
                    // a start comment, then we have reached the end of a comment.
                    if (line.contains("*/") && (state == State.START_COMMENT)) {
                        stop_index = line.indexOf("*/");
                        state = State.END_COMMENT;
                    } else {
                        stop_index = line.length();
                    }
                    
                    if (state != State.NON_COMMENT) {
                        comment = line.substring(start_index, stop_index);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
