package Final.classes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author IvanWeissVanDerPol https://github.com/IvanWeissPol 
 */
public class Person {
    public String CI;
    public String name;
    public String surname;
    public String chapa;
    public String mark;

    public Person() {
    }
    
    public Person(String CI, String name, String surname, String chapa, String mark) {
        this.CI = CI;
        this.name = name;
        this.surname = surname;
        this.chapa = chapa;
        this.mark = mark;
    }
    public String toJSON(){
        JSONObject obj = new JSONObject();
        
        obj.put("CI", this.CI);
        obj.put("name", this.name);
        obj.put("surname", this.surname);
        obj.put("chapa", this.chapa);
        obj.put("mark", this.mark);

        return obj.toJSONString();
    }
    public Person(String JSON) throws ParseException{
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(JSON.trim());
        JSONObject jsonObject = (JSONObject)obj;
        
        this.CI = (String) jsonObject.get("CI");
        this.name = (String) jsonObject.get("name");
        this.surname = (String) jsonObject.get("surname");
        this.chapa = (String) jsonObject.get("chapa");
        this.mark = (String) jsonObject.get("mark");
    }
    

}
