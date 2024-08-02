package main.bazePodataka.validator;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.WhereUpit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhereValidator implements ValidatorInter{

    private String name;
    private static List<String> agrg = Arrays.asList("count(","avg(","min(","max(", "sum(");

    @Override
    public String provera(List<GlavniUpit> upitiSql) {
        List<String> upiti = new ArrayList<>();
        for (GlavniUpit glavniUpit : upitiSql) {
            if(glavniUpit instanceof WhereUpit) {
                upiti.addAll(((WhereUpit) glavniUpit).getElementi());
                System.out.println(upiti);
            }
        }

        for (String a : upiti) {
            if(a.length() > 6 && (agrg.contains(a.substring(0,4)) || agrg.contains(a.substring(0,6))))
                return "U WHERU se nalazi funkcija agrekacije !";
        }
        return "";
    }

    @Override
    public String greska(int greska) {
        return  null;
    }
}
