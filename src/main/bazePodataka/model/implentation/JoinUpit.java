package main.bazePodataka.model.implentation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@Getter
public class JoinUpit implements GlavniUpit{

//    private String name;
//    private String type;
//    private String kWord;
//    private String bazaZaUpored, bazaZaUpored2, baza, suppBaza, baza2, suppBaza2, typeOfOperater, bazaGlavna;
    private List<String> param;
    private static List<String> agreg = Arrays.asList("max", "sum", "min","avg", "count");
    private String name, typeOfJoin, onOrUsing = "", alias = "", condition;
    private static Integer joinBaza = 1, onBaza1 = 2, onBaza2 = 3;
    HashMap<Integer, ArrayList<String>> map = new HashMap<>();
    //select department_name, upper(street_address), country_name from hr.departments cross join hr.locations using (location_id) RIGHT JOIN Employees ON Orders.EmployeeID = Employees.EmployeeID
    public JoinUpit(ArrayList<String> upiti) {
        param = new ArrayList<>();
        raspodeli(upiti);
    }

    @Override
    public String ocisti(String s) {
        if(s.charAt(0) == '(') {
            if(s.charAt(s.length() - 1) == ')')
                return s.substring(1, s.length() - 1);
            return s.substring(1);
        }
        if(s.charAt(s.length() - 1) == ')')
            return s.substring(0, s.length() - 1);
        return s;
    }

    @Override
    public void raspodeli(List<String> parametri) {
        String[] arrOfStr = parametri.get(0).split(" ");
        if(arrOfStr[0].equalsIgnoreCase("join")){
            typeOfJoin = "INNER";
            name = "JOIN";
        }else {
            typeOfJoin = arrOfStr[0];
            name = "JOIN";
        }
        parametri.remove(0);
        List<String> post = new ArrayList<>();

        razresiString(parametri.get(0), "joinBaza");
        parametri.remove(0);

        if(!parametri.get(0).equalsIgnoreCase("on") && !parametri.get(0).equalsIgnoreCase("using"))
            alias = parametri.get(0);

        for(int i = 0; i < parametri.size(); i++){
            if(parametri.get(i).equalsIgnoreCase("on")){
                onOrUsing = "on";
                for(int j = i + 1; j < parametri.size(); j++){
                    post.add(ocisti(parametri.get(j)));
                }
                break;
            }else if (parametri.get(i).equalsIgnoreCase("using")){
                onOrUsing = "using";
                for(int j = i + 1; j < parametri.size(); j++){
                    post.add(ocisti(parametri.get(j)));
                }
                break;
            }
        }

        if(post.size() == 1){
            razresiString(post.get(0), "onBaza1");
        }else if(post.size() == 3){
            for(String arg : post){
                if(arg.equals(">")){
                    condition = "gt";
                }else if(arg.equals("<")){
                    condition = "lt";
                }else if(arg.equals("=")){
                    condition = "eq";
                }else if(arg.equals("<=")){
                    condition = "lte";
                }else if(arg.equals(">=")){
                    condition = "gte";
                }
            }

            razresiString(post.get(0), "onBaza1");
            razresiString(post.get(2), "onBaza2");
        }
    }

    private void razresiString(String word, String key){
        ArrayList<String> tmp = new ArrayList<>();
        if(word.indexOf('.') > 0){
            tmp.add(word.substring(word.indexOf('.') +1 ));
            tmp.add(word.substring(0, word.indexOf('.') ));
        }else
            tmp.add(word);

        if(key.equals("joinBaza"))
            addToMap(joinBaza, tmp);
        else if(key.equals("onBaza1"))
            addToMap(onBaza1, tmp);
        else
            addToMap(onBaza2, tmp);
    }

    private void addToMap(Integer mapKey, ArrayList<String> lista){
        map.put(mapKey, lista);
    }
}
