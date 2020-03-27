package Lesson16.MyVocabularyImplByHashMapWithMapInterface;

import java.util.*;

public class MyVocabularyImplWithMapInterface implements Map<String, String> {

    public static class Entry implements Map.Entry<String, String> {
        private String en;
        private String ru;
        private Entry next;
        private Entry prev;


        public Entry(String en, String ru) {
            this.en = en;
            this.ru = ru;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String getKey() {
            return en;
        }

        @Override
        public String getValue() {
            return ru;
        }

        @Override
        public String setValue(String value) {
            this.ru = value;
            return this.ru;
        }

        public Entry getNext() {
            return next;
        }

        public Entry getPrev() {
            return prev;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        public void setPrev(Entry prev) {
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "en='" + en + '\'' +
                    ", ru='" + ru + '\'' +
                    '}';
        }
    }

    private class IteratorImplForMyVocabularyByEntries implements Iterator<Entry> {
        private Entry[] data;
        private Entry currentEntry = null;
        private int currentPosition = 0;

        public IteratorImplForMyVocabularyByEntries(Entry[] data) {
            this.data = data;
            for (int i = 0; i < data.length; i++) {
                if (data[i] != null) {
                    currentEntry = data[i];
                    currentPosition = i;
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return currentEntry != null;
        }

        @Override
        public Entry next() {
            Entry toReturn = currentEntry;
            try {
                if (currentEntry.getNext() != null) {
                    currentEntry = currentEntry.getNext();
                } else if (currentPosition < data.length - 1) {
                    for (int i = currentPosition + 1; i < data.length; i++) {
                        if (data[i] != null) {
                            currentEntry = data[i];
                            currentPosition = i;
                            break;
                        } else {
                            currentEntry = null;
                        }
                    }
                } else {
                    currentEntry = null;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                currentEntry = null;
            }
            return toReturn;
        }
    }

    private int capacity;
    private Entry[] data;
    private final double OVERLOAD_RATIO = 0.75;
    private int wordsCounter = 0;

    public MyVocabularyImplWithMapInterface() {
        this.capacity = 16;
        data = new Entry[capacity];
    }

    public MyVocabularyImplWithMapInterface(int capacity) throws IllegalArgumentException {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        data = new Entry[this.capacity];
    }

    public int size() {
        return wordsCounter;
    }

    @Override
    public boolean isEmpty() {
        return wordsCounter == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return keySet().contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    @Override
    public String get(Object key) {
        try {
            String stringKey = String.valueOf(key);
            if (keySet().contains(stringKey)) {
                Entry current = data[calculateIndex(stringKey)];
                if (current.getKey().equals(stringKey)) {
                    return current.getValue();
                } else {
                    while (current.getNext() != null) {
                        current = current.getNext();
                        if (current.getKey().equals(stringKey)) {
                            return current.getValue();
                        }
                    }
                }
            } else {
                return null;
            }
        } catch (ClassCastException ex) {
            return null;
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
        return null;
    }

    @Override
    public String put(String en, String ru) {
        if (en == null || ru == null) {
            return null;
        }
        if (!keySet().contains(en)) {
            Entry newEntry = new Entry(en, ru);
            if (data[calculateIndex(en)] == null) {
                data[calculateIndex(en)] = newEntry;
            } else {
                Entry current = data[calculateIndex(en)];
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(newEntry);
                newEntry.setPrev(current);
            }
            wordsCounter++;
            if (isOverLoaded()) {
                expandData();
            }
            return en;
        } else {
            return null;
        }
    }

    @Override
    public String remove(Object key) {
        try {
            if (keySet().contains(key)) {
                String strKey = String.valueOf(key);
                Entry current = data[calculateIndex(strKey)];
                if (current.getKey().equals(strKey)) {
                    String toReturn = current.getValue();
                    if (current.getNext() != null) {
                        data[calculateIndex(strKey)] = current.getNext();
                    } else {
                        data[calculateIndex(strKey)] = null;
                    }
                    wordsCounter--;
                    return toReturn;
                } else {
                    while (current.getNext() != null) {
                        current = current.getNext();
                        if (current.getKey().equals(strKey)) {
                            String toReturn = current.getValue();
                            Entry prev = current.getPrev();
                            Entry next = current.getNext();
                            prev.setNext(next);
                            if (next != null) {
                                next.setPrev(prev);
                            }
                            wordsCounter--;
                            return toReturn;
                        }
                    }
                }
            } else {
                return null;
            }
        } catch (ClassCastException ex) {
            return null;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        Set<? extends String> keys = m.keySet();
        for (Object key : keys) {
            try {
                put((String) key, m.get(key));
            } catch (ClassCastException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    @Override
    public void clear() {
        data = new Entry[capacity];
        wordsCounter = 0;
    }

    @Override
    public Set<String> keySet() {
        Set<String> toReturn = new TreeSet();
        IteratorImplForMyVocabularyByEntries iter = iterator();
        while (iter.hasNext()) {
            toReturn.add(iter.next().getKey());
        }
        return toReturn;
    }

    @Override
    public Collection<String> values() {
        ArrayList<String> toReturn = new ArrayList<>();
        IteratorImplForMyVocabularyByEntries iter = iterator();
        while (iter.hasNext()) {
            toReturn.add(iter.next().getValue());
        }
        return toReturn;
    }

    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        Set<Map.Entry<String, String>> toReturn = new HashSet<>();
        IteratorImplForMyVocabularyByEntries iter = iterator();
        while (iter.hasNext()) {
            toReturn.add(iter.next());
        }
        return toReturn;
    }

    private int calculateIndex(String key) {
        int index = Math.abs(calculateHash(key)) % capacity;
        return index;
    }

    private int calculateHash(String key) {
        return key != null ? key.hashCode() : 0;
    }

    private int calculateMaxLoading() {
        int max = 0;
        for (int i = 0; i < capacity; i++) {
            int currentMax = 0;
            if (data[i] != null) {
                currentMax++;
                Entry currentEntry = data[i];
                while (currentEntry.getNext() != null) {
                    currentMax++;
                    currentEntry = currentEntry.getNext();
                }
                if (max < currentMax) {
                    max = currentMax;
                }
            }
        }
        return max;
    }

    private boolean isOverLoaded() {
        double ratio = wordsCounter * OVERLOAD_RATIO;
        return capacity < ratio;
    }

    private int[] calculateLoading() {
        int maxLoad = calculateMaxLoading();
        int[] loadingData = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            if (data[i] != null) {
                Entry current = data[i];
                while (current.getNext() != null) {
                    loadingData[i]++;
                    current = current.getNext();
                }
                if (loadingData[i] > maxLoad) {
                    maxLoad = loadingData[i];
                }
            } else {
                loadingData[i] = 0;
            }
        }
        return loadingData;
    }

    public void printLoading() {
        int maxLoad = calculateMaxLoading();
        int[] loadingData = calculateLoading();
        System.out.println("Т А Б Л И Ц А    Н А Г Р У З К И");
        for (int i = maxLoad + 1; i >= 0; i--) {
            String row = "";
            for (int j = 0; j < loadingData.length; j++) {
                if (i <= loadingData[j]) {
                    row += " * ";
                } else {
                    row += "   ";
                }
            }
            System.out.println(row);
        }
    }

    private void expandData() {
        capacity++;
        MyVocabularyImplWithMapInterface newVoc = new MyVocabularyImplWithMapInterface(capacity);
        IteratorImplForMyVocabularyByEntries iter = iterator();
        while (iter.hasNext()) {
            Entry currentIteration = iter.next();
            Entry newEntry = new Entry(currentIteration.getKey(), currentIteration.getValue());
            if (newVoc.data[calculateIndex(currentIteration.getKey())] == null) {
                newVoc.data[calculateIndex(currentIteration.getKey())] = newEntry;
            } else {
                Entry current = newVoc.data[calculateIndex(currentIteration.getKey())];
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(newEntry);
                newEntry.setPrev(current);
            }
            newVoc.wordsCounter++;
        }
        data = newVoc.data;
        wordsCounter = newVoc.wordsCounter;
    }

    public IteratorImplForMyVocabularyByEntries iterator() {
        return new IteratorImplForMyVocabularyByEntries(data);
    }

    public int getCapacity() {
        return this.capacity;
    }
}
