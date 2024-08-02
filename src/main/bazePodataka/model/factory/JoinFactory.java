package main.bazePodataka.model.factory;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.JoinUpit;

import java.util.ArrayList;

public class JoinFactory extends ClouseFactory{
    @Override
    GlavniUpit createFactory(ArrayList<String> upit) {
        return new JoinUpit(upit);
    }
}
