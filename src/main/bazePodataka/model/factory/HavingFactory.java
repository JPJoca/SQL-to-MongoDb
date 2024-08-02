package main.bazePodataka.model.factory;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.HavingUpit;

import java.util.ArrayList;

public class HavingFactory extends ClouseFactory{
    @Override
    GlavniUpit createFactory(ArrayList<String> upit ){
        return new HavingUpit(upit);
    }
}
