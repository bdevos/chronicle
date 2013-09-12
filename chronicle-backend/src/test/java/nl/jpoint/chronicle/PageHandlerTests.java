package nl.jpoint.chronicle;

import nl.jpoint.chronicle.handler.PageHandler;
import org.junit.Test;
import static org.junit.Assert.*;

public class PageHandlerTests {

    @Test
    public void testIsPageRequest() {
        assertTrue(PageHandler.isPageRequest("/page"));
        assertTrue(PageHandler.isPageRequest("/page/"));
        assertTrue(PageHandler.isPageRequest("/page/bla"));
    }

    @Test
    public void test() {
        assertEquals("", PageHandler.parsePageUri("/page"));
        assertEquals("bla", PageHandler.parsePageUri("/page/bla"));
        assertEquals("bla/bla", PageHandler.parsePageUri("/page/bla///bla//"));
    }

    @Test
    public void testParseParent_noParent() {
        assertEquals("", PageHandler.parseParentUri("/bla"));
        assertEquals("", PageHandler.parseParentUri("/bla/"));
    }

    @Test
    public void testParseParent_simpleParent() {
        assertEquals("test", PageHandler.parseParentUri("/page/test/something"));
        assertEquals("test", PageHandler.parseParentUri("/page/test/something/"));
    }

    @Test
    public void testParseParent_complexParent() {
        assertEquals("test/something", PageHandler.parseParentUri("/page/test/something/else"));
        assertEquals("test/something", PageHandler.parseParentUri("/page/test//something///else///"));
    }
}
