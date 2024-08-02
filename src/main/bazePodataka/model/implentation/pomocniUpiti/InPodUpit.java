package main.bazePodataka.model.implentation.pomocniUpiti;

import lombok.Getter;
import main.bazePodataka.AppCore;
import main.bazePodataka.parser.SqlParser;

import java.util.ArrayList;
import java.util.List;
@Getter
public class InPodUpit implements PomocniUpit{

    private String baza;
    private ArrayList<String> podUpit;
    private SqlParser sqlParser;

    public InPodUpit(String baza, ArrayList<String> novUpit){
        this.baza = baza;
        podUpit = new ArrayList<>();
        podUpit.addAll(novUpit);
        sqlParser = new SqlParser(novUpit);
    }
    @Override
    public void store(List<String> args) {
    }
}
