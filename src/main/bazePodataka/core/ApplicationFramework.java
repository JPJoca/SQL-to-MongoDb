package main.bazePodataka.core;

import main.bazePodataka.adapter.MongoAdapter;
import main.bazePodataka.database.Database;
import main.bazePodataka.database.MyDatabase;
import main.bazePodataka.parser.SqlParser;
import main.bazePodataka.validator.ValidatorTest;
import lombok.Getter;
import lombok.Setter;
import main.bazePodataka.view.swingGui.TableModel.TableModel;

import javax.swing.table.DefaultTableModel;

@Getter
@Setter
public abstract class ApplicationFramework {
    protected Gui gui;
    protected MessageGenerator messageGenerator;
    protected SqlParser sqlParser;
    protected ValidatorTest validatorTest;
    protected MongoAdapter mongoAdapter;
    protected Database database;
    protected TableModel tableModel;


    public ApplicationFramework() {
    }

    public abstract void run();

    public void initialise(Gui gui, MessageGenerator messageGenerator, SqlParser sqlParser, ValidatorTest validatorTest, MongoAdapter mongoAdapter, Database database, TableModel tableModel){

        this.gui = gui;
        this.messageGenerator = messageGenerator;
        this.sqlParser = sqlParser;
        this.validatorTest = validatorTest;
        this.mongoAdapter = mongoAdapter;
        this.database =  database;
        this.tableModel = tableModel;
    }
}