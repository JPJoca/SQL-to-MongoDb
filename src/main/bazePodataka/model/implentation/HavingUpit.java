package main.bazePodataka.model.implentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HavingUpit implements  GlavniUpit{


    private String name;
    private String operator;
    private static Integer baza1 = 1, baza2 = 2;
    private HashMap<Integer, ArrayList<String>> mapa;
    private static List<String> agreg = Arrays.asList("max(", "sum(", "min(","avg(", "count(");
    private List<String> param;

    public HavingUpit( List<String> upit) {
        this.name = upit.get(0);
        this.mapa = new HashMap<>();
        upit.remove(0);
        System.out.println(upit);
        raspodeli(upit);
    }

    @Override
    public String ocisti(String s) {

        return null;
    }

    @Override
    public void raspodeli(List<String> parametri) {
        operator = parametri.get(1);

        razresiString(parametri.get(0), "baza1");
        razresiString(parametri.get(2), "baza2");

        System.out.println(mapa + " " + operator);
    }
    private void razresiString(String word, String key) {
        ArrayList<String> tmp = new ArrayList<>();
        if(word.indexOf('.') > 0){
            tmp.add(word.substring(word.indexOf('.') + 1));
            tmp.add(word.substring(0, word.indexOf('.')));
            tmp.add(null);
        }else if(agreg.contains(word.substring(0, 4).toLowerCase())){
            tmp.add(word.substring(4, word.length() - 1));
            tmp.add(null);
            tmp.add(word.substring(0,3));
        }else{
            tmp.add(word);
            tmp.add(null);
            tmp.add(null);
        }

        if(key.equalsIgnoreCase("baza1"))
            addToMap(baza1, tmp);
        else
            addToMap(baza2, tmp);
    }

    //select avg(salary), department_id from hr.employees where department_id between 59 and 101     group by department_id having avg(salary) > d1.dep_id

    private void addToMap(Integer mapKey, ArrayList<String> lista){
        mapa.put(mapKey, lista);
    }

}
