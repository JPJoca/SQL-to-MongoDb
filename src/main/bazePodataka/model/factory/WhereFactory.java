package main.bazePodataka.model.factory;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.WhereUpit;

import java.util.ArrayList;

public class WhereFactory extends ClouseFactory{
    @Override
    GlavniUpit createFactory(ArrayList<String> upit) {
        return new WhereUpit(upit);
    }
}
