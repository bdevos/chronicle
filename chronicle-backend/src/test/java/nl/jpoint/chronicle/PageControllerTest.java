package nl.jpoint.chronicle;

import nl.jpoint.chronicle.controller.PageController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PageControllerTest {

    @Test
    public void testIsPageRequest() {
        assertTrue(PageController.isPageRequest("/page"));
        assertTrue(PageController.isPageRequest("/page/"));
        assertTrue(PageController.isPageRequest("/page/bla"));
    }

    @Test
    public void test() {
        assertEquals("", PageController.parsePageUri("/page"));
        assertEquals("bla", PageController.parsePageUri("/page/bla"));
        assertEquals("bla/bla", PageController.parsePageUri("/page/bla///bla//"));
    }

    @Test
    public void testParseParent_noParent() {
        assertEquals("", PageController.parseParentUri("/bla"));
        assertEquals("", PageController.parseParentUri("/bla/"));
    }

    @Test
    public void testParseParent_simpleParent() {
        assertEquals("test", PageController.parseParentUri("/page/test/something"));
        assertEquals("test", PageController.parseParentUri("/page/test/something/"));
    }

    @Test
    public void testParseParent_complexParent() {
        assertEquals("test/something", PageController.parseParentUri("/page/test/something/else"));
        assertEquals("test/something", PageController.parseParentUri("/page/test//something///else///"));
    }
}
