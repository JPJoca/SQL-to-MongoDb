package main.bazePodataka.adapter;

import main.bazePodataka.model.implentation.*;
import main.bazePodataka.mongo.MongoQuery;
import main.bazePodataka.observer.IPublisher;
import main.bazePodataka.observer.ISubscriber;
import main.bazePodataka.validator.ValidatorTest;

import java.util.ArrayList;
import java.util.List;

public class MongoAdapterImpl implements MongoAdapter, ISubscriber , IPublisher {

    private ValidatorTest vt;
    private List<GlavniUpit> sqlUpiti;
    private List<ISubscriber> subscribers;

    public MongoAdapterImpl(ValidatorTest v){
        this.vt = v;
        this.sqlUpiti = new ArrayList<>();
        subscribers = new ArrayList<>();
        if(vt != null)
            vt.addSubscriber(this);

    }

    @Override
    public void convert() {

        MongoQuery mongo = new MongoQuery();
        for(int i = sqlUpiti.size() -1; i>=0; i--){
            if(sqlUpiti.get(i) instanceof SelectUpit){
                mongo.setSelect((SelectUpit) sqlUpiti.get(i));
            }else if(sqlUpiti.get(i) instanceof FromUpit){
                mongo.setFrom((FromUpit) sqlUpiti.get(i));
            } else if(sqlUpiti.get(i) instanceof OrderByUpit)
                mongo.setOrderBy((OrderByUpit) sqlUpiti.get(i));
            else if (sqlUpiti.get(i) instanceof JoinUpit)
                mongo.setJoin((JoinUpit) sqlUpiti.get(i));
            else if(sqlUpiti.get(i) instanceof  GroupByUpit)
                mongo.setGroupBy((GroupByUpit) sqlUpiti.get(i));
            else if(sqlUpiti.get(i) instanceof  WhereUpit)
                mongo.setWhere((WhereUpit) sqlUpiti.get(i));
        }
        mongo.obradi();
            this.notifySubscribers(mongo);
    }

    @Override
    public void update(Object notification) {
        this.sqlUpiti.addAll((ArrayList<GlavniUpit>)notification);
        convert();
        this.sqlUpiti.removeAll(sqlUpiti);
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
