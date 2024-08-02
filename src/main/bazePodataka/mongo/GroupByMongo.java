package main.bazePodataka.mongo;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.GroupByUpit;
import main.bazePodataka.model.implentation.SelectUpit;

import java.util.ArrayList;
import java.util.Map;

public class GroupByMongo implements Mongo{
    @Override
    public String translate(GlavniUpit query) {
        String groupBy = "";
        if(query instanceof SelectUpit)
            groupBy = translateSelect((SelectUpit) query);
        else if(query instanceof GroupByUpit)
            groupBy =translateGroupBy((GroupByUpit) query);
        return groupBy;
    }

    private String translateGroupBy(GroupByUpit upit) {
        StringBuilder groupBy = new StringBuilder();
        groupBy.append("{\n" +
                "\"$group\": {\n" +
                "\"_id\": {\n");

        for (String o : upit.getParam()) {
            if (o.contains("("))
                continue;
            groupBy.append("\"" + o + "\": " + "\"$" + o + "\", \n");
        }
        groupBy.delete(groupBy.length()-3,groupBy.length());
        groupBy.append("}\n} \n}");

        return groupBy.toString();
    }

    private String translateSelect(SelectUpit upit) {
        boolean flag = false;
        StringBuilder groupBy = new StringBuilder();
        groupBy.append("{\n" +
                "\"$group\": {\n" +
                "\"_id\": {\n");

        for (String o : upit.getParams()) {

            if (o.contains("("))
                continue;

            groupBy.append("\"" + o + "\": " + "\"$" + o + "\", \n");
            flag = true;
        }
        if(flag)
            groupBy.delete(groupBy.length()-3,groupBy.length());

        for (Map.Entry<String, ArrayList<String>>entry : upit.getMapa().entrySet()){
            for(String s : entry.getValue()) {
                if(entry.getKey().equalsIgnoreCase("count"))
                        groupBy.append("\n},\n" +
                                "\"" + "count" +"\": { \n" +
                                "\"$" + "sum" + "\": " + 1 + "\n");
                else
                     groupBy.append("\n},\n" +
                        "\"" + entry.getKey() + "_" + s +"\": { \n" +
                        "\"$" + entry.getKey() + "\": \"$" + s + "\"\n");

            }
        }
        groupBy.append("}\n} }");

                return groupBy.toString();
    }
}
