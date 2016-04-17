package db.nosql;

import entities.Like;
import java.util.ArrayList;
import java.util.List;
import oracle.kv.FaultException;
import oracle.kv.table.FieldRange;
import oracle.kv.table.Index;
import oracle.kv.table.IndexKey;
import oracle.kv.table.MultiRowOptions;
import oracle.kv.table.PrimaryKey;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;
import oracle.kv.table.TableIterator;

public class LikeDM {

    public static Like get(String email, String idSong) {
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("likes");
            PrimaryKey key = table.createPrimaryKey();
            key.put("email", email);
            key.put("idsong", idSong);
            Row row = tableH.get(key, null);
            if (row == null) {
                return null;
            }
            return new Like(email, idSong, row.get("recommend").asInteger().get());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        return null;
    }

    public static List<String> getSongsByEmail(String email) {
        List<String> songs = new ArrayList<>();
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("likes");
            PrimaryKey key = table.createPrimaryKey();
            key.put("email", email);
            List<Row> rows = tableH.multiGet(key, null, null);
            for (Row row : rows) {
                songs.add(row.get("idSong").asString().get());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        return songs;
    }

    public static int getLikesCount(String idSong) {
        int cnt = 0;
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("likes");
            Index index = table.getIndex("likessongs");
            IndexKey indexKey = index.createIndexKey();
            FieldRange fieldRange = index.createFieldRange("idsong");
            fieldRange.setStart(idSong, true);
            fieldRange.setEnd(idSong, true);
            MultiRowOptions multiRowOptions = fieldRange.createMultiRowOptions();
            TableIterator<Row> iterator = tableH.tableIterator(indexKey, multiRowOptions, null);
            try {
                while (iterator.hasNext()) {
                    iterator.next();
                    cnt++;
                }
            } finally {
                if (iterator != null) {
                    iterator.close();
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        return cnt;
    }

    public static void insert(String email, String idSong, int recommend) {
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("likes");
            Row row = table.createRow();
            row.put("email", email);
            row.put("idsong", idSong);
            row.put("recommend", recommend);
            tableH.put(row, null, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
    }

    public static void update(String email, String idSong, int recommend) {
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("likes");
            Row row = table.createRow();
            row.put("email", email);
            row.put("idsong", idSong);
            row.put("recommend", recommend);
            tableH.putIfPresent(row, null, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
    }

    public static void drop(String email, String idSong) {
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("likes");
            PrimaryKey key = table.createPrimaryKey();
            key.put("email", email);
            key.put("idsong", idSong);
            tableH.delete(key, null, null);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
    }

}
