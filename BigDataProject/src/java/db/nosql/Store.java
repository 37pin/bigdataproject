package db.nosql;

import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;

public class Store {
    
    private static KVStore store;
    
    public static KVStore getStore() {
        if (store == null) {
            String storeName = "kvstore";
            String hostName = "bigdatalite.localdomain";
            String hostPort = "5000";
            store = KVStoreFactory.getStore(new KVStoreConfig(storeName, hostName + ":" + hostPort));
        }
        return store;
    }
    
}
