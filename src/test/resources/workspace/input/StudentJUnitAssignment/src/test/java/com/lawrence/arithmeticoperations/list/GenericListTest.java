package com.lawrence.arithmeticoperations.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lawrence.list.GenericList;

class GenericListTest
{
    private int capacity;
    private Object element;

    @BeforeEach
    void SetupGenericListTest()
    {
        capacity = 1;
        element = "test";
    }

    @Test
    void testGenericListDefaultConstructor()
    {
        GenericList list = new GenericList();
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    void testGenericListPamaterizedConstructor()
    {
        GenericList list = new GenericList(capacity);
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    void testSize()
    {
        GenericList list = new GenericList();
        list.add(element);
        assertEquals(1, list.size());
    }

    @Test
    void testGetElement()
    {
        GenericList list = new GenericList();
        list.add(element);
        assertEquals(element, list.get(0));
    }

    @Test
    void testAddElement()
    {
        GenericList list = new GenericList();
        list.add(element);
        assertEquals(1, list.size());
        assertEquals(element, list.get(0));
    }

    @Test
    void testInsertElement()
    {
        GenericList list = new GenericList();
        list.add(element);
        list.add(element);
        int size = list.size();
        list.insert(1, "inserted");
        assertEquals(list.size(), size + 1);
        assertEquals("inserted", list.get(1));
    }

    @Test
    void testRemoveElement()
    {
        GenericList list = new GenericList();
        list.add(element);
        int size = list.size();
        assertEquals(list.remove(0), element);
        assertEquals(list.size(), size - 1);
    }

    @Test
    void testToStringWithEmptyList()
    {
        GenericList list = new GenericList();
        assertEquals("size: 0, elements: []", list.toString());
    }

    @Test
    void testToString()
    {
        GenericList list = new GenericList();
        list.add(element);
        list.add(element);
        assertEquals("size: 2, elements: [test, test]", list.toString());
    }

    @Test
    void testToStringUsingStringBufferWithEmptyList()
    {
        GenericList list = new GenericList();
        assertEquals("size: 0, elements: []", list.toStringUsingStringBuffer());
    }

    @Test
    void testToStringUsingStringBuffer()
    {
        GenericList list = new GenericList();
        list.add(element);
        list.add(element);
        assertEquals("size: 2, elements: [test, test]", list.toStringUsingStringBuffer());
    }

    @Test
    void testEqualsNull()
    {
        GenericList list = new GenericList();
        assertEquals(false, list.equals(null));
    }

    @Test
    void testEqualsDifferentClasses()
    {
        GenericList list = new GenericList();
        Object obj = new Object();
        assertEquals(false, list.equals(obj));
    }

    @Test
    void testEqualsSameReference()
    {
        GenericList list = new GenericList();
        GenericList refList = list;
        assertEquals(true, list.equals(refList));
    }

    @Test
    void testEqualsDifferentInstances()
    {
        GenericList list = new GenericList();
        list.add(element);
        GenericList anotherList = new GenericList();
        anotherList.add(element);
        assertEquals(true, list.equals(anotherList));
    }
}
