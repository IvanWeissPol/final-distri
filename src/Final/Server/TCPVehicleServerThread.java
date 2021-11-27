package Final.Server;

import java.net.*;
import java.io.*;
import org.json.simple.parser.ParseException;
import Final.classes.Message;
import Final.classes.Person;

public class TCPVehicleServerThread extends Thread {

    private Socket socket = null;

    TCPMultiServerVehicle servidor;
    
    public TCPVehicleServerThread(Socket socket, TCPMultiServerVehicle servidor ) {
        super("TCPServerThread");
        this.socket = socket;
        this.servidor = servidor;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            Message received_Client;
            Message Sent_Client = new Message(1,"TCP comunucation established");            
            out.println(Sent_Client.toJSON());

            while ((inputLine = in.readLine()) != null) {
                received_Client = new Message(inputLine);    
                Sent_Client = ProcessMessage(received_Client);
                if(Sent_Client == null){
                    break;
                }    
                out.println(Sent_Client.toJSON());
            }
            
            out.close();
            in.close();
            socket.close();
            System.out.println("finishing  thread");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            System.err.println(ex.toString());            
        }
    }
    
    public static Message ProcessMessage(Message Received_data) throws ParseException{
        int Operation_type = Received_data.Operation_type;
        String body = Received_data.body;
        switch (Operation_type) {
            case 1:
                Person Person = new Person(body);
                TCPMultiServerVehicle.db.datos.add(Person);
                return new Message(1,"operation completed succesflly ");
            case 2:
                String CI = body;
                String list = "";
                for(int i=0;i<TCPMultiServerVehicle.db.datos.size();i++){
                    if(TCPMultiServerVehicle.db.datos.get(i).CI.equals(CI)){
                        list = list + TCPMultiServerVehicle.db.datos.get(i).chapa + ",";
                    }
                }
                return new Message(2,list);
            default:
                break;
        }
        return null;
    }   
}