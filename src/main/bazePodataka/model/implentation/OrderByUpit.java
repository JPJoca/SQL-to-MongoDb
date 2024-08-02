package main.bazePodataka.model.implentation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@Getter
public class OrderByUpit implements GlavniUpit{
    private String name;
    private List<String> params;
    HashMap<Integer, List<String>> mapa = new HashMap<>();
    private static List<String> agreg = Arrays.asList("max", "sum", "min","avg", "count(");
    private int brojac;

    // ORDER BY avg(newst) asc, dep_id desc, e1.dep_id asc

    // 1 <newst, asc, avg>
    // 2 <dep_id, desc>

   // select avg(salary), department_id from hr.employees group by department_id order by count(pera), avg(salary) asc, e1.dep_id desc, dep_id asc, eploy_id


    public OrderByUpit( List<String> upit) {
        this.name = upit.get(0);
        this.brojac = 0;
        upit.remove(0);
        raspodeli(upit);
    }

    @Override
    public String ocisti(String s) {
        if(s.charAt(s.length() - 1) == ',')
            return s.substring(0, s.length() - 1);
        return s;
    }

    @Override
    public void raspodeli(List<String> parametri) {
        List<String> tmp = new ArrayList<>();
        for(String arg : parametri){
            if(arg.charAt(arg.length() - 1) != ','){
                tmp.add(arg);
            }else{
                tmp.add(arg.substring(0, arg.length() - 1));
                addToMap(tmp);
                tmp.removeAll(tmp);
            }
        }

        addToMap(tmp);

    }

    private void mapping(Integer mapKey, String stringToAdd){
        List<String> stringList = mapa.get(mapKey);

        if(stringList == null){
            stringList = new ArrayList<>();
            stringList.add(stringToAdd);
            mapa.put(mapKey, stringList);
        }else {
            stringList.add(stringToAdd);
        }
    }

    private void addToMap(List<String> tmp) {


        if(tmp.get(0).length() > 6 && (agreg.contains(tmp.get(0).substring(0, 3)))){
            mapping(brojac,tmp.get(0).substring(4, tmp.get(0).length() - 1).toLowerCase());
            if(tmp.size() > 1 ) {
                if(tmp.get(1).equalsIgnoreCase("desc"))
                mapping(brojac, "-1");
            else
                mapping(brojac, "1");
            }
            else
                mapping(brojac,"1");

            mapping(brojac++,tmp.get(0).substring(0, 3).toLowerCase());

        }
         else if(tmp.get(0).length() > 6 && (agreg.contains(tmp.get(0).substring(0, 5)))){
            mapping(brojac,tmp.get(0).substring(5, tmp.get(0).length() - 1).toLowerCase());
            if(tmp.size() > 1 ) {
                if(tmp.get(1).equalsIgnoreCase("desc"))
                    mapping(brojac, "-1");
                else
                    mapping(brojac, "1");
            }
            else
                mapping(brojac,"1");

            mapping(brojac++,tmp.get(0).substring(0, 5).toLowerCase());
        }
         else if(tmp.get(0).contains(".")){
             int tmp1 = tmp.get(0).indexOf('.') ;
            mapping(brojac,tmp.get(0).substring(tmp1 + 1, tmp.get(0).length() ).toLowerCase());
            if(tmp.size() > 1 ) {
                if(tmp.get(1).equalsIgnoreCase("desc"))
                    mapping(brojac, "-1");
                else
                    mapping(brojac, "1");
            }
            else
                mapping(brojac,"1");

            mapping(brojac++,tmp.get(0).substring(0, tmp1).toLowerCase());

        }
         else{
            mapping(brojac,tmp.get(0).toLowerCase());
            if(tmp.size() > 1 ) {
                if(tmp.get(1).equalsIgnoreCase("desc"))
                    mapping(brojac, "-1");
                else
                    mapping(brojac, "1");
            }
            else
                mapping(brojac,"1");
             brojac++;
        }
    }
}
