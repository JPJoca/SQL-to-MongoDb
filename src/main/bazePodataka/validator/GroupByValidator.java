package main.bazePodataka.validator;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.GroupByUpit;
import main.bazePodataka.model.implentation.SelectUpit;

import java.util.Arrays;
import java.util.List;

public class GroupByValidator implements ValidatorInter{

    private String name;
    private  List<String> agrg = Arrays.asList("count(","avg(","min(","max(", "sum(");
    //select avg(salary), department_id from hr.employees where department_id between 59 and 101 group by department_id having avg(salary) > 8000 order by avg(salary) asc

    @Override
    public String provera(List<GlavniUpit> upitiSql) {
        SelectUpit selectUpit = null;
        GroupByUpit groupByUpit = null;

        for (GlavniUpit glavniUpit : upitiSql) {
            if(glavniUpit instanceof  SelectUpit)
                selectUpit = (SelectUpit) glavniUpit;
            if(glavniUpit instanceof GroupByUpit)
                groupByUpit = (GroupByUpit) glavniUpit;
        }

        if (groupByUpit == null)
            return "";

        if(groupByUpit.getParam() == null)
            greska(0);

        for (String upit : selectUpit.getParams()) {
            if(upit.length() > 4 && (agrg.contains(upit.substring(0, 4).toLowerCase()) || agrg.contains(upit.substring(0, 6).toLowerCase())))
                continue;
            if(!groupByUpit.getParam().contains(upit))
                return greska(0);
        }
        return "";
    }



    @Override
    public String greska(int greska) {
        if(greska == 0)
            return "Ne sadrze se tabele u Group By-u";
        return "";
    }
}
