package Lesson6.MyArray;

public class MyIntArray {
    private int[] data;

    public MyIntArray() {
        this.data = new int[0];
    }

    public void add(int intToAdd) {
        int[] tempArr = new int[this.getSize() + 1];
        if (this.data.length > 0) {
            for (int i = 0; i < this.data.length; i++) {
                tempArr[i] = this.data[i];
            }
            tempArr[getSize()] = intToAdd;
        } else {
            tempArr[0] = intToAdd;
        }
        this.data = tempArr;
    }

    public int getSize() {
        return this.data.length;
    }

    public Integer get(int arrayIndex) {
        if (arrayIndex >= this.data.length) {
            throw new IndexOutOfBoundsException("Index is bigger than array size");
        }
        if (arrayIndex < 0) {
            throw new IndexOutOfBoundsException("Index must be a positive number");
        }
        return this.data[arrayIndex];
    }

    public boolean contains(int intToCheck) {
        boolean isContains = false;
        for (int i : this.data) {
            if (i == intToCheck) {
                isContains = true;
            }
        }
        return isContains;
    }

    public void addAll(MyIntArray addingArr) {
        int index = 0;
        int[] tempArr = new int[this.data.length + addingArr.getSize()];
        for (int i = 0; i < this.data.length; i++) {
            tempArr[index] = this.data[i];
            index++;
        }
        for (int i = 0; i < addingArr.getSize(); i++) {
            tempArr[index] = addingArr.get(i);
            index++;
        }
        this.data = tempArr;
    }

    public boolean equals(MyIntArray arrToCheck) {
        int counter = 0;
        if (this.data.length != arrToCheck.getSize()) {
            return false;
        } else {
            for (int i = 0; i < arrToCheck.getSize(); i++) {
                if (this.data[i] == arrToCheck.get(i)) {
                    counter++;
                }
            }
            return counter == arrToCheck.getSize();
        }
    }

    public void clear() {
        this.data = new int[0];
    }

    public int[] indexOf(int intToFindIndex) {
        MyIntArray tempArr = new MyIntArray();
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == intToFindIndex) {
                tempArr.add(i);
            }
        }
        return tempArr.toIntArr();
    }

    public int[] toIntArr() {
        int[] newArr = new int[getSize()];
        for (int i = 0; i < getSize(); i++) {
            newArr[i] = get(i);
        }
        return newArr;
    }

    public void sort() {
        int temp;
        for (int i = 0; i < this.data.length; i++) {
            for (int j = i + 1; j < this.data.length; j++) {
                if (this.data[i] > this.data[j]) {
                    temp = this.data[i];
                    this.data[i] = this.data[j];
                    this.data[j] = temp;
                }
            }
        }
    }

    @Override
    public String toString() {
        String strToPrint = "";
        if (this.data.length < 1) {
            strToPrint = "ARRAY IS EMPTY";
        } else {
            strToPrint += "[ ";
            for (int element :
                    this.data) {
                strToPrint += element + ";";
            }
            strToPrint += " ]";
        }
        return strToPrint;
    }

    public void print() {
        System.out.println("PRINTING ARRAY:");
        System.out.println(toString());
    }
}
