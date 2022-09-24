package ru.job4j.ood.lsp;

class WholeNumber {
    private int value;

    WholeNumber() {

    }

    WholeNumber(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class PositiveNumber extends WholeNumber {

    PositiveNumber() {

    }

    /*
    Первый пример нарушения принципа LSP:
    предусловие усилено в подклассе.
     */
    PositiveNumber(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must be positive!");
        }
        super.setValue(value);
    }

}

public class FirstExample {

    public static void main(String[] args) {
        WholeNumber wholeNumber = new PositiveNumber(-10);
        System.out.println(wholeNumber.getValue());
    }
}

