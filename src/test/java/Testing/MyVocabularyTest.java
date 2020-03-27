package Testing;

import Lesson16.MyVocabularyImplByHashMapWithMapInterface.MyVocabularyImplWithMapInterface;
import org.junit.Assert;
import org.junit.Test;


import java.io.*;
import java.util.*;

public class MyVocabularyTest {
    @Test
    public void constructor_Empty() {
        //given
        MyVocabularyImplWithMapInterface myVoc;
        //when
        myVoc = new MyVocabularyImplWithMapInterface();
        //then
        Assert.assertNotNull(myVoc);
        Assert.assertEquals(16, myVoc.getCapacity());
        Assert.assertEquals(0, myVoc.size());
    }

    @Test
    public void constructor_WithCapacityParam() {
        //given
        MyVocabularyImplWithMapInterface myVoc;
        int initCapacityParam = 100;
        //when
        myVoc = new MyVocabularyImplWithMapInterface(initCapacityParam);
        //then
        Assert.assertNotNull(myVoc);
        Assert.assertEquals(initCapacityParam, myVoc.getCapacity());
        Assert.assertEquals(0, myVoc.size());
    }

    @Test
    public void isEmpty_WhenEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //then
        Assert.assertTrue(myVoc.isEmpty());
    }

    @Test
    public void isEmpty_WhenNotEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("cat", "кот");
        //then
        Assert.assertFalse(myVoc.isEmpty());
    }

    @Test
    public void put_Correct() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("cat", "кот");
        //then
        Assert.assertEquals(1, myVoc.size());
        Assert.assertFalse(myVoc.isEmpty());
    }

    @Test
    public void put_Null() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put(null, null);
        //then
        Assert.assertEquals(0, myVoc.size());
        Assert.assertTrue(myVoc.isEmpty());
    }

    @Test
    public void put_Duplicate() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("cat", "кот");
        myVoc.put("cat", "кот");
        //then
        Assert.assertEquals(1, myVoc.size());
        Assert.assertFalse(myVoc.isEmpty());
    }

    @Test
    public void put_100Strings() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        ArrayList<String[]> array = get100StringArraysFromTextFile();
        //when
        for (int i = 0; i < 100; i++) {
            myVoc.put(array.get(i)[0], array.get(i)[1]);
        }
        //then
        Assert.assertEquals(100, myVoc.size());
        Assert.assertFalse(myVoc.isEmpty());
    }

    @Test
    public void containsKey_WhenItIsNot() {
//given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
//then
        Assert.assertFalse(myVoc.containsKey("cat"));

    }

    @Test
    public void containsKey_WhenItIsPresent() {
//given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("cat", "кот");
//then
        Assert.assertTrue(myVoc.containsKey("cat"));

    }

    @Test
    public void containsValue_WhenItIsNot() {
//given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
//then
        Assert.assertFalse(myVoc.containsValue("кот"));

    }

    @Test
    public void containsValue_WhenItIsPresent() {
//given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("cat", "кот");
//then
        Assert.assertTrue(myVoc.containsValue("кот"));

    }

    @Test
    public void get_WhenEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //then
        Assert.assertEquals(null, myVoc.get("cat"));
    }

    @Test
    public void get_WhenNotPresent() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("dog", "собака");
        //then
        Assert.assertEquals(null, myVoc.get("cat"));
    }

    @Test
    public void get_WhenPresent() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("cat", "кот");
        //then
        Assert.assertEquals("кот", myVoc.get("cat"));
    }

    @Test
    public void get_Null() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("cat", "кот");
        //then
        Assert.assertEquals(null, myVoc.get(null));
    }

    @Test
    public void removeWhenEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //then
        Assert.assertEquals(null, myVoc.remove("cat"));
    }

    @Test
    public void remove_WhenNotPresent() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("dog", "собака");
        //then
        Assert.assertEquals(null, myVoc.remove("cat"));
    }

    @Test
    public void remove_WhenPresent() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("cat", "кот");
        //then
        Assert.assertEquals("кот", myVoc.remove("cat"));
    }

    @Test
    public void remove_Null() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("cat", "кот");
        //then
        Assert.assertEquals(null, myVoc.get(null));
    }

    @Test
    public void putAll_ToEmptyVocabulary_MapTypeIsCorrect_UniqueValues() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        Map map = new HashMap<String, String>();
        //when
        map.put("cat", "кот");
        map.put("dog", "собака");
        myVoc.putAll(map);
        //then
        Assert.assertFalse(myVoc.isEmpty());
        Assert.assertEquals(map.size(), myVoc.size());
    }

    @Test
    public void putAll_ToEmptyVocabulary_MapTypeIsCorrect_DuplicateValues() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        Map map = new HashMap<String, String>();
        //when
        map.put("cat", "кот");
        map.put("dog", "собака");
        myVoc.putAll(map);
        myVoc.putAll(map);
        //then
        Assert.assertFalse(myVoc.isEmpty());
        Assert.assertEquals(map.size(), myVoc.size());
    }

    @Test
    public void putAll_ToEmptyVocabulary_MapTypeIsNotCorrect_UniqueValues() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        Map map = new HashMap<Integer, Integer>();
        //when
        map.put(1, 2);
        map.put(3, 4);
        myVoc.putAll(map);
        //then
        Assert.assertTrue(myVoc.isEmpty());
        Assert.assertEquals(0, myVoc.size());
    }

    @Test
    public void putAll_ToEmptyVocabulary_MapTypeIsNotCorrect_DuplicateValues() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        Map map = new HashMap<Integer, Integer>();
        //when
        map.put(1, 2);
        map.put(1, 2);
        myVoc.putAll(map);
        //then
        Assert.assertTrue(myVoc.isEmpty());
        Assert.assertEquals(0, myVoc.size());
    }

    @Test
    public void putAll_MapTypeIsCorrect_UniqueValues() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        Map map = new HashMap<String, String>();
        //when

        myVoc.put("fish", "рыба");
        myVoc.put("bird", "птица");

        map.put("cat", "кот");
        map.put("dog", "собака");
        myVoc.putAll(map);
        //then
        Assert.assertFalse(myVoc.isEmpty());
        Assert.assertEquals(4, myVoc.size());
    }

    @Test
    public void putAll_MapTypeIsCorrect_DuplicateValues() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        Map map = new HashMap<String, String>();
        //when
        myVoc.put("fish", "рыба");
        myVoc.put("bird", "птица");
        myVoc.put("horse", "лошадь");
        map.put("cat", "кот");
        map.put("dog", "собака");
        map.put("horse", "лошадь");
        map.put("wolf", "волк");
        myVoc.putAll(map);
        //then
        Assert.assertFalse(myVoc.isEmpty());
        Assert.assertEquals(6, myVoc.size());
    }

    @Test
    public void putAll_MapTypeIsNotCorrect_UniqueValues() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        Map map = new HashMap<Integer, Integer>();
        //when
        myVoc.put("fish", "рыба");
        myVoc.put("bird", "птица");
        myVoc.put("horse", "лошадь");
        map.put(1, 2);
        myVoc.putAll(map);
        //then
        Assert.assertFalse(myVoc.isEmpty());
        Assert.assertEquals(3, myVoc.size());
    }

    @Test
    public void putAll_MapTypeIsNotCorrect_DuplicateValues() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        Map map = new HashMap<Integer, Integer>();
        //when
        myVoc.put("fish", "рыба");
        myVoc.put("bird", "птица");
        myVoc.put("horse", "лошадь");
        map.put(1, 2);
        map.put(1, 2);
        myVoc.putAll(map);
        //then
        Assert.assertFalse(myVoc.isEmpty());
        Assert.assertEquals(3, myVoc.size());
    }

    @Test
    public void clear_WhenEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.clear();
        //then
        Assert.assertEquals(0, myVoc.size());
        Assert.assertTrue(myVoc.isEmpty());
    }

    @Test
    public void clear_WhenNotEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("fish", "рыба");
        myVoc.put("bird", "птица");
        myVoc.put("horse", "лошадь");
        myVoc.clear();
        //then
        Assert.assertEquals(0, myVoc.size());
        Assert.assertTrue(myVoc.isEmpty());
    }

    @Test
    public void keySet_WhenEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        Set<String> set = myVoc.keySet();
        //then
        Assert.assertTrue(set.isEmpty());
        Assert.assertEquals(0, set.size());
    }

    @Test
    public void keySet_WhenNotEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("key1", "value1");
        myVoc.put("key2", "value2");
        myVoc.put("key3", "value3");
        myVoc.put("key4", "value4");
        myVoc.put("key5", "value5");
        Set<String> set = myVoc.keySet();
        //then
        Assert.assertFalse(set.isEmpty());
        Assert.assertEquals(5, set.size());
        Assert.assertTrue(set.contains("key1"));
        Assert.assertTrue(set.contains("key2"));
        Assert.assertTrue(set.contains("key3"));
        Assert.assertTrue(set.contains("key4"));
        Assert.assertTrue(set.contains("key5"));
    }

    @Test
    public void values_WhenEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        Collection<String> collection = myVoc.values();
        //then
        Assert.assertTrue(collection.isEmpty());
        Assert.assertEquals(0, collection.size());
    }

    @Test
    public void values_WhenNotEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("key1", "value1");
        myVoc.put("key2", "value2");
        myVoc.put("key3", "value3");
        myVoc.put("key4", "value4");
        myVoc.put("key5", "value5");
        Collection<String> collection = myVoc.values();
        //then
        Assert.assertFalse(collection.isEmpty());
        Assert.assertEquals(5, collection.size());
        Assert.assertTrue(collection.contains("value1"));
        Assert.assertTrue(collection.contains("value2"));
        Assert.assertTrue(collection.contains("value3"));
        Assert.assertTrue(collection.contains("value4"));
        Assert.assertTrue(collection.contains("value5"));
    }

    @Test
    public void iterator_WhenEmpty() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        Iterator iterator = myVoc.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        //then
        Assert.assertTrue(myVoc.isEmpty());
        Assert.assertEquals(0, myVoc.size());
        Assert.assertEquals(0, count);
    }

    @Test
    public void iterator_WhenNotEmpty_5elements() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        myVoc.put("key1", "value1");
        myVoc.put("key2", "value2");
        myVoc.put("key3", "value3");
        myVoc.put("key4", "value4");
        myVoc.put("key5", "value5");

        Iterator iterator = myVoc.iterator();
        ArrayList<Object> values = new ArrayList<Object>();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            values.add(value);
        }
        //then
        for (int i = 0; i < 5; i++) {
            Object obj = values.get(i);
            try {
                MyVocabularyImplWithMapInterface.Entry casted = (MyVocabularyImplWithMapInterface.Entry) obj;
                Assert.assertTrue(casted.getKey().equals("key" + (i + 1)) && casted.getValue().equals("value" + (i + 1)));
            } catch (ClassCastException ex) {
                Assert.fail("ClassCastException");
            }
        }

        Assert.assertFalse(myVoc.isEmpty());
        Assert.assertEquals(5, myVoc.size());
        Assert.assertEquals(5, values.size());
    }

    @Test
    public void iterator_WhenNotEmpty_100elements() {
        //given
        MyVocabularyImplWithMapInterface myVoc = new MyVocabularyImplWithMapInterface();
        //when
        ArrayList<String[]> arr100 = get100StringArraysFromTextFile();
        for (int i = 0; i < arr100.size(); i++) {
            myVoc.put(arr100.get(i)[0], arr100.get(i)[1]);
        }
        Iterator iterator = myVoc.iterator();

        //then
        ArrayList<Object> objectsFromIterator = new ArrayList<Object>();
        ArrayList<String> keys = new ArrayList<String>();
        ArrayList<String> vals = new ArrayList<String>();
        while (iterator.hasNext()) {
            Object value = iterator.next();
            objectsFromIterator.add(value);
        }
        for (int i = 0; i < objectsFromIterator.size(); i++) {
            Object obj = objectsFromIterator.get(i);
            try {
                MyVocabularyImplWithMapInterface.Entry casted = (MyVocabularyImplWithMapInterface.Entry) obj;
                keys.add(casted.getKey());
                vals.add(casted.getValue());
            } catch (ClassCastException ex) {
                Assert.fail("ClassCastException");
            }
        }

        for (String[] strArr : arr100) {
            String k = strArr[0];
            String v = strArr[1];
            Assert.assertTrue(keys.contains(k));
            Assert.assertTrue(vals.contains(v));
        }

        Assert.assertFalse(myVoc.isEmpty());
        Assert.assertEquals(100, myVoc.size());
        Assert.assertEquals(100, objectsFromIterator.size());
    }

    private static ArrayList<String[]> get100StringArraysFromTextFile() {
        ArrayList<String[]> toReturn = new ArrayList<String[]>();
        ArrayList<String[]> strArr = new ArrayList<String[]>();
        File file = new File("src/main/java/Lesson15/Vocabulary/words.txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String st = "";
        while (true) {
            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            String split1 = st.split("\\[")[0];
            String en = split1.substring(0, split1.length() - 1);
            String split2 = st.split("\\]")[1];
            String ru = split2.substring(3, split2.length());
            strArr.add(new String[]{en, ru});
        }
        for (int i = 0; i < 100; i++) {
            toReturn.add(strArr.get(i));
        }
        return toReturn;
    }
}
