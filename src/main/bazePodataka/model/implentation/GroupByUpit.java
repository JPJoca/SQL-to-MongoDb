package main.bazePodataka.model.implentation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Getter
public class GroupByUpit implements GlavniUpit{
    private String name;
    private List<String> param;


    public GroupByUpit( List<String> upit) {
        this.name = upit.get(0);
        param = new ArrayList<>();
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
        for (String s : parametri){
            param.add(ocisti(s));
        }

    }
}
