package Lesson15.Vocabulary;

import java.util.Iterator;

public class MyVocabularyImpl {

    private class Entry {
        private String en;
        private String ru;
        private Entry next;

        public Entry(String en, String ru) {
            this.en = en;
            this.ru = ru;
            this.next = null;
        }

        public String getEn() {
            return en;
        }

        public String getRu() {
            return ru;
        }

        public Entry getNext() {
            return next;
        }

        private void setNext(Entry next) {
            this.next = next;
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

        private IteratorImplForMyVocabularyByEntries(Entry[] data) {
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

    public MyVocabularyImpl() {
        this.capacity = 16;
        data = new Entry[capacity];
    }

    public MyVocabularyImpl(int capacity) throws IllegalArgumentException {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        data = new Entry[capacity];
    }

    public int size() {
        return wordsCounter;
    }

    public void put(String en, String ru) {
        Entry newEntry = new Entry(en, ru);
        if (data[calculateIndex(en)] == null) {
            data[calculateIndex(en)] = newEntry;
        } else {
            Entry current = data[calculateIndex(en)];
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newEntry);
        }
        wordsCounter++;
        if (isOverLoaded()) {
            expandData();
        }
    }

    public String get(String en) {
        if (data[calculateIndex(en)] != null) {
            Entry current = data[calculateIndex(en)];
            while (current.getNext() != null) {
                if (current.getEn().equals(en)) {
                    return current.getRu();
                }
                current = current.getNext();
            }
            return data[calculateIndex(en)].getRu();
        } else {
            return "";
        }
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
        capacity = capacity * 2;
        MyVocabularyImpl newVoc = new MyVocabularyImpl(capacity);
        IteratorImplForMyVocabularyByEntries iter = iterator();
        while (iter.hasNext()) {
            Entry currentIteration = iter.next();
            Entry newEntry = new Entry(currentIteration.getEn(), currentIteration.getRu());
            if (newVoc.data[calculateIndex(currentIteration.getEn())] == null) {
                newVoc.data[calculateIndex(currentIteration.getEn())] = newEntry;
            } else {
                Entry current = newVoc.data[calculateIndex(currentIteration.getEn())];
                while (current.getNext() != null) {
                    current = current.getNext();
                }
                current.setNext(newEntry);
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
