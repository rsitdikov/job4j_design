package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("collection contains no data");
    }

    @Test
    void whenThereIsNoDelimiterCharacter() {
        NameLoad nameLoad = new NameLoad();
        String data = "KeyValue";
        assertThatThrownBy(() -> nameLoad.parse(data))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain the symbol \"=\"", data));
    }

    @Test
    void whenDelimiterCharacterIsFirst() {
        NameLoad nameLoad = new NameLoad();
        String data = "=KeyValue";
        assertThatThrownBy(() -> nameLoad.parse(data))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain a key", data));
    }

    @Test
    void whenDelimiterCharacterIsLast() {
        NameLoad nameLoad = new NameLoad();
        String data = "KeyValue=";
        assertThatThrownBy(() -> nameLoad.parse(data))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: %s does not contain a value", data));
    }

    @Test
    void whenArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }
}