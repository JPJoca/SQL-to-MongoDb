package main.bazePodataka.model.implentation.pomocniUpiti;


import lombok.Getter;

import java.util.List;
@Getter

public class LikeUpit implements PomocniUpit{

    private String base;
    private String karakter;
    private int duzina;
    private int procenat;
    private boolean pozicija_duzina;     /** Gleda da li _ oznacava poziciju ili minimalnu duzinu ako je true oznacava pozicuju ako je false oznacava min duzinu**/


    public LikeUpit(List<String> params){
        base = params.get(0);
        params.remove(0);
        store(params);
    }



    @Override
    public void store(List<String> args) {
       String tmp = args.get(0).substring(1,args.get(0).length()-1);
       duzina = 1;
       karakter = "";
       pozicija_duzina = false;
       if(tmp.charAt(0) == '%' && tmp.charAt(tmp.length()-1) == '%')
           procenat = 0;
       else if(tmp.charAt(0) == '%')
           procenat = -1;
       else if(tmp.charAt(tmp.length()-1) == '%')
           procenat = 1;
       else if(tmp.charAt(0) == '_')
           pozicija_duzina = true;


       for(int i =0; i<tmp.length(); i++){
           if(tmp.charAt(i) == '_') {
               duzina++;
               continue;
           }
           if(tmp.charAt(i) == '%') {
               continue;
           }
           karakter+=tmp.charAt(i);
       }


    }
}
