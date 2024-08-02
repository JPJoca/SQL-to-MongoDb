package main.bazePodataka.model.implentation.pomocniUpiti;

import lombok.Getter;

import java.util.List;
@Getter
public class AndOrUpit implements PomocniUpit{
    private boolean and, or;
    public AndOrUpit(String flag){
        if(flag.equals("AND")){
            and = true;
            or = false;
        }else{
            and = false;
            or = true;
        }
    }
    @Override
    public void store(List<String> args) {

    }
}
