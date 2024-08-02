package main.bazePodataka.model.implentation.pomocniUpiti;

import java.util.List;

public class IsNotUpit implements PomocniUpit{
    private String leftBase, rightBase, levaPomocna, rightPomocna, rightAgregacija, rightBaza;
    private int tryIntRight;

    public IsNotUpit(List<String> args){
        store(args);
    }

    @Override
    public void store(List<String> args) {
        if(args.get(0).indexOf('.') > 0) {
            leftBase = args.get(0).substring(args.get(0).indexOf('.') + 1);
            levaPomocna = args.get(0).substring(0, args.get(0).indexOf('.'));
        }else{
            levaPomocna = args.get(0);
        }

        try {
            Integer.parseInt(args.get(1));
        }catch (NumberFormatException e){
            if(args.get(1).indexOf('.') > 0) {
                rightBase = args.get(1).substring(args.get(1).indexOf('.') + 1);
                rightPomocna = args.get(1).substring(0, args.get(1).indexOf('.'));
            }else{
                rightBase = args.get(0);
            }
            if(!rightBase.substring(0, 6).equalsIgnoreCase("count(")){
                rightAgregacija = rightBase.substring(0, 3).toUpperCase();
                rightBaza = rightBase.substring(4, rightBase.length() - 1).toUpperCase();
            }else{
                rightAgregacija = rightBase.substring(0, 5).toUpperCase();
                rightBaza = rightBase.substring(6, rightBase.length() - 1).toUpperCase();
            }
        }
    }
}
