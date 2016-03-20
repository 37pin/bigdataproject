package db.nosql;

import entities.nosql.Genre;
import java.util.ArrayList;
import java.util.List;
import oracle.kv.FaultException;
import oracle.kv.StatementResult;
import oracle.kv.table.IndexKey;
import oracle.kv.table.PrimaryKey;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;
import oracle.kv.table.TableIterator;

public class GenreDM {

    private static List<Genre> allGenres;

    public List<Genre> getAll() {
        if (allGenres == null) {
            allGenres = new ArrayList<>();
            try {
                TableAPI tableH = Store.getStore().getTableAPI();
                Table table = tableH.getTable("genres");
                IndexKey ik = table.getIndex("idx_genres_idgenre").createIndexKey();
                TableIterator<Row> iter = tableH.tableIterator(ik, null, null);
                try {
                    while (iter.hasNext()) {
                        Row row = iter.next();
                        allGenres.add(new Genre(row.get("idgenre").asInteger().get(), row.get("title").asString().get()));
                    }
                } finally {
                    if (iter != null) {
                        iter.close();
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid statement:\n" + e.getMessage());
            } catch (FaultException e) {
                System.out.println("Statement couldn't be executed, please retry: " + e);
            }
        }
        Store.closeStore();
        return allGenres;
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
        Store.closeStore();
        return null;
    }

}
