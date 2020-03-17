package Services;

import Models.Model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Service {

    public ArrayList<String[]> fileToArray();
    public ArrayList<Model> stripList(ArrayList<String[]> list);
    public ArrayList<Model> sort(ArrayList<Model> list, String id);
    public HashMap<String, Integer> suggestionsFromInput(ArrayList<Model> list, String search, String reflection) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;
    public ArrayList<Model> chooseSuggestionsAndRes(HashMap<String, Integer> suggestions, ArrayList<Model> list, String scan, String reflection);
    public void ArrayToCsv(ArrayList<Model> list) throws IOException;

}
