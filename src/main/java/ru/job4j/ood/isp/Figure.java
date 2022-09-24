package ru.job4j.ood.isp;

interface Figure {
    double perimetr();

    double square();

    double volume();
}

interface Figure2D {
    double perimetr();

    double square();
}

interface Figure3D {
    double volume();
}