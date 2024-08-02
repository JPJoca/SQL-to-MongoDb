package main.bazePodataka.database;

import main.bazePodataka.data.Row;

import java.util.List;

public interface Database {
    List<Row> getDataFromTable(String from);
}
