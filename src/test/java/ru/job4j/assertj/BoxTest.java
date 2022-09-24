package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .isNotBlank()
                .isEqualTo("Sphere");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(9, 9);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEqualTo("Sphere")
                .isEqualTo("Unknown object");
    }

    @Test
    void whenSphereThenZeroVertices() {
        Box box = new Box(0, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isLessThan(1)
                .isGreaterThan(-1)
                .isZero();
    }

    @Test
    void whenUnknownObjectThenVerticesLessThanZero() {
        Box box = new Box(9, 9);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isNotZero()
                .isLessThan(0)
                .isNegative();
    }

    @Test
    void sphereExists() {
        Box box = new Box(0, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void unknownObjectDoesNotExist() {
        Box box = new Box(9, 9);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void sphereArea() {
        Box box = new Box(0, 10);
        assertThat(box.getArea()).isNotZero()
                .isPositive()
                .isEqualTo(1256.6D, withPrecision(0.05D));
    }

    @Test
    void unknownObjectArea() {
        Box box = new Box(9, 9);
        assertThat(box.getArea()).isZero()
                .isEqualTo(0D);
    }
}