/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package provasocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author gio
 */
public class Server implements Runnable{

    private int porta;
    Socket clientSocket;
    JLabel txt;
    String a ="";
    int n=0;
    
    public Server(int  porta, JLabel txt){
        this.porta = porta;
        this.txt = txt;
    }
    
    @Override
    public void run() {
        BufferedReader in;
        do{
            try {
                n++;
                
                ServerSocket serverSocket = new ServerSocket(porta+n);
                clientSocket = serverSocket.accept();
                //System.out.println("connessione accettata");
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                
                
                String b = in.readLine();
                System.out.println(b);
                if(b!= null && b.equals("-1"))
                    try{
                    a = a.substring(0,a.length()-1);
                    }catch(Exception e){
                        System.out.println(e.toString());
                    }
                else if(b!= null)
                    a+=b;
                txt.setText(a);
                
                in.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(true);
    }
    
}
