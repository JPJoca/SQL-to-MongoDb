package main.bazePodataka;


import lombok.Data;
import main.bazePodataka.adapter.MongoAdapter;
import main.bazePodataka.adapter.MongoAdapterImpl;
import main.bazePodataka.database.Database;
import main.bazePodataka.database.MyDatabase;
import main.bazePodataka.messageGenerator.MessageGeneratorImpl;
import main.bazePodataka.core.ApplicationFramework;
import main.bazePodataka.core.Gui;
import main.bazePodataka.core.MessageGenerator;
import main.bazePodataka.parser.SqlParser;
import main.bazePodataka.validator.ValidatorTest;
import main.bazePodataka.view.SwingGui;
import lombok.Getter;
import main.bazePodataka.view.swingGui.TableModel.TableModel;

@Getter
public class AppCore extends ApplicationFramework {

    private static AppCore instance;


    private AppCore(){

    }

    public static AppCore getInstance(){
        if(instance == null){
            instance = new AppCore();
        }
        return instance;
    }

    public void run(){
        this.gui.start();
    }

    public static void main(String[] args) {
        ApplicationFramework appCore = AppCore.getInstance();
        Gui gui = new SwingGui();
        MessageGenerator messageGenerator1 = new MessageGeneratorImpl();

        SqlParser sqlParser = new SqlParser();
        ValidatorTest validatorTest = new ValidatorTest(sqlParser);
        MongoAdapter ma = new MongoAdapterImpl(validatorTest);
        Database database = new MyDatabase((MongoAdapterImpl) ma);
        TableModel tableModel = new TableModel();

        appCore.initialise(gui,messageGenerator1,sqlParser,validatorTest, ma,database,tableModel);
        appCore.run();
    }



}