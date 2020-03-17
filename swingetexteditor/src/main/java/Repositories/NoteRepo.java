package Repositories;

import Models.Note;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteRepo {
    private List<Note> notes = new ArrayList<>();

    public List<Note> makeList(){
        notes.add(new Note((long) 1, "en teskt0", "mit navn"));
        notes.add(new Note((long) 2, "en tesk4", "mit navn2"));
        notes.add(new Note((long) 3, "en teskt9", "mit navn3"));
        notes.add(new Note((long) 4, "en teskt10", "mit navn6"));
        notes.add(new Note((long) 5, "en teskt11", "mit navn4"));
        return notes;
    };

    private void notesSort(){

        Collections.sort(notes);
    }



    public int noteGetSize(){
        return notes.size();
    }

    public Note getNoteAt(int index){
        if (index < notes.size()){
            return notes.get(index);
        }
        return null;
    };


    public void saveToJson(){

        ArrayList<Note> noteList = (ArrayList<Note>) makeList();//make the list
        notesSort();

        String path = System.getProperty("user.dir") + "/"; //path

        try(FileWriter fileWriter = new FileWriter(new File(path + "jsonFile.json"))) {

        JSONArray jsonArray = new JSONArray();
        for (Note note : noteList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("time", note.getTime());
            jsonObject.put("name", note.getName());
            jsonObject.put("text", note.getText());
            jsonArray.add(jsonObject);
        }

        fileWriter.write(jsonArray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Note> readJsonFile(){

        JSONParser jsonParser = new JSONParser();
        String path = System.getProperty("user.dir") + "/";
        JSONArray jsonArray = new JSONArray();
        try ( FileReader reader = new FileReader(path + "jsonFile.json")) {
            Object obj = jsonParser.parse(reader);
            jsonArray = (JSONArray) obj;


        }catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<Note> noteList = new ArrayList<>();
        for (Object object: jsonArray){
            JSONObject jsonObject = (JSONObject) object;
            Note note = new Note();
            note.setTime((Long) jsonObject.get("time"));
            note.setName((String) jsonObject.get("name"));
            note.setText((String) jsonObject.get("text"));
            noteList.add(note);
        }

        System.out.println(noteList);
        return noteList;
    }




}
