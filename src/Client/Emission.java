package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;


public class Emission {

    private PrintWriter out;
    private String login = null;


    public Emission(PrintWriter out, String login) {
        this.out = out;
        this.login = login;

    }

    
    public void sendRacketPosition(int x){
    	out.println("sendracketposition");
    	out.println(x);
    	out.flush();
    	
    }
}