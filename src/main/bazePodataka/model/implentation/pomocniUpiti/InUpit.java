package main.bazePodataka.model.implentation.pomocniUpiti;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public class InUpit implements PomocniUpit{

    private boolean isNot = false;
    private List<Integer> set = new ArrayList<>();
    private String base;

    public InUpit(List<String> args){
        store(args);
    }

    //select * from hrnesto where jovan not in (    8,7    , 2, 1       )

    @Override
    public void store(List<String> args) {
        base = args.get(0);
        args.remove(0);
        if(args.get(0).equalsIgnoreCase("not")) {
            isNot = true;
            args.remove(0);
        }

        StringBuffer result = new StringBuffer();
        for (String s : args) {
            result.append(s);
        }
        String newString = result.toString();
        int start = 1, end = -1;

        for(int i = 1; i < newString.length(); i++){
            if(newString.charAt(i) == ',' || newString.charAt(i) == ')'){
                end = i;
                String tmp = newString.substring(start, end);
                tryToAdd(tmp);
                start = end + 1;
            }
        }
    }

    private void tryToAdd(String tmp) {
        try{
            Integer num = Integer.parseInt(tmp);
            set.add(num);
        }catch (NumberFormatException e){

        }
    }
}
