package org.algorithm_base;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Lesson03_LinkedTest {

    private Lesson03_Linked lesson03Linked;

    @Before
    public void setUp() {
        lesson03Linked = new Lesson03_Linked();
    }

    @Test
    public void deleteThisNode_EmptyList_ReturnsNull() {
        Node head = null;
        Node p = null;
        Node result = lesson03Linked.deleteThisNode(head, p);
        assertNull(result);
    }

    @Test
    public void deleteThisNode_SingleNodeList_ReturnsNull() {
        Node head = new Node(1, null);
        Node p = head;
        Node result = lesson03Linked.deleteThisNode(head, p);
        assertNull(result);
    }

    @Test
    public void deleteThisNode_DeleteHeadNode_ReturnsNewHead() {
        Node head = new Node(1, new Node(2, null));
        Node p = head;
        Node result = lesson03Linked.deleteThisNode(head, p);
        assertNotNull(result);
        assertEquals(2, result.data);
        assertNull(result.next);
    }

    @Test
    public void deleteThisNode_DeleteMiddleNode_RemovesNode() {
        Node head = new Node(1, new Node(2, new Node(3, null)));
        Node p = head.next;
        Node result = lesson03Linked.deleteThisNode(head, p);
        assertNotNull(result);
        assertEquals(1, result.data);
        assertEquals(3, result.next.data);
        assertNull(result.next.next);
    }

    @Test
    public void deleteThisNode_DeleteTailNode_RemovesNode() {
        Node head = new Node(1, new Node(2, null));
        Node p = head.next;
        Node result = lesson03Linked.deleteThisNode(head, p);
        assertNotNull(result);
        assertEquals(1, result.data);
        assertNull(result.next);
    }

    @Test
    public void deleteThisNode_NodeNotFound_ReturnsSameList() {
        Node head = new Node(1, new Node(2, null));
        Node p = new Node(3, null); // 不存在的节点
        Node result = lesson03Linked.deleteThisNode(head, p);
        assertNotNull(result);
        assertEquals(1, result.data);
        assertEquals(2, result.next.data);
        assertNull(result.next.next);
    }

    @Test
    public void deleteThisNode_PIsNull_ReturnsSameList() {
        Node head = new Node(1, new Node(2, null));
        Node p = null;
        Node result = lesson03Linked.deleteThisNode(head, p);
        assertNotNull(result);
        assertEquals(1, result.data);
        assertEquals(2, result.next.data);
        assertNull(result.next.next);
    }
}
