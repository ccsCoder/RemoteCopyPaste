
package remotecopypaste;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neo
 */
public class ClipboardSetter extends TimerTask {
    private Clipboard clipboard=null;
    private Socket clientSocket;
    private String incomingText;
    private BufferedReader br;
    
    public ClipboardSetter(final Socket td) {
        try {
            this.clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
            this.clientSocket=td;
            br = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        } catch (IOException ex) {
            System.out.println("OOPS!"+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Listener invoked:");
                
                this.incomingText = br.readLine();
                System.out.println("Incoming:"+this.incomingText);
                if (this.incomingText.trim().equalsIgnoreCase("")==false){
                    StringSelection ss = new StringSelection(this.incomingText);
                    this.clipboard.setContents(ss,ss);
                    System.out.println("SET this to clipboard:"+ss.getTransferData(DataFlavor.stringFlavor));
                }
        } catch (Exception ex) {
            Logger.getLogger(ClipboardSetter.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
    }
    
}
