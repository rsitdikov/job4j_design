package generic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class MemStoreTest {
    private MemStore<User> mem = new MemStore<>();

    @Before
    public void init() {
        User first = new User("0001", "Ivanov");
        User second = new User("0002", "Petrov");
        User third = new User("0003", "Sidorov");
        mem.add(first);
        mem.add(second);
        mem.add(third);
    }

    @Test
    public void whenAddUserThenShouldResultIvanov() {
        String result = mem.findById("0001").getName();
        assertEquals(result, "Ivanov");
    }

    @Test
    public void whenDeleteUserThenShouldResultNull() {
        mem.delete("0001");
        User result = mem.findById("0001");
        assertNull(result);
    }

    @Test
    public void whenReplaceUserThenShouldResultSmirnov() {
        mem.replace("0002", new User("0004", "Smirnov"));
        String result = mem.findById("0004").getName();
        assertEquals(result, "Smirnov");
    }

    @Test
    public void whenReplaceUserThenOldIdReturnsNull() {
        mem.replace("0002", new User("0004", "Smirnov"));
        User result = mem.findById("0002");
        assertNull(result);
    }

    @Test
    public void whenIdExistsThenResultNotNull() {
        assertNotNull(mem.findById("0003"));
    }

    @Test
    public void whenIdNotExistsThenResultNull() {
        assertNull(mem.findById("9999"));
    }
}