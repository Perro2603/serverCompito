package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
    
        try {
            Socket socket = new Socket("localhost", 3000);

            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String serverResponse;
            while ((serverResponse = serverIn.readLine()) != null) {
                System.out.println(serverResponse);

                if (serverResponse.contains("Connessione effettuata")) {
                    break;
                }
            }

            String userInput;
            while ((userInput = consoleIn.readLine()) != null) {
                out.println(userInput);

                if (userInput.equalsIgnoreCase("ESCI")) {
                    break;
                }

                String serverOutput = serverIn.readLine();
                System.out.println(serverOutput);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
   
}


