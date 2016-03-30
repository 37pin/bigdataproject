package db.nosql;

import entities.nosql.Like;
import oracle.kv.FaultException;
import oracle.kv.table.PrimaryKey;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;

public class LikeDM {

    public static Like get(String email, String idSong) {
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("likes");
            PrimaryKey key = table.createPrimaryKey();
            key.put("email", email);
            key.put("idsong", idSong);
            Row row = tableH.get(key, null);
            if (row == null) return null;
            return new Like(email, idSong, row.get("recommend").asInteger().get());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        Store.closeStore();
        return null;
    }
    
    public static void insert(Like like) {
	try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("likes");
            Row row = table.createRow();
            row.put("email", like.getEmail());
            row.put("idsong", like.getIdSong());
            row.put("recommend", like.getRecommend());
            tableH.put(row, null, null);
	}
	catch (IllegalArgumentException e) {
		System.out.println("Invalid statement:\n" + e.getMessage());
	}
	catch (FaultException e) {
		System.out.println("Statement couldn't be executed, please retry: " + e);
	}
    }
    
    public static void update(Like like) {
	try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("likes");
            Row row = table.createRow();
            row.put("email", like.getEmail());
            row.put("idsong", like.getIdSong());
            row.put("recommend", like.getRecommend());
            tableH.putIfPresent(row, null, null);
	}
	catch (IllegalArgumentException e) {
		System.out.println("Invalid statement:\n" + e.getMessage());
	}
	catch (FaultException e) {
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
	}
	catch (IllegalArgumentException e) {
		System.out.println("Invalid statement:\n" + e.getMessage());
	}
	catch (FaultException e) {
		System.out.println("Statement couldn't be executed, please retry: " + e);
	}
    }
    
}
