package db.nosql;

import oracle.kv.FaultException;
import oracle.kv.table.PrimaryKey;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;

public class ArtistDM {

    public static String get(int idArtist) {
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("artists");
            PrimaryKey key = table.createPrimaryKey();
            key.put("idartist", idArtist);
            Row row = tableH.get(key, null);
            if (row == null) {
                return null;
            }
            return row.get("name").asString().get();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        return null;
    }

}
