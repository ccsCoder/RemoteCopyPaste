/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecopypaste;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.TimerTask;
import javax.swing.JTextArea;

/**
 *
 * @author CodeBeast
 */
public class ClipboardGetter extends TimerTask {
    private String clipboardText;
    private final Clipboard clipboard;
    private Socket toTarget;
    

    public ClipboardGetter(final Socket toTarget) {
        this.clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
        this.toTarget=toTarget;
        
    }
    
    

    @Override
    public void run() {
        try {
            
            this.clipboardText= (String) this.clipboard.getData(DataFlavor.stringFlavor);
            PrintWriter out = new PrintWriter(this.toTarget.getOutputStream());
            out.println(this.clipboardText);
            out.flush();
            System.out.println("Wrote to Target:"+this.clipboardText);
            
        } catch (Exception ex) {
            System.out.println("OOPS!"+ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
