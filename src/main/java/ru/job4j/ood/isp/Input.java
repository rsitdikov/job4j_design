package ru.job4j.ood.isp;

interface Input {
    void in(String data);
}

interface Output {
    void output();
}

interface Calculator {
    void calculate();
}

interface Internet {
    void connect();
}

class RealServer implements Calculator, Internet {

    @Override
    public void calculate() {
        System.out.println("Do work!");
    }

    @Override
    public void connect() {
        System.out.println("Try connect ...");
    }
}