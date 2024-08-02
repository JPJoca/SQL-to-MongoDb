package main.bazePodataka.model.implentation;

import main.bazePodataka.model.implentation.pomocniUpiti.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Getter
public class WhereUpit implements GlavniUpit{
    private String name;
    private List<String> args;
    private List<PomocniUpit> pomocniUpiti;
    private List<GlavniUpit> izPodUpita;
    private List<String> keyWords = Arrays.asList("SELECT", "FROM", "WHERE", "JOIN","GROUP BY","ORDER BY","HAVING");
    public WhereUpit(ArrayList<String> upit) {
        this.name = upit.get(0);
        args = new ArrayList<>();
        pomocniUpiti = new ArrayList<>();
        upit.remove(0);
        raspodeli(upit);
    }

    @Override
    public String ocisti(String s) {
        return s;
    }

    private List<String> elementi = new ArrayList<>();

    @Override
    public void raspodeli(List<String> parametri) {
        args.addAll(parametri);
        System.out.println(args + "args");
        for(int i = 0; i < args.size(); i++){
            if(args.get(i).equalsIgnoreCase("(select")){
                ArrayList<String> podUpit = new ArrayList<>();
                for(int j = i; j < args.size(); j++){
                    if (args.get(j).charAt(0) == '(')
                        podUpit.add(args.get(j).substring(1));
                    else if(args.get(j).charAt(args.get(j).length() - 1) == ')')
                        podUpit.add(args.get(j).substring(0, args.get(j).length() - 1));
                    else
                        podUpit.add(args.get(j));
                }
                PomocniUpit pu = new InPodUpit(args.get(i - 2), podUpit);
                pomocniUpiti.add(pu);
                izPodUpita = new ArrayList<>();
                izPodUpita.addAll(((InPodUpit)pu).getSqlParser().getListaUpita());
                return;
            }
        }

        List<String> tmpList = new ArrayList<>();
        for(int i = 0; i < args.size(); i++){
            tmpList.removeAll(tmpList);
            if(args.get(i).equalsIgnoreCase("LIKE")){
                tmpList.add(args.get(i - 1));
                tmpList.add(args.get(i + 1));
                PomocniUpit pu = new LikeUpit(tmpList);
                pomocniUpiti.add(pu);
            }else if(args.get(i).equalsIgnoreCase("AND") && !args.get(i - 2).equalsIgnoreCase("BETWEEN")){
                PomocniUpit pu = new AndOrUpit("AND");
                pomocniUpiti.add(pu);
            }else if(args.get(i).equalsIgnoreCase("OR")){
                PomocniUpit pu = new AndOrUpit("OR");
                pomocniUpiti.add(pu);
            }else if(args.get(i).equalsIgnoreCase("NOT") && !args.get(i + 1).equalsIgnoreCase("IN")){
                tmpList.add(args.get(i - 2));
                tmpList.add(args.get(i + 1));
                PomocniUpit pu = new IsNotUpit(tmpList);
                pomocniUpiti.add(pu);
            }else if(args.get(i).equalsIgnoreCase("=")){
                tmpList.add(args.get(i - 1));
                tmpList.add(args.get(i + 1));
                tmpList.add("eq");
                PomocniUpit pu = new GreaterLoverEqUpit(tmpList);
                pomocniUpiti.add(pu);
            }else if(args.get(i).equalsIgnoreCase(">")){
                tmpList.add(args.get(i - 1));
                tmpList.add(args.get(i + 1));
                tmpList.add("gt");
                PomocniUpit pu = new GreaterLoverEqUpit(tmpList);
                pomocniUpiti.add(pu);
            }else if(args.get(i).equalsIgnoreCase("<")){
                tmpList.add(args.get(i - 1));
                tmpList.add(args.get(i + 1));
                tmpList.add("lt");
                PomocniUpit pu = new GreaterLoverEqUpit(tmpList);
                pomocniUpiti.add(pu);
            }else if(args.get(i).equalsIgnoreCase(">=")){
                tmpList.add(args.get(i - 1));
                tmpList.add(args.get(i + 1));
                tmpList.add("gte");
                PomocniUpit pu = new GreaterLoverEqUpit(tmpList);
                pomocniUpiti.add(pu);
            }else if(args.get(i).equalsIgnoreCase("<=")){
                tmpList.add(args.get(i - 1));
                tmpList.add(args.get(i + 1));
                tmpList.add("lte");
                PomocniUpit pu = new GreaterLoverEqUpit(tmpList);
                pomocniUpiti.add(pu);
            }else if(args.get(i).equalsIgnoreCase("BETWEEN")){
                tmpList.add(args.get(i + 2));
                tmpList.add(args.get(i - 1));
                tmpList.add(args.get(i + 1));
                tmpList.add(args.get(i + 3));
                PomocniUpit pu = new BetweenUpit(tmpList);
                pomocniUpiti.add(pu);
        }else if(args.get(i).equalsIgnoreCase("IN") ){

                boolean flag = false;
                for(int j = i; j < args.size(); j++){
                    if(keyWords.contains(args.get(j))){
                        flag = true;
                    }
                }

                if(flag == false){
                    if(args.get(i-1).equalsIgnoreCase("not")) {
                        tmpList.add(args.get(i - 2));
                        tmpList.add(args.get(i - 1));
                    }
                    else
                        tmpList.add(args.get(i - 1));
                    for(int j = i + 1; j < args.size(); j++){
                        tmpList.add(args.get(j));
                    }
                }

                PomocniUpit pu = new InUpit(tmpList);
                pomocniUpiti.add(pu);

            }
            else {
                try {
                    Integer.parseInt(args.get(i));
                }catch (NumberFormatException e){
                    if(args.get(i).charAt(0) != '\'')
                        elementi.add(args.get(i));
                }
            }
        }

    }


}
