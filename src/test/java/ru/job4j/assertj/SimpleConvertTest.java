package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert convert = new SimpleConvert();
        String[] result = convert.toArray("first", "second", "third", "fourth");
        assertThat(result)
                .hasSize(4)
                .contains("third", "first", "second")
                .containsExactly("first", "second", "third", "fourth")
                .containsAnyOf("second", "fourth", "fifth")
                .doesNotContain("fifth", "sixth")
                .startsWith("first", "second")
                .endsWith("third", "fourth")
                .containsSequence("second", "third");
    }

    @Test
    void checkList() {
        SimpleConvert convert = new SimpleConvert();
        List<String> result = convert.toList("Moscow", "Yekaterinburg", "Krasnodar", "Elista",
                "Khabarovsk", "Irkutsk");
        assertThat(result)
                .hasSize(6)
                .contains("Yekaterinburg", Index.atIndex(1))
                .doesNotContain("Chita")
                .allSatisfy(e -> {
                    assertThat(e).isNotNull();
                    assertThat(e.length()).isGreaterThan(5);
                })
                .anySatisfy(e -> assertThat(e).contains("K"))
                .allMatch(e -> e.length() < 30)
                .anyMatch("Irkutsk"::equals)
                .noneMatch(e -> e.startsWith("A"));
    }

    @Test
    void checkSet() {
        SimpleConvert convert = new SimpleConvert();
        Set<String> result = convert.toSet("first", "second", "third", "fourth", "third", "first",
                "fifth", "sixth", "seventh", "second", "eighth");
        assertThat(result)
                .hasSize(8)
                .containsExactlyInAnyOrder("first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth")
                .allSatisfy(e -> assertThat(e).isNotNull());
    }

        @Test
        void checkMap() {
            SimpleConvert convert = new SimpleConvert();
            Map<String, Integer> result = convert.toMap("first", "second", "third", "fourth",
                    "fifth", "sixth", "seventh", "eighth");
            assertThat(result)
                    .hasSize(8)
                    .containsKeys("second", "third", "fourth")
                    .containsValues(0, 7, 5)
                    .doesNotContainKey("tenth")
                    .doesNotContainValue(9)
                    .containsEntry("first", 0);
    }
}