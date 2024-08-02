package main.bazePodataka.model.implentation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Getter
public class SelectUpit implements GlavniUpit{
    private String name;
    private List<String> params = new ArrayList<>();
    HashMap<String, ArrayList<String>> mapa;
    private boolean distinct = false;


    public SelectUpit(List<String> upit) {
        this.name = upit.get(0);
        mapa = new HashMap<>();
        upit.remove(0);
        raspodeli(upit);
    }

    @Override
    public String ocisti(String s) {
        if(s.charAt(s.length() - 1) == ',')
            return s.substring(0, s.length() - 1);
        return s;
    }

    private void addToMap(String mapKey, String stringToAdd){
        ArrayList<String> stringList = mapa.get(mapKey);

        if(stringList == null){
            stringList = new ArrayList<>();
            stringList.add(stringToAdd);
            mapa.put(mapKey, stringList);
        }else {
            stringList.add(stringToAdd);
        }
    }

    @Override
    public void raspodeli(List<String> parametri) {
        if(parametri.isEmpty())
            return;
        if(parametri.get(0).equalsIgnoreCase("distinct")) {
            distinct = true;
            parametri.remove(0);
        }
        for (String s : parametri) {
            params.add(ocisti(s));
        }
        System.out.println(params);
        for(int i = 0; i < parametri.size(); i++){
            if(params.get(i).length() < 6)
                continue;
            if(params.get(i).substring(0, 4).equalsIgnoreCase("MAX(") ){
                addToMap("max", parametri.get(i).substring(4, parametri.get(i).length() - 2));
            }else if(params.get(i).substring(0, 4).equalsIgnoreCase("MIN(")){
                addToMap("min", parametri.get(i).substring(4, parametri.get(i).length() - 2));
            }else if(params.get(i).substring(0, 4).equalsIgnoreCase("AVG(")){
                addToMap("avg", parametri.get(i).substring(4, parametri.get(i).length() - 2));
            }else if(params.get(i).substring(0, 4).equalsIgnoreCase("SUM(")){
                addToMap("sum", parametri.get(i).substring(4, parametri.get(i).length() - 2));
            }else if(params.get(i).substring(0, 6).equalsIgnoreCase("COUNT(")){
                addToMap("count", parametri.get(i).substring(6, parametri.get(i).length() - 2));
            }
        }
        // select dep_id, max(jovan), min(nikola), face_id, count(sda), max(nidza) from dsadsa_id

    }
}
