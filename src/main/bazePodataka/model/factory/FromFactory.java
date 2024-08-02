package main.bazePodataka.model.factory;

import main.bazePodataka.model.implentation.FromUpit;
import main.bazePodataka.model.implentation.GlavniUpit;

import java.util.ArrayList;

public class FromFactory extends ClouseFactory{


    @Override
    GlavniUpit createFactory(ArrayList<String> upit) {
        return new FromUpit(upit);
    }
}
