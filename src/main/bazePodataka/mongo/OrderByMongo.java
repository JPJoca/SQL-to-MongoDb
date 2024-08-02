package main.bazePodataka.mongo;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.OrderByUpit;

import java.util.List;
import java.util.Map;

public class OrderByMongo implements Mongo{
    @Override
    public String translate(GlavniUpit query) {
        System.out.println(query);
        OrderByUpit upit = (OrderByUpit) query;
        StringBuilder orderBy = new StringBuilder();
        orderBy.append("{\n\"$sort\": {\n");
        for (Map.Entry<Integer, List<String>> entry :upit.getMapa().entrySet() ){

            if(entry.getValue().size() == 2)
                orderBy.append("\"" + entry.getValue().get(0) +"\": "+entry.getValue().get(1)+", \n");
            else if(entry.getValue().size() == 3)
                orderBy.append("\"" + entry.getValue().get(2) + "_" + entry.getValue().get(0)+"\": "+entry.getValue().get(1)+", \n");

        }
        orderBy.delete(orderBy.length()-3,orderBy.length());
        orderBy.append("\n} \n }");
        return orderBy.toString();
    }
}
