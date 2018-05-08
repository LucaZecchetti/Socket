/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provasocket;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author gio
 */
public class Client implements Runnable, KeyListener{

    private int porta;
    private String indirizzo;
    private JTextField scrivi;
    private Socket socket;
    
    public Client(int porta, String indirizzo, JTextField scrittura){
        this.porta = porta;
        this.indirizzo = indirizzo;
        this.scrivi = scrittura;
    }
    
    @Override
    public void run() {
        scrivi.addKeyListener(this);
    }
    
    int n=0;

    @Override
    public void keyTyped(KeyEvent e) {
        PrintWriter out = null;
        InetAddress addr;
        n++;
        try {
            addr = InetAddress.getByName(indirizzo);
             socket = new Socket(addr, porta+n);
            
        } catch (Exception ex) {
            System.out.println("client "+ex.toString());
        }
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            //System.out.println((int)e.getKeyChar());
            if(((int)e.getKeyChar()) == 8)
                out.print("-1");
            else
                out.print(e.getKeyChar());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            out.close();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
