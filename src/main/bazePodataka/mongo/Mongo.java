package main.bazePodataka.mongo;

import main.bazePodataka.model.implentation.GlavniUpit;

public interface Mongo {

    String translate(GlavniUpit query);
}
