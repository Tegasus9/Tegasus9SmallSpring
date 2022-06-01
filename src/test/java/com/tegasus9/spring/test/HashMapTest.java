package com.tegasus9.spring.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description
 */
public class HashMapTest {
    @Test
    public void hashMapTest(){
        Map<String, Integer> hashmap = new HashMap<>();
        hashmap.put("one",1);
        hashmap.put("two",2);
        hashmap.put("three",3);
        hashmap.put("four",4);
//        for (Map.Entry<String, Integer> stringIntegerEntry : hashmap.entrySet()) {
//            Integer remove = hashmap.remove(stringIntegerEntry.getKey());
//            System.out.println("removeInteger = " + remove);
//            System.out.println(hashmap);
//        }
        Set<String> strings = hashmap.keySet();
        Object[] names = strings.toArray();
        for (Object name : names) {
            Integer removeInteger = hashmap.remove(name);
            System.out.println("removeInteger = " + removeInteger);
            System.out.println("hashmap = " + hashmap);
        }

//        for (int i = hashmap.size()-1; i >=0; i--) {
//            Integer removeInteger = hashmap.remove(names[i]);
//            System.out.println("removeInteger = " + removeInteger);
//            System.out.println("hashmap = " + hashmap);
//        }
//        for (Iterator<Map.Entry<String, Integer>> iterator = hashmap.entrySet().iterator(); iterator.hasNext() ;) {
//            System.out.println("hashmapBefore = " + hashmap);
//            Map.Entry<String, Integer> next = iterator.next();
//            System.out.println("next = " + next.getValue());
//            iterator.remove();
//            System.out.println("hashmap = " + hashmap);
//        }
    }
}
