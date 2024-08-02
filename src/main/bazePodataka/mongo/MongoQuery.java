package main.bazePodataka.mongo;

import lombok.Getter;
import main.bazePodataka.model.implentation.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MongoQuery {

    private String select ;
    private String where;
    private String join;
    private String from;
    private String orderBy;
    private String groupBy;
    private String unwind;
    private List<Document> MongoList;

    private static SelectMongo sm = new SelectMongo();
    private static FromMongo fm = new FromMongo();
    private static OrderByMongo obm = new OrderByMongo();
    private static WhereMongo wm = new WhereMongo();
    private static JoinMongo jm = new JoinMongo();
    private static GroupByMongo gbm= new GroupByMongo();

    private boolean agregat;
    public MongoQuery(){
        MongoList = new ArrayList<>();
        select = "";
        join = "";
        orderBy = "";
        from = "";
        where = "";
        groupBy = "";
        unwind = "";
    }

    public void setSelect(SelectUpit upit) {
       select = sm.translate(upit);
       if(!upit.getMapa().isEmpty())
        groupBy = gbm.translate(upit);
       if(!agregat)
        agregat = sm.isAgregat();
    }
    public void setFrom(FromUpit upit){
        from = fm.translate(upit);
       // System.out.println(from);
    }

    public void setOrderBy(OrderByUpit upit){
      orderBy = obm.translate(upit);
       // System.out.println(orderBy);

    }

    public void setJoin(JoinUpit upit){
        join = jm.translate(upit);
        agregat = true;
        unwind = jm.getUndw();
    }

    public void setGroupBy(GroupByUpit upit){
        groupBy = gbm.translate(upit);
        agregat = true;
    }
    public void setWhere(WhereUpit upit){
        where = wm.translate(upit);
    }

    public void obradi(){
       // MongoList.add(Document.parse("{}"));
        if(!join.isEmpty())
            MongoList.add(Document.parse(join));
        if(!unwind.isEmpty())
            MongoList.add(Document.parse(unwind));
        if(!where.isEmpty())
            MongoList.add(Document.parse(where));
        if(!groupBy.isEmpty())
            MongoList.add(Document.parse(groupBy));
        if(!orderBy.isEmpty())
            MongoList.add(Document.parse(orderBy));
        if(!select.isEmpty())
            MongoList.add(Document.parse(select));

        System.out.println(join +"\n" + groupBy + "\n" + orderBy + "\n" + select);
    }

}
