/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neo
 */
public class Validator {
    public static boolean validateIPOrHost(String host) {
        System.out.println("Validating->"+host);
        boolean isValidIP = false;
        try {
            InetAddress.getByName(host);
            isValidIP=true;
        } catch (UnknownHostException ex) {
            System.out.println("Invalid hostname!");
            isValidIP=false;
        }
        return isValidIP;
    }
}
