package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

	
    public static void main(String[] args) {
    	
    	StartGui f = new StartGui();
		//f.getContentPane().add(new JButton("Bouton 3"));
		f.setVisible(true);
    	
    	/*BounceBall application = new BounceBall();
        application.addWindowListener(
           new WindowAdapter()
           {
              public void windowClosing(WindowEvent e)  
              {
                 System.exit(0);
              }
           }
        );*/
    }

}
