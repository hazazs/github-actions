package hu.hazazs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CloneTest {

    private Person tibot;
    private Person clone;

    @Before
    public void init() {
        tibot = new Person("Tibor", "Hazafi", new Date(1986, 7, 12));
        clone = new Person(tibot);
    }

    @Test
    public void referenceTest() {
        assertNotSame(tibot, clone);
    }

    @Test
    public void classTest() {
        assertSame(tibot.getClass(), clone.getClass());
    }

}