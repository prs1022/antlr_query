package test;

import java.util.WeakHashMap;

public class WeakHashMapTest {
    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<Object, Object> map = new WeakHashMap<>();
        for(int i=0;i<1000;i++){
            map.put(new Object(),"1");
//            System.gc();
            Thread.sleep(1000L);
            System.out.println(map.size());
        }
    }
}
