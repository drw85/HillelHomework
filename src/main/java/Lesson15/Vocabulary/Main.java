package Lesson15.Vocabulary;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        int x = 0;
        MyVocabularyImpl voc = new MyVocabularyImpl(16);
        initVocabularyWith996Words(voc);
        voc.printLoading();
    }

    private static void initVocabularyWith996Words(MyVocabularyImpl voc) {
        File file = new File("src/Lesson15/Vocabulary/words.txt");
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
            voc.put(en, ru);
        }
    }
}
