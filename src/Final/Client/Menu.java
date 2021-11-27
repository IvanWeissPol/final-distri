package Final.Client;

import java.util.scanner_auxanner;
import Final.classes.Message;
import Final.classes.Person;

/**
 *
 * @author IvanWeissVanDerPol https://github.com/IvanWeissPol 
 */
public class Menu {
    
    public static void menuError(String error){
        System.err.println(error);
    }
    
    public static Message MenuCLI(){
        System.out.println("Main menu");
        System.out.println("ingrese el numero que corresponde a la accion que desea realizar ");
        System.out.println("1 - listara los vehiculos de a cuerdo a la cedula de identidad");
        System.out.println("2 - agregar una persona y un vehiculo nuevo ");
        System.out.println("3 - salir");
        Scanner scanner_aux = new Scanner(System.in);
        int input = scanner_aux.nextInt();
        String body = null;
        
        switch (input) {
            case 1:
                System.out.println("Introduzca la CI: ");
                body = scanner_aux.next();
                break;
            case 2:
                Person persona_vehiculo = new Person();

                System.out.println("cedula: ");
                persona_vehiculo.CI = scanner_aux.next();
                System.out.println("nombre: ");
                persona_vehiculo.name = scanner_aux.next();
                System.out.println("apellido: ");
                persona_vehiculo.surname = scanner_aux.next();
                System.out.println("marca: ");
                persona_vehiculo.mark = scanner_aux.next();
                System.out.println("Chapa: ");
                persona_vehiculo.chapa = scanner_aux.next();

                body = persona_vehiculo.toJSON();
                break;
            default:
                System.exit(0);
        }
        return new Message(input,body);
    }
    
    public static void answer_menu(Message respuesta){
        System.out.println();
        System.out.println(respuesta.body);
        System.out.println();
    }

}
