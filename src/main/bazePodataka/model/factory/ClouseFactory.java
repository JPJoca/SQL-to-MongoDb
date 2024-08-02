package main.bazePodataka.model.factory;

import main.bazePodataka.model.implentation.GlavniUpit;

import java.util.ArrayList;

public abstract class ClouseFactory {

        public GlavniUpit getUpit(ArrayList<String> upit){
            GlavniUpit noviUpit = createFactory(upit);
            return noviUpit;
        }
        abstract GlavniUpit createFactory(ArrayList<String> upit);
    }

