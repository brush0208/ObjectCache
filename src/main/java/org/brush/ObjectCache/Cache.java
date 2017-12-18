package org.brush.ObjectCache;


import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class Cache<K,V>{

    private final Map<K,SoftReference<V>> ObjectMap=new HashMap<K,SoftReference<V>>();

    private final ReferenceQueue<V> referenceQueue=new ReferenceQueue<V>();
    protected Cache(){
    }
    /***
     * 获得一个Cache实例
     *
     */
    public static <K, V> Cache<K, V> getCache() {
        return new Cache<K, V>(){
            protected V createValue(K k) {
                return null;
            }
        };
    }
    public void addObject(K key,V value)
    {
        SoftReference<V> reference = new SoftReference<V>(value, referenceQueue);
        this.ObjectMap.put(key,reference);
    }
    public V getObject(K k)
    {
        V v=null;
        if(ObjectMap.containsKey(k))
        {
            v=ObjectMap.get(k).get();
        }
        if(v==null)
        {
            v=createValue(k);
            this.addObject(k,v);
        }
        return v;
    }

    protected abstract V createValue(K k);

}
