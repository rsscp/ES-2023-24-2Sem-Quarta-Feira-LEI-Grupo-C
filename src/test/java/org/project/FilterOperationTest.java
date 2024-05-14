package org.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.project.FilterOperation.NOP;

class FilterOperationTest {
    @Test
    public void testOp() {
        assertTrue(FilterOperation.OR.op(true, true));
        assertTrue(FilterOperation.OR.op(true, false));
        assertTrue(FilterOperation.OR.op(false, true));
        assertFalse(FilterOperation.OR.op(false, false));

        assertTrue(FilterOperation.AND.op(true, true));
        assertFalse(FilterOperation.AND.op(true, false));
        assertFalse(FilterOperation.AND.op(false, true));
        assertFalse(FilterOperation.AND.op(false, false));

        assertTrue(NOP.op(true, true));
        assertTrue(NOP.op(true, false));
        assertFalse(NOP.op(false, true));
        assertFalse(NOP.op(false, false));

        assertEquals("-",NOP.getLabel());
    }

    @Test
    public void testGetFilterOperation() {
        assertEquals(FilterOperation.OR, FilterOperation.getFilterOperation("OR"));
        assertEquals(FilterOperation.AND, FilterOperation.getFilterOperation("AND"));
        assertEquals(NOP, FilterOperation.getFilterOperation("NOP"));
        assertEquals(NOP, FilterOperation.getFilterOperation("Invalid"));
    }

    @Test
    public void testGetNextFilterLabel() {
        assertEquals("OR", FilterOperation.getNextFilterLabel("-"));
        assertEquals("AND", FilterOperation.getNextFilterLabel("OR"));
        assertEquals("-", FilterOperation.getNextFilterLabel("AND"));
        assertEquals("-", FilterOperation.getNextFilterLabel("Invalid"));
    }

}