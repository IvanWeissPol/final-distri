package Final.Server;

import java.util.ArrayList;
import Final.classes.Person;

/**
 *
 * @author IvanWeissVanDerPol https://github.com/IvanWeissPol 
 */
public class DB {
    public ArrayList<Person> datos = new ArrayList<>();
    
    public void preloadedData(){
        this.datos.add(new Person("5897596","Ivan","Van Der Pol","AAAA 000","Pagani Zonda HP Barchetta"));
        this.datos.add(new Person("5897596","Ivan","Weiss","AAAA 001","Rolls-Royce Sweptail"));
        this.datos.add(new Person("1111111","Fernando","Mancia","AAAA 002","Lamborghini Sián Roadster"));
    }
}