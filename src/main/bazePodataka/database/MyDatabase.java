package main.bazePodataka.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import main.bazePodataka.AppCore;
import main.bazePodataka.adapter.MongoAdapterImpl;
import main.bazePodataka.core.ApplicationFramework;
import main.bazePodataka.data.Row;
import main.bazePodataka.mongo.Mongo;
import main.bazePodataka.mongo.MongoQuery;
import main.bazePodataka.observer.IPublisher;
import main.bazePodataka.observer.ISubscriber;
import org.bson.Document;

import javax.swing.text.html.parser.DocumentParser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyDatabase implements Database , ISubscriber, IPublisher {

    private MongoAdapterImpl mai;
    private MongoClient connection;
    private MongoQuery mongoQuery;
    private List<ISubscriber> subscribers;
   // private
    public MyDatabase(MongoAdapterImpl mongoAdapter) {
        mai = mongoAdapter;
        mai.addSubscriber(this);

    }
    private void initConnection() throws MongoException {
        String ip = "134.209.239.154";
        String database = "bp_tim40";
        String username = "writer";
        String password = "7V6XZ4ZPmGlkMr4Z";

        MongoCredential credential = MongoCredential.createCredential(username, database, password.toCharArray());
        connection = new MongoClient(new ServerAddress(ip, 27017), Arrays.asList(credential));
        List<Row> rows = new ArrayList<>();
        MongoDatabase baza = connection.getDatabase(database);
        MongoCursor<Document> cursor = baza.getCollection(mongoQuery.getFrom()).aggregate(mongoQuery.getMongoList()).iterator();
        try {
            while(cursor.hasNext()){
                Row row = new Row();
                row.setName(mongoQuery.getFrom());

                Document d = cursor.next();

                for(Map.Entry<String,Object> entry : d.entrySet()){
                    String kljuc = entry.getKey();
                    Object vrednost = entry.getValue();
                    row.addField(kljuc,vrednost);
                }
                rows.add(row);
                AppCore.getInstance().getTableModel().setRows(rows);
            }
        } catch (Exception e){
            e.printStackTrace();

        }

        closeConnection();

    }

    private void closeConnection(){
        try{
            connection.close();
        }
        catch (MongoException e){
            e.printStackTrace();
        }
        finally {
            connection = null;
        }
    }

    @Override
    public List<Row> getDataFromTable(String from) {

        return null;

    }

    @Override
    public void update(Object notification) {
        mongoQuery = (MongoQuery) notification;
        initConnection();
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for(ISubscriber subs : subscribers){
            subs.update(notification);
        }
    }
}
