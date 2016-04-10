package db.nosql;

import entities.nosql.Song;
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

public class SongDM {

    public static Song get(String idsong) {
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("songs");
            PrimaryKey key = table.createPrimaryKey();
            key.put("idsong", idsong);
            Row row = tableH.get(key, null);
            if (row == null) {
                return null;
            }
            return new Song(idsong, row.get("idalbum").asInteger().get(), row.get("idgenre").asInteger().get(), row.get("idartist").asInteger().get());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid statement:\n" + e.getMessage());
        } catch (FaultException e) {
            System.out.println("Statement couldn't be executed, please retry: " + e);
        }
        Store.closeStore();
        return null;
    }

    public static List<String> getSongIdsByArtist(int idArtist) {
        List<String> songs = new ArrayList<>();
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("songs");
            Index index = table.getIndex("songsartists");
            IndexKey indexKey = index.createIndexKey();
            FieldRange fieldRange = index.createFieldRange("idartist");
            fieldRange.setStart(idArtist, true);
            fieldRange.setEnd(idArtist, true);
            MultiRowOptions multiRowOptions = fieldRange.createMultiRowOptions();
            TableIterator<Row> iterator = tableH.tableIterator(indexKey, multiRowOptions, null);
            try {
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    songs.add(row.get("idsong").asString().get());
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
        Store.closeStore();
        return songs;
    }
    
    public static List<String> getSongIdsByGenre(int idGenre) {
        List<String> songs = new ArrayList<>();
        try {
            TableAPI tableH = Store.getStore().getTableAPI();
            Table table = tableH.getTable("songs");
            Index index = table.getIndex("songsgenres");
            IndexKey indexKey = index.createIndexKey();
            FieldRange fieldRange = index.createFieldRange("idgenre");
            fieldRange.setStart(idGenre, true);
            fieldRange.setEnd(idGenre, true);
            MultiRowOptions multiRowOptions = fieldRange.createMultiRowOptions();
            TableIterator<Row> iterator = tableH.tableIterator(indexKey, multiRowOptions, null);
            try {
                while (iterator.hasNext()) {
                    Row row = iterator.next();
                    songs.add(row.get("idsong").asString().get());
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
        Store.closeStore();
        return songs;
    }
}
