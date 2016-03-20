package db.nosql;

import entities.nosql.Genre;
import java.util.List;
import oracle.kv.FaultException;
import oracle.kv.StatementResult;
import oracle.kv.table.PrimaryKey;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;

public class GenreDM {

    public List<Genre> getAll() {
        TableAPI tableAPI = Store.getStore().getTableAPI();
        StatementResult result = null;
        String statement = null;
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("genres");
            PrimaryKey key = table.createPrimaryKey();
            key.put("idgenre", 1);
            List<Row> myRows = null;
            myRows = tableH.multiGet(key, null, null);
            for (Row r : myRows) {
                System.out.println("======>" + r.get("title").asString().get());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        return null;
    }

    public Genre getById(int idgenre) {
        TableAPI tableAPI = Store.getStore().getTableAPI();
        StatementResult result = null;
        String statement = null;
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("genres");
            PrimaryKey key = table.createPrimaryKey();
            key.put("idgenre", idgenre);
            Row row = tableH.get(key, null);
            return new Genre(idgenre, row.get("title").asString().get());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        return null;
    }

}
