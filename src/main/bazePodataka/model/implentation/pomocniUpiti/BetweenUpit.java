package main.bazePodataka.model.implentation.pomocniUpiti;

import java.util.List;

public class BetweenUpit implements PomocniUpit{
    private String base, leftBase, leftPomacna, leftTrazena, leftAgregacija;
    private String rightBase, rightPomacna, rightTrazena, rightAgregacija;
    private int first, second;
    private String operation;

    public BetweenUpit(List<String> args){
        store(args);
    }
    //..... dep_id between 1 and max(selary)
    // [dep_id, 1, max(selary)]  e1.max(selary)   e1 max(selary)
    // select avg(salary), department_id from hr.employees where department_id between e1.max(selary) and e2.count(jovan)

    @Override
    public void store(List<String> args) {
        operation = args.get(0);
        args.remove(0);
        base = args.get(0);

        try {
            first = Integer.parseInt(args.get(1));
        }catch (NumberFormatException e){
            if(args.get(1).indexOf('.') > 0) {
                leftBase = args.get(1).substring(args.get(1).indexOf('.') + 1);
                leftPomacna = args.get(1).substring(0, args.get(1).indexOf('.'));
            }else{
                leftBase = args.get(1);
            }

            if(!leftBase.substring(0, 6).equalsIgnoreCase("count(")){
                leftAgregacija = leftBase.substring(0, 3).toUpperCase();
                leftTrazena = leftBase.substring(4, leftBase.length() - 1).toUpperCase();
            }else{
                leftAgregacija = leftBase.substring(0, 5).toUpperCase();
                leftTrazena = leftBase.substring(6, leftBase.length() - 1).toUpperCase();
            }
        }

        try {
            second = Integer.parseInt(args.get(2));
        }catch (NumberFormatException e){
            if(args.get(2).indexOf('.') > 0) {
                rightBase = args.get(2).substring(args.get(2).indexOf('.') + 1);
                rightPomacna = args.get(2).substring(0, args.get(2).indexOf('.'));
            }else{
                rightBase = args.get(2);
            }

            if(!rightBase.substring(0, 6).equalsIgnoreCase("count(")){
                rightAgregacija = rightBase.substring(0, 3).toUpperCase();
                rightTrazena = rightBase.substring(4, rightBase.length() - 1).toUpperCase();
            }else{
                rightAgregacija = rightBase.substring(0, 5).toUpperCase();
                rightTrazena = rightBase.substring(6, rightBase.length() - 1).toUpperCase();
            }
        }
    }
}
