package main.bazePodataka.mongo;

import main.bazePodataka.model.implentation.FromUpit;
import main.bazePodataka.model.implentation.GlavniUpit;

public class FromMongo implements Mongo{
    @Override
    public String translate(GlavniUpit query) {
        FromUpit upit = (FromUpit) query;
        if(upit.getBaza().charAt(0) == '.')
            return upit.getBaza().substring(1);
        return upit.getBaza();
    }
}
