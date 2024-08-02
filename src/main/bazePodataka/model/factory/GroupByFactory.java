package main.bazePodataka.model.factory;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.GroupByUpit;

import java.util.ArrayList;

public class GroupByFactory extends ClouseFactory{
    @Override
    GlavniUpit createFactory(ArrayList<String> upit) {
        return new GroupByUpit(upit);
    }
}
