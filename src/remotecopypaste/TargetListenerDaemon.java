/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package remotecopypaste;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import util.PropertyFileWriter;

/**
 *
 * @author neo
 */
public class TargetListenerDaemon {
    private boolean listenFlag=true;
    private String incomingText;
    private ServerSocket serverSocket;
    private Socket client;
    private Timer t=null;

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }
    
    
    
    public TargetListenerDaemon() {
        try {
            this.serverSocket = new ServerSocket(Integer.parseInt(PropertyFileWriter.CONNECTION_PROPERTIES.getProperty("port")));
            System.out.println("Socket Server created, listening for data on port...");
            this.client=this.serverSocket.accept();
            System.out.println("Client Connected !"+this.client.getInetAddress().toString());
            
            t = new Timer();
            ClipboardSetter clipboardSetter = new ClipboardSetter(this.client);
            //Start processing clipboard data...
            t.schedule(clipboardSetter, 0, 1000*Integer.parseInt(PropertyFileWriter.CONNECTION_PROPERTIES.getProperty("syncFrequency")));
        } catch (IOException ex) {
            System.out.println("OOPS!"+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean isListenFlag() {
        return listenFlag;
    }

    public void setListenFlag(boolean listenFlag) {
        this.listenFlag = listenFlag;
        if (this.listenFlag==false) {
            System.out.println("listen flag = false, stopping timer task");
            t.cancel();
            t.purge();
        }
    }

    public String getIncomingText() {
        return incomingText;
    }

    public void setIncomingText(String incomingText) {
        this.incomingText = incomingText;
    }
    
    
    
}
