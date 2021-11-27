package Final.Client;

import java.io.*;
import java.net.*;
import static Final.Client.Menu.MenuCLI;
import static Final.Client.Menu.menuError;
import static Final.Client.Menu.answer_menu;
import Final.classes.Message;

public class TCPVehicleClient {

    public static void main(String[] args) throws Exception {

        
        String direccion_Servidor = "127.0.0.1";
        int server_port = 4200;

        if (args.length == 2) {
            direccion_Servidor = args[0];
            server_port = Integer.parseInt(args[1]);
        }else if(args.length!=0){
            menuError("args Error");
            System.exit(1);
        }
        
        Socket Socket_Aux = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int Time_Out_Conexion = 10000;
        int Time_Out_Recepcion = 10000; 
        long inicio = 0;
        long fin = 0;


        try {

            SocketAddress socket_addres = new InetSocketAddress(direccion_Servidor, server_port);
            Socket_Aux = new Socket();
            
            inicio = System.currentTimeMillis();
            Socket_Aux.connect(socket_addres, Time_Out_Conexion);
            Socket_Aux.setSoTimeout(Time_Out_Recepcion);
            out = new PrintWriter(Socket_Aux.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(Socket_Aux.getInputStream()));
            
        }catch (SocketTimeoutException e){
            fin = System.currentTimeMillis();
            menuError("timeout error at " + Time_Out_Conexion);
            menuError("time spent " + (fin-inicio));
            System.exit(1);
        }catch (UnknownHostException e) {
            menuError("unknown host");
            System.exit(1);
        } catch (IOException e) {
            menuError("I/O Error in host connection");
            System.exit(1);
        }

        Message fromUser;
        String fromServer;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            while ((fromServer = in.readLine()) != null) {
                System.out.println("server: " + fromServer);
                answer_menu(new Message(fromServer));
                fromUser = MenuCLI();
                if (fromUser != null) {
                    System.out.println("client: " + fromUser.toJSON());
                    out.println(fromUser.toJSON());
                }
            }
        }catch(SocketTimeoutException exTime){
            menuError("wait time exceded " );
        }
		
        
        
        stdIn.close();
        Socket_Aux.close();
        in.close();
        out.close();
    }
}