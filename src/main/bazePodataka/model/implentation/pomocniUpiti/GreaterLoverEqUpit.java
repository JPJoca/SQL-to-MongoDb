package main.bazePodataka.model.implentation.pomocniUpiti;

import lombok.Getter;

import java.util.List;
@Getter
public class GreaterLoverEqUpit implements PomocniUpit{
    private String baza1, baza2, pomocnaBaza1, pomocnaBaza2, typeOf;
    // razmotriti da jedna baza moze da bude samo vrednost

    public GreaterLoverEqUpit(List<String> args){
        store(args);
    }

    @Override
    public void store(List<String> args) {
        if(args.get(0).indexOf('.') > 0){
            baza1 = args.get(0).substring(args.get(0).indexOf('.') + 1);
            pomocnaBaza1 = args.get(0).substring(0, args.get(0).indexOf('.'));
        }else
            baza1 = args.get(0);

        if(args.get(1).indexOf('.') > 0){
            baza2 = args.get(1).substring(args.get(1).indexOf('.') + 1);
            pomocnaBaza2 = args.get(1).substring(0, args.get(1).indexOf('.'));
        }else
            baza2 = args.get(1);

        typeOf = args.get(2);
    }
}
