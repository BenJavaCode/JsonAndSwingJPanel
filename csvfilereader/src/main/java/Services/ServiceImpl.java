package Services;

import Models.Model;
import Services.Service;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ServiceImpl implements Service {

    @Override
    public ArrayList<String[]> fileToArray() {
        //get it part
        ArrayList<String[]> psykose = new ArrayList<>();
        String path = System.getProperty("user.dir") + "/";
        try (CSVReader reader = new CSVReader(new FileReader(path + "movie_metadata_500.csv"))){
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                psykose.add(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return psykose;
    }

    @Override
    public ArrayList<Model> stripList(ArrayList<String[]> list) {

        ArrayList<Model> modelList = new ArrayList<>();
        for (String[] modelArr : list){
            Model model = new Model(modelArr[23].toLowerCase(), modelArr[14].toLowerCase(), modelArr[9].toLowerCase(), modelArr[25].toLowerCase(), modelArr[1].toLowerCase());
            modelList.add(model);

        }
        return modelList;
    }

    @Override
    public ArrayList<Model> sort(ArrayList<Model> list, String id) {

        if (id.equals("1")){
            Comparator<Model> compareByParameter = Comparator.comparing(Model::getTitelYear);
            Collections.sort(list, compareByParameter.reversed());
        }
        if (id.equals("2")){
            Comparator<Model> compareByParameter = Comparator.comparing(Model::getRating);
            Collections.sort(list, compareByParameter.reversed());
        }

        return list;
    }



    @Override
    public HashMap<String, Integer> suggestionsFromInput(ArrayList<Model> list, String search, String reflection) {


        HashMap<String, Integer> suggestions = new HashMap<>();
        HashMap<String, Integer> map = new HashMap<>();


            //map for search
            int i = 0;
            for (Model model : list){

                try {

                    Method getByParameter = Model.class.getMethod(reflection);
                    getByParameter.setAccessible(true); // This is important if you want to access protected or private method. For public method you can skip

                    map.put((String) getByParameter.invoke(model), i);
                    i++;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }


            }


            //Search suggestions
            int x = 0;
            for(Map.Entry<String, Integer> entry : map.entrySet()) {

                String key = entry.getKey();
                if (key.contains(search.toLowerCase())){
                    suggestions.put(key,x);
                    x++;
                }
            }

        return suggestions;
    }

    @Override
    public ArrayList<Model> chooseSuggestionsAndRes(HashMap<String, Integer> suggestions, ArrayList<Model> list, String scan, String reflection){

        ArrayList<Model> searchList = new ArrayList<>();

        //Choose suggestion
        int v = 1;
        while (v == 1){

            String key = "";
            try {
                int choice = Integer.parseInt(scan);
                for (Map.Entry<String, Integer> entry: suggestions.entrySet()){
                    if (entry.getValue() == choice){
                        key = entry.getKey();
                    }
                }

                for (Model model : list){
                    try {

                        Method getByParameter = Model.class.getMethod(reflection);
                        getByParameter.setAccessible(true); // This is important if you want to access protected or private method. For public method you can skip

                        if (key.equals(getByParameter.invoke(model))){
                            searchList.add(model);
                        }

                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }


                }
                v = 2;
            }catch (Exception e){
                //log something
            }

        }
        return searchList;
    }



    @Override
    public void ArrayToCsv(ArrayList<Model> list) throws IOException, FileNotFoundException {
        String path = "C:/Users/Benjamin/Downloads/CsvResultSet.csv";

            try {
                CSVWriter writer = new CSVWriter(new FileWriter(path),',', CSVWriter.NO_QUOTE_CHARACTER);

                for (Model model : list) {
                    String[] reverse = new String[5];
                    reverse[0] = model.getTitelYear();
                    reverse[1] = model.getActor();
                    reverse[2] = model.getRating();
                    reverse[3] = model.getGenre();
                    reverse[4] = model.getInstructor();
                    writer.writeNext(reverse);
                }
                writer.close();
            }catch (Exception e){
                e.printStackTrace( );
            }


        }

}
