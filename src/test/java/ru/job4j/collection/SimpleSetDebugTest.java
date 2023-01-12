package ru.job4j.collection;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
class SimpleSetDebugTest {
    @Test
    void when4AddAndAddFirstThenFalse() {
        SimpleSetDebug set = new SimpleSetDebug();
        set.add("first");
        set.add("second");
        set.add("third");
        set.add("four");
        assertThat(set.add("first")).isFalse();
    }
}