package main.bazePodataka.validator;

import main.bazePodataka.model.implentation.GlavniUpit;

import java.util.List;

public interface ValidatorInter {

    String provera(List<GlavniUpit> upitiSql);
    String greska(int greska);
}
