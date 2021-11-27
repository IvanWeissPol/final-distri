package Final.Server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class TCPMultiServerVehicle {
    
        public static DB db = new DB();

	boolean listening = true;
	List<TCPVehicleServerThread> clientStrings; //list of threads
	List<String> users; //list of users 
        
        static int server_port = 4200;

    public void ejecutar() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(server_port);
        } catch (IOException e) {
            System.err.println("Could not open port: " + server_port);
            System.exit(1);
        }
        System.out.println("Opened port : " + 4200);

        while (listening) {
            TCPVehicleServerThread thread = new TCPVehicleServerThread(serverSocket.accept(), this);
            clientStrings.add(thread);
            thread.start();
        }
        serverSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
        
        server_port = 4200;
        if(args.length == 1){
            server_port = Integer.parseInt(args[0]);
        }else if(args.length > 1){
            System.err.println("ERROR in args");
            System.exit(1);
        }
        
        db.preloadedData();
        TCPMultiServerVehicle tms = new TCPMultiServerVehicle();
        tms.clientStrings = new ArrayList<TCPVehicleServerThread>();
        tms.users = new ArrayList<String>();
        tms.ejecutar();
    }
}
