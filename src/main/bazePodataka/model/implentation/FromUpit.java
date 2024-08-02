package main.bazePodataka.model.implentation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class FromUpit implements GlavniUpit{
    private String name;
    private String baza;
    private String alias;
    private String leftRight;

    public FromUpit(List<String> upit) {
        this.name = upit.get(0);
        upit.remove(0);
        raspodeli(upit);
    }

    @Override
    public String ocisti(String s) {

        return null;
    }

    @Override
    public void raspodeli(List<String> parametri) {
        if(parametri.get(0).indexOf('.') > 0)
            baza = parametri.get(0).substring(parametri.get(0).indexOf('.') );
        else
            baza = parametri.get(0);
        parametri.remove(0);
        if(parametri.size() == 1)
            alias = parametri.get(0);
    }
}
