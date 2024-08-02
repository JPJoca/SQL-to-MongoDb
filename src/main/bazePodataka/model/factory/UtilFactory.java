package main.bazePodataka.model.factory;

import java.util.ArrayList;

public class UtilFactory  {

    public static FromFactory ff = new FromFactory();
    public static GroupByFactory gbf = new GroupByFactory();
    public static HavingFactory hf = new HavingFactory();
    public static JoinFactory jf = new JoinFactory();
    public static OrderByFactory obf = new OrderByFactory();
    public static SelectFactory sf = new SelectFactory();
    public static  WhereFactory wf = new WhereFactory();

    public static ClouseFactory getFactory(ArrayList<String> upiti){

        String naziv = upiti.get(0);

        if(naziv.equalsIgnoreCase("SELECT"))
            return  sf;
        else if(naziv.equalsIgnoreCase("FROM"))
            return  ff;
        else if(naziv.equalsIgnoreCase("WHERE"))
            return  wf;
        else if(naziv.equalsIgnoreCase("HAVING"))
            return  hf;
        else if(naziv.equalsIgnoreCase("JOIN") || naziv.contains("JOIN") || naziv.contains("join"))
            return  jf;
        else if(naziv.equalsIgnoreCase("ORDER BY"))
            return  obf;
        else if(naziv.equalsIgnoreCase("GROUP BY"))
            return  gbf;
        return null;
    }
}
