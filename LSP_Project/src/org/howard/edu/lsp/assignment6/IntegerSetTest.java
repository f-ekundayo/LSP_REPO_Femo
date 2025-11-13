package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * JUnit 5 tests covering all public methods of IntegerSet, including edge cases.
 */
public class IntegerSetTest {

  @Test
  public void testClearAndIsEmptyAndLength() {
    IntegerSet s = new IntegerSet();
    assertTrue(s.isEmpty());
    assertEquals(0, s.length());

    s.add(1);
    s.add(2);
    assertFalse(s.isEmpty());
    assertEquals(2, s.length());

    s.clear();
    assertTrue(s.isEmpty());
    assertEquals(0, s.length());
  }

  @Test
  public void testAddPreventsDuplicatesAndContains() {
    IntegerSet s = new IntegerSet();
    s.add(5);
    s.add(5); // duplicate ignored
    s.add(-1);
    assertTrue(s.contains(5));
    assertTrue(s.contains(-1));
    assertFalse(s.contains(9));
    assertEquals(2, s.length());
  }

  @Test
  public void testRemoveNoOpIfAbsent() {
    IntegerSet s = new IntegerSet();
    s.add(10);
    s.add(20);
    s.remove(30); // not present, no error
    assertEquals(2, s.length());
    s.remove(10);
    assertFalse(s.contains(10));
    assertEquals(1, s.length());
  }

  @Test
  public void testLargestAndSmallestBasic() {
    IntegerSet s = new IntegerSet();
    s.add(3);
    s.add(1);
    s.add(7);
    s.add(7); // duplicate ignored
    assertEquals(7, s.largest());
    assertEquals(1, s.smallest());
  }

  @Test
  public void testLargestAndSmallestThrowOnEmpty() {
    IntegerSet s = new IntegerSet();
    assertThrows(IllegalStateException.class, s::largest);
    assertThrows(IllegalStateException.class, s::smallest);
  }

  @Test
  public void testEqualsIgnoresOrderAndDuplicates() {
    IntegerSet a = new IntegerSet();
    IntegerSet b = new IntegerSet();

    a.add(1); a.add(2); a.add(3);
    b.add(3); b.add(2); b.add(1); b.add(1); // duplicate

    assertTrue(a.equals(b));
    assertTrue(b.equals(a));

    b.remove(1);
    assertFalse(a.equals(b));
  }

  @Test
  public void testUnionModifiesThisOnly() {
    IntegerSet set1 = new IntegerSet();
    set1.add(1); set1.add(2);

    IntegerSet set2 = new IntegerSet();
    set2.add(2); set2.add(3);

    set1.union(set2);
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertEquals(3, set1.length());

    // set2 unchanged
    assertEquals(2, set2.length());
    assertTrue(set2.contains(2));
    assertTrue(set2.contains(3));
  }

  @Test
  public void testIntersectKeepsOnlyCommonElements() {
    IntegerSet a = new IntegerSet();
    a.add(1); a.add(2); a.add(3);

    IntegerSet b = new IntegerSet();
    b.add(2); b.add(4);

    a.intersect(b);
    assertEquals(1, a.length());
    assertTrue(a.contains(2));
  }

  @Test
  public void testDiffRemovesElementsFoundInOther() {
    IntegerSet a = new IntegerSet();
    a.add(1); a.add(2); a.add(3);

    IntegerSet b = new IntegerSet();
    b.add(2); b.add(5);

    a.diff(b); // remove 2
    assertEquals(2, a.length());
    assertTrue(a.contains(1));
    assertTrue(a.contains(3));
    assertFalse(a.contains(2));
  }

  @Test
  public void testComplementBecomesOtherMinusThis() {
    IntegerSet a = new IntegerSet();
    a.add(1); a.add(3);

    IntegerSet b = new IntegerSet();
    b.add(1); b.add(2); b.add(4);

    a.complement(b); // should become {2,4}
    assertEquals(2, a.length());
    assertTrue(a.contains(2));
    assertTrue(a.contains(4));
    assertFalse(a.contains(1));
    assertFalse(a.contains(3));
  }

  @Test
  public void testOperationsWithSelfAreSafe() {
    IntegerSet s = new IntegerSet();
    s.add(1); s.add(2);

    // union with self: no duplicates, unchanged
    s.union(s);
    assertEquals(2, s.length());

    // intersect with self: unchanged
    s.intersect(s);
    assertEquals(2, s.length());

    // diff with self: becomes empty
    s.diff(s);
    assertTrue(s.isEmpty());
  }

  @Test
  public void testToStringFormat() {
    IntegerSet s = new IntegerSet();
    assertEquals("[]", s.toString());
    s.add(3); s.add(1); s.add(2);
    String repr = s.toString();
    assertTrue(repr.startsWith("[") && repr.endsWith("]"));
    // In ascending order due to toString's sort
    assertEquals("[1, 2, 3]", repr);
  }
}
