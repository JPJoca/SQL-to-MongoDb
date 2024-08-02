package main.bazePodataka.mongo;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.SelectUpit;

import java.util.ArrayList;
import java.util.Map;

public class SelectMongo implements Mongo{


    private boolean agregat = false;

    @Override
    public String translate(GlavniUpit query) {
        System.out.println(query);
        this.agregat = false;
       SelectUpit upit = (SelectUpit)query;
       StringBuilder select = new StringBuilder();
        if(upit.getParams().get(0).equals("*") ) {
            return select.toString();
        }

         if (upit.getMapa().isEmpty()) {
             select.append("{\n" +
                     "\"$project\": { \n" +
                    "  \"_id\": 0,\n");
            for (String su : upit.getParams()) {

                select.append ("\"" + su + "\": 1,\n");
            }
            select.delete(select.length()-2,select.length());
            select.append("\n} \n}");
        } else {
             this.agregat = true;

             select.append("{\n" +
                     "\"$project\": { \n");

             for (String o : upit.getParams()) {
                 if (o.contains("("))
                     continue;
                 select.append("\"" + o + "\": " + "\"$_id." + o + "\", \n");
             }


             for (Map.Entry<String, ArrayList<String>> entry : upit.getMapa().entrySet()) {
                 for (String s : entry.getValue()) {

                     if(entry.getKey().equalsIgnoreCase("count"))
                             select.append("\"" + entry.getKey() +  "\": 1, \n");
                     else
                         select.append("\"" + entry.getKey() + "_" + s + "\": 1, \n");

                 }
             }
             select.append(" \n \"_id\": 0\n" +
                     "  }\n" +
                     "}");
         }

        return select.toString();
    }

    public boolean isAgregat(){
        return agregat;
    }
}
