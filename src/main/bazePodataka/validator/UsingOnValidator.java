package main.bazePodataka.validator;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.JoinUpit;

import java.util.List;
//Prijavljuje gresko ako su kljucne reci poslednje !!!
public class UsingOnValidator implements ValidatorInter{
    private String name;
    @Override
    public String provera(List<GlavniUpit> upitiSql) {
        JoinUpit join = null;

        for(GlavniUpit upit : upitiSql){
            if(upit instanceof JoinUpit) {
                join = (JoinUpit) upit;

                if(join.getOnOrUsing().equals(""))
                    return greska(1);
            }
        }

        if(join == null)
            return "";

        if(join.getOnOrUsing().equals(""))
            return greska(1);

        return greska(3);
    }

    @Override
    public String greska(int greska) {
        if (greska == 1) {
            return "Posle koriscenja joina potrebno je koristi kljucnu rec ON ili USING\n";
        }else if(greska == 2)
            return "Posle koriscenja joina potrebno je koristi samo ON ili samo USING\n";
        else
            return "";
    }
}
