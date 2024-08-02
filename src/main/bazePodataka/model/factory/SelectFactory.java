package main.bazePodataka.model.factory;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.SelectUpit;

import java.util.ArrayList;

public class SelectFactory extends ClouseFactory{
    @Override
    GlavniUpit createFactory(ArrayList<String> upit) {
        return new SelectUpit(upit);
    }
}
