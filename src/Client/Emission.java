package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;


public class Emission implements Runnable {

    private PrintWriter out;
    private String login = null;
    private String message = null;
    private Scanner sc = null;

    public Emission(PrintWriter out, String login) {
        this.out = out;
        this.login = login;

    }


    public void run() {

        sc = new Scanner(System.in);

        while(true){
            message = sc.nextLine();
            out.println(message);
            out.flush();
        }
    }
}