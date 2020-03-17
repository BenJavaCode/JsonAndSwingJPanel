package Services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonService {



    public JSONArray JsonArrayInit(){

        JSONParser jsonParser = new JSONParser();
        String path = System.getProperty("user.dir") + "/";
        JSONArray jsonArray = new JSONArray();
        try ( FileReader reader = new FileReader(path + "unece.json")) {
            Object obj = jsonParser.parse(reader);
            jsonArray = (JSONArray) obj;


        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonArray;
    }

    public int countJsonObject(JSONArray jsonArray){
        int z = 0;
        for (Object o : jsonArray){
            z++;
        }
        return z;
    }

    public Set giveKeys(JSONArray jsonArray){
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        return jsonObject.keySet();
    }

    public JSONArray someColumns(JSONArray jsonArray) throws IOException {
        JSONArray jsonArray2 = new JSONArray();
        HashMap<String,Integer> boss = new HashMap<String, Integer>();
        try (FileWriter file = new FileWriter(new File("C:/Users/Benjamin/IdeaProjects/csvfilereader/output.json"))){


            for (Object o : jsonArray){
                JSONObject jsonObject = (JSONObject) o;

                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("Country", jsonObject.get("Country"));
                jsonObject2.put("Year", jsonObject.get("Year"));
                jsonObject2.put("Total population", jsonObject.get("Total population"));


                if (!boss.containsKey(jsonObject2.get("Country").toString())){
                    boss.put(jsonObject2.get("Country").toString(), Integer.parseInt(jsonObject2.get("Year").toString()));

                } else if(Integer.parseInt(jsonObject2.get("Year").toString()) > boss.get(jsonObject2.get("Country").toString())){
                    boss.replace(jsonObject2.get("Country").toString(), Integer.parseInt(jsonObject2.get("Year").toString()));
                }

            }

            for (Object o : jsonArray){
                JSONObject jsonObject = (JSONObject) o;

                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("Country", jsonObject.get("Country"));
                jsonObject2.put("Year", jsonObject.get("Year"));
                jsonObject2.put("Total population", jsonObject.get("Total population"));


                if(Integer.parseInt(jsonObject2.get("Year").toString()) >= boss.get(jsonObject2.get("Country").toString())){
                    jsonArray2.add(jsonObject2);

                }

            }
            file.write(jsonArray2.toJSONString());


        }catch (Exception e){
            e.printStackTrace();
        }







        return jsonArray2;
    }

    public HashMap<Integer, String> chose(JSONArray jsonArray){

        HashMap<Integer, String> boss = new HashMap<>();
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);

        List<String> list = new ArrayList<String>(jsonObject.keySet());

        int z = 0;
        for (String s : list){
            z++;
            boss.put(z,s);
        }
        return boss;

    }

    public JSONArray getChosen(JSONArray jsonArray, ArrayList<String> inp) {
        JSONArray jsonArray2 = new JSONArray();


        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;

            JSONObject jsonObject2 = new JSONObject();
            for (String s : inp){
                jsonObject2.put(s, jsonObject.get(s));
                jsonArray2.add(jsonObject2);
            }



        }
        return jsonArray2;
    }

}
