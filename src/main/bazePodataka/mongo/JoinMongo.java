package main.bazePodataka.mongo;

import lombok.Getter;
import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.JoinUpit;

import java.util.ArrayList;
import java.util.Map;
@Getter
public class JoinMongo implements Mongo{
    private String undw;
    @Override
    public String translate(GlavniUpit query) {
        JoinUpit upit = (JoinUpit) query;
        StringBuilder join = new StringBuilder();
        if(upit.getOnOrUsing().equals("on"))
        join.append("{\n" +
                "  \"$lookup\": {\n" +
                "    \"from\": \""+upit.getMap().get(1).get(0)+"\",\n" +
                "    \"let\": {\n" +
                "      \""+upit.getMap().get(2).get(0)+"\": \"$"+upit.getMap().get(2).get(0)+"\"\n" +
                "    },\n" +
                "    \"pipeline\": [\n" +
                "      {\n" +
                "        \"$match\": {\n" +
                "          \"$expr\": {\n" +
                "            \"$"+upit.getCondition() +"\": [\n" +
                "              \"$$"+upit.getMap().get(2).get(0)+"\",\n" +
                "              \"$"+upit.getMap().get(3).get(0)+"\"\n" +
                "            ]\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"as\": \""+upit.getAlias()+"\"\n" +
                "  }\n" +
                "}");



        undw =        "{\n" +
                "  \"$unwind\": {\n" +
                "    \"path\": \"$"+upit.getAlias()+"\",\n" +
                "    \"preserveNullAndEmptyArrays\": false\n" +
                "  }\n" +
                "}";


        return join.toString();
    }
}
