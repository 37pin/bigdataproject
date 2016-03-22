package db.nosql;

import entities.nosql.Song;
import oracle.kv.FaultException;
import oracle.kv.table.PrimaryKey;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;

public class SongDM {
    
    public static Song getById(String idsong) {
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("songs");
            PrimaryKey key = table.createPrimaryKey();
            key.put("idsong", idsong);
            Row row = tableH.get(key, null);
            return new Song(idsong, row.get("idalbum").asInteger().get(), row.get("idgenre").asInteger().get());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        Store.closeStore();
        return null;
    }
    
}
