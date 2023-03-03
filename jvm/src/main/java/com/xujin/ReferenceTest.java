package com.xujin;

import java.lang.ref.WeakReference;
import java.util.UUID;

public class ReferenceTest {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());

    }
    public static void testWeakReference(){
        WeakReference<byte[]> weakReference = new WeakReference<byte[]>(new byte[1]);
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
    }

    /**
     * 因为ThreadLocal内部也是使用的弱引用
     * @
     */
    public static void testThreadLocal(){
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("xujin");
        System.out.println(stringThreadLocal.get());
        System.gc();
        System.out.println(stringThreadLocal.get());
    }



}
