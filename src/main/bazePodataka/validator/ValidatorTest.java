package main.bazePodataka.validator;

import main.bazePodataka.AppCore;
import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.observer.IPublisher;
import main.bazePodataka.observer.ISubscriber;
import main.bazePodataka.parser.SqlParser;

import java.util.ArrayList;
import java.util.List;

public class ValidatorTest implements ISubscriber, IPublisher {
    private List<GlavniUpit> upitiSQL = new ArrayList<>();
    private static GroupByValidator gbv = new GroupByValidator();
    private static ObaveznaValidator obv = new ObaveznaValidator();
    private static UsingOnValidator uov = new UsingOnValidator();
    private static WhereValidator whv = new WhereValidator();
    private SqlParser sqlParser;
    private List<ISubscriber> subscribers;

    public ValidatorTest(SqlParser sqlParser){
        this.sqlParser = sqlParser;
        subscribers = new ArrayList<>();
        if(sqlParser != null)
            this.sqlParser.addSubscriber(this);
    }

    private void validiraj() {
        StringBuilder sb = new StringBuilder();
        sb.append(gbv.provera(upitiSQL));
        sb.append(obv.provera(upitiSQL));
        sb.append(uov.provera(upitiSQL));
        sb.append(whv.provera(upitiSQL));

        System.out.println(sb);

        if(sb.isEmpty())
            this.notifySubscribers(upitiSQL);
        AppCore.getInstance().getMessageGenerator().generate(sb.toString());

        upitiSQL.removeAll(upitiSQL);
    }

    @Override
    public void update(Object notification) {
        this.upitiSQL.removeAll(upitiSQL);
        this.upitiSQL.addAll((ArrayList<GlavniUpit>)notification);
        validiraj();

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


