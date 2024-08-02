package main.bazePodataka.validator;

import main.bazePodataka.model.implentation.FromUpit;
import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.SelectUpit;

import java.util.List;

public class ObaveznaValidator implements ValidatorInter{

    private String name;


    @Override
    public String provera(List<GlavniUpit> upitiSql) {
        int flag = 0;
        for (GlavniUpit glavniUpit : upitiSql) {
            if(glavniUpit instanceof SelectUpit)
                flag +=1;
            if(glavniUpit instanceof FromUpit)
                flag +=2;

            if (flag == 3) {
                greska(flag);
                break;
            }
        }


        return greska(flag);
    }

    @Override
    public String greska(int greska) {
        if(greska == 3) {
            return "";
        }
        else if(greska == 2) {
            return "Ne postoji SELECT, dodati SELECT \n";
        }
        else if(greska == 1) {
            return "Ne postoji FROM, dodati FROM \n";
        }
        else {
            return "Ne postoji SELECT i FROM, dodati SELECT i FROM \n";
        } }
}
