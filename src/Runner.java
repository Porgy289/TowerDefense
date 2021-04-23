

import javax.swing.*;

public class Runner {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Systems system = new Systems();

        frame.add( system );
        frame.pack();
        frame.setResizable( false );
        system.requestFocusInWindow();

        frame.setVisible( true );

        while( true )
        {
            try { Thread.sleep( 50 ); }
            catch( Exception e ){}
            frame.repaint();
    }
    
}
