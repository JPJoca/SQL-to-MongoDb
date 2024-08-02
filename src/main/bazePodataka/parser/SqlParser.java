package main.bazePodataka.parser;

import main.bazePodataka.AppCore;
import main.bazePodataka.model.factory.ClouseFactory;
import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.observer.IPublisher;
import main.bazePodataka.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;
import main.bazePodataka.model.factory.UtilFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class SqlParser implements IPublisher {

    private String query;
    private static List<String> keyWords = Arrays.asList("SELECT", "FROM", "WHERE","JOIN","GROUP BY", "ORDER BY", "HAVING", "CROSS JOIN", "LEFT JOIN","RIGHT JOIN");
    private static List<String> agreg = Arrays.asList("max", "sum", "min","avg", "count");
    private static List<String> agrg2 = Arrays.asList("count(","avg(","min(","max(", "sum(");
    private List<String> parsedString;
    private List<GlavniUpit> listaUpita;
    private List<GlavniUpit> pomocnaListaUpita;
    private List<ISubscriber> subscribers;
    private ArrayList<String> newParse;

    public SqlParser(){
        subscribers = new ArrayList<>();
    }

    public SqlParser(ArrayList<String> toParse){
        parsedString = new ArrayList<>();
        listaUpita = new ArrayList<>();
        pomocnaListaUpita = new ArrayList<>();
        parsedString.addAll(toParse);
        srediZagrade(parsedString);
        init();
    }

    public void parseQuery(String query) {
        this.query = query.toLowerCase();
        parsedString = new ArrayList<>();
        listaUpita = new ArrayList<>();
        pars();
    }

    public void pars() {
        String[] arrOfStr = query.split(" ");
        for (String a : arrOfStr) {
            if(a.equalsIgnoreCase("BY") && (parsedString.get(parsedString.size() - 1).equalsIgnoreCase("ORDER") || parsedString.get(parsedString.size() - 1).equalsIgnoreCase("GROUP") )) {
                String tmp = parsedString.get(parsedString.size() -1) + " " + a;
                parsedString.remove(parsedString.size()-1);
                parsedString.add(tmp);
                continue;
            }
            if(a.equalsIgnoreCase("JOIN") && (parsedString.get(parsedString.size() - 1).equalsIgnoreCase("CROSS") || parsedString.get(parsedString.size() - 1).equalsIgnoreCase("LEFT") || parsedString.get(parsedString.size() - 1).equalsIgnoreCase("RIGHT"))) {
                String tmp = parsedString.get(parsedString.size() -1) + " " + a;
                parsedString.remove(parsedString.size()-1);
                parsedString.add(tmp);
                continue;
            }
            if(a.isEmpty())
                continue;
            parsedString.add(a);
        }
        //select max ( salary) from hr.employees having min ( aacw ) max( jovan )
        srediZagrade(parsedString);

        init();
    }

    //select job_title, avg(salary) from hr.employees join hr.jobs using (job_id) where employee_id in       (       select distinct employee_id from hr.job_history   )      group by job_title

    private void init(){
        System.out.println(parsedString);
        boolean flag = false;
        ArrayList<String> tmp = new ArrayList<>();
        for(String s : parsedString){
            if(keyWords.contains(s.toUpperCase()) && !flag){
                if (!tmp.isEmpty()) {
                    listaUpita.add(makeClouse(tmp));
                }
                tmp.removeAll(tmp);
                tmp.add(s);
            }else{
                if((tmp.size() > 2 && tmp.get(0).equalsIgnoreCase("where") && tmp.get(tmp.size() - 1).equalsIgnoreCase("in") && s.charAt(0) == '(') || flag  ){
                    flag = true;
                    if(s.charAt(s.length() - 1) == ')') {
                        flag = false;
                        tmp.add(s);
                    }else{
                        tmp.add(s);
                    }
                }else {
                    tmp.add(s);
                }
            }
        }
        if(!tmp.isEmpty())
        listaUpita.add(makeClouse(tmp));

        this.notifySubscribers(listaUpita);
    }

    private void srediZagrade(List<String> lista) {

        List<String> ls = new ArrayList<>();
        for(int i = 0; i<lista.size();i++) {
            if (lista.get(i).equals("(")) {
                String tmp = "(" + lista.get(i + 1);
                lista.set(i + 1, tmp);

                ls.add(lista.get(i));
            } else if (lista.get(i).equals(")")) {
                String tmp = lista.get(i - 1) + ")";
                lista.set(i - 1, tmp);

                ls.add(lista.get(i));
            } else if (agrg2.contains(lista.get(i)) ) {
                String tmp = lista.get(i) + lista.get(i + 1);
                lista.set(i + 1, tmp);

                ls.add(lista.get(i));
            } else if(agreg.contains(lista.get(i))){
                String tmp = lista.get(i) + lista.get(i + 1);
                lista.set(i + 1, tmp);

                ls.add(lista.get(i));
            }
        }
            lista.removeAll(ls);

    }

    private GlavniUpit makeClouse(ArrayList<String> s) {
        ClouseFactory cf = UtilFactory.getFactory(s);
        if(cf != null)
            return cf.getUpit(s);
        return null;
    }


    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for(ISubscriber subs : subscribers){
            subs.update(notification);
        }
    }
}
