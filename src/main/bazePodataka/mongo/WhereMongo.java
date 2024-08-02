package main.bazePodataka.mongo;

import main.bazePodataka.model.implentation.GlavniUpit;
import main.bazePodataka.model.implentation.WhereUpit;
import main.bazePodataka.model.implentation.pomocniUpiti.*;

public class WhereMongo implements Mongo{
    @Override
    public String translate(GlavniUpit query) {
        WhereUpit upit = (WhereUpit) query;
        StringBuilder where = new StringBuilder();
        boolean and = false, or = false;
        boolean like = false;
        for(PomocniUpit p : upit.getPomocniUpiti()){
            if(p instanceof AndOrUpit){
                if(((AndOrUpit)p).isAnd())
                    and = true;
                else
                    or = true;
            }
        }


       where.append("{\n" + "\"$match\": {\n");

       if(and){
           where.append("    \"$and\": [\n");
       }else if(or){
           where.append("    \"$or\": [\n");
       }

       for(PomocniUpit p: upit.getPomocniUpiti()){
           if(p instanceof GreaterLoverEqUpit){
               if(and || or)
                   where.append("{");
               where.append("\"" + ((GreaterLoverEqUpit)p).getBaza1() + "\":");
               if(((GreaterLoverEqUpit) p).getTypeOf().equals("eq"))
                   where.append(((GreaterLoverEqUpit) p).getBaza2() + "\n" + "},\n");
               else
               where.append("{\n\"$" + ((GreaterLoverEqUpit)p).getTypeOf() + "\": "  + ((GreaterLoverEqUpit) p).getBaza2() + "\n" + "}\n" + "},\n");
           }else if(p instanceof LikeUpit){
                where.append( "{\n\"" + ((LikeUpit)p).getBase() + "\": {\n" + "\"$regex\": \"^" + ((LikeUpit)p).getKarakter()  + "$\"\n" + "}\n" + "},\n");
              //  like = true;
           } else if(p instanceof InUpit){
               where.append(
                       "        \"$expr\": {\n" +
                       "          \"$in\": [\n" +
                       "            \""+((InUpit) p).getBase()+"\",\n" +
                       "            [\n" );
               for (Integer i : ((InUpit) p).getSet() ) {
                   where.append(i + ",\n");
               }
               where.delete(where.length()-2,where.length());
               where.append("]]}},\n");
           }


       }

        where.delete(where.length()-2,where.length());


       if(and || or)
            where.append("]}}");
       else
           where.append("}");

      // if(like && (and || or))
           //where.delete(where.length()-1,where.length());
        System.out.println(where.toString());

        //select first_name from hr.employees where last_name like 'King'

        return where.toString();
    }
}
