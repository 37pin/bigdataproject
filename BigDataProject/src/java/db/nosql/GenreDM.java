package db.nosql;

import java.util.LinkedHashMap;
import java.util.Map;
import oracle.kv.FaultException;
import oracle.kv.table.PrimaryKey;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;
import oracle.kv.table.TableIterator;

public class GenreDM {

    private static Map<String, Object> allGenres;

    public static Map<String, Object> getAll() {
        if (allGenres == null) {
            allGenres = new LinkedHashMap<>();
            try {
                TableAPI tableH = Store.getStore().getTableAPI();
                Table table = tableH.getTable("genres");
                PrimaryKey pkey = table.createPrimaryKey();
                TableIterator<Row> iter = tableH.tableIterator(pkey, null, null);
                try {
                    while (iter.hasNext()) {
                        Row row = iter.next();
                        allGenres.put(row.get("title").asString().get(), row.get("idgenre").asInteger().get());
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

    public static String get(int idgenre) {
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("genres");
            PrimaryKey key = table.createPrimaryKey();
            key.put("idgenre", idgenre);
            Row row = tableH.get(key, null);
            if (row == null) return null;
            return row.get("title").asString().get();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        Store.closeStore();
        return null;
    }

}
