package main.bazePodataka.model.factory;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.OrderByUpit;

import java.util.ArrayList;

public class OrderByFactory extends ClouseFactory{
    @Override
    GlavniUpit createFactory(ArrayList<String> upit) {
        return new OrderByUpit(upit);
    }
}
