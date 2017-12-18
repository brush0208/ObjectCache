package org.brush.ObjectCache;

import org.junit.*;

public class TestObjectCache {

    @Test
    public void testCreateCache()
    {
        Cache<String , String> cache = Cache.getCache();
        MyCache myCache1 = MyCache.getMyCache();
        MyCache myCache = myCache1;
        Assert.assertSame(myCache,myCache1);
    }
   private static class MyCache extends Cache<String,String>{
        private static MyCache myCache=new MyCache();
       private MyCache() {
           super();
       }

       protected String createValue(String s) {

           return new String(s);
       }

       public static MyCache getMyCache() {
           return myCache;
       }
   }
}
