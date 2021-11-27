package Final.classes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author IvanWeissVanDerPol https://github.com/IvanWeissPol 
 */
public class Message {
    public int Operation_type;
    public String body;

    public String toJSON(){
        JSONObject obj = new JSONObject();
        
        obj.put("Operation_type", Operation_type);
        obj.put("body", body);
        
        return obj.toJSONString();
    }

    public Message(int Operation_type, String body) {
        this.Operation_type = Operation_type;
        this.body = body;
    }
    
    public Message(String JSON) throws ParseException {
    
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(JSON.trim());
        JSONObject jsonObject = (JSONObject)obj;
        
        this.Operation_type = (int) (long) jsonObject.get("Operation_type");
        this.body = (String) jsonObject.get("body");
    }
    
    
}