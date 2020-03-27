package Lesson21.LambdaSorting;

import java.util.Comparator;

public enum ComparingVars {
    BY_FIRSTNAME("СОРТИРОВКА ПО ИМЕНИ"),
    BY_LASTNAME("СОРТИРОВКА ПО ФАМИЛИИ"),
    BY_MARKS_AVERAGE("СОРТИРОВКА ПО СРЕДНЕМУ БАЛЛУ");
    private String comparingWay;
    private Comparator<Student> comparator;

    private ComparingVars(String comparingWay) {
        this.comparingWay = comparingWay;
        switch (comparingWay) {
            case "СОРТИРОВКА ПО ИМЕНИ":
                comparator = (st1, st2) -> st1.getFirstname().compareTo(st2.getFirstname());
                break;
            case "СОРТИРОВКА ПО ФАМИЛИИ":
                comparator = (st1, st2) -> st1.getLastname().compareTo(st2.getLastname());
                break;
            case "СОРТИРОВКА ПО СРЕДНЕМУ БАЛЛУ":
                comparator = (st1, st2) -> Double.compare(st1.getMarksAverage(), st2.getMarksAverage());
                break;
        }
    }

//    public String[] getArrayOfComparingWaysInString() {
//        ComparingVars[] ways = ComparingVars.values();
//        String[] toReturn = new String[ways.length];
//        for (int i = 0; i < ways.length; i++) {
//            toReturn[i] = ways[i].name();
//        }
//        return toReturn;
//    }

    public String getComparingWay() {
        return comparingWay;
    }

    public Comparator<Student> getComparator() {
        return comparator;
    }
}
