package nl.jpoint.chronicle;

import nl.jpoint.chronicle.controller.PageController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PageControllerTest {

    @Test
    public void test() {
        assertEquals("", PageController.parsePageUri(""));
        assertEquals("bla", PageController.parsePageUri("/bla"));
        assertEquals("bla/bla", PageController.parsePageUri("/bla///bla//"));
    }

    @Test
    public void testParseParent_noParent() {
        assertEquals("", PageController.parseParentUri("/bla"));
        assertEquals("", PageController.parseParentUri("/bla/"));
    }

    @Test
    public void testParseParent_simpleParent() {
        assertEquals("test", PageController.parseParentUri("/test/something"));
        assertEquals("test", PageController.parseParentUri("/test/something/"));
    }

    @Test
    public void testParseParent_complexParent() {
        assertEquals("test/something", PageController.parseParentUri("/test/something/else"));
        assertEquals("test/something", PageController.parseParentUri("/test//something///else///"));
    }
}
