package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * A mutable mathematical set of integers backed by an ArrayList.
 * No duplicates are stored; mutator operations modify the current instance.
 */
public class IntegerSet  {
  private List<Integer> set = new ArrayList<Integer>();

  /** Clears the internal representation of the set. */
  public void clear() {
    set.clear();
  }

  /** Returns the number of elements in the set. */
  public int length() {
    return set.size();
  }

  /**
   * Returns true if the two sets contain exactly the same values (order-independent).
   * Overrides Object.equals.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof IntegerSet)) return false;
    IntegerSet other = (IntegerSet) o;
    // Order-independent, content equality with no duplicates
    return new HashSet<>(this.set).equals(new HashSet<>(other.set));
  }

  /** Returns true if the set contains the given value. */
  public boolean contains(int value) {
    return set.contains(value);
  }

  /**
   * Returns the largest item in the set.
   * @throws IllegalStateException if the set is empty.
   */
  public int largest()  {
    if (set.isEmpty()) {
      throw new IllegalStateException("IntegerSet is empty");
    }
    return Collections.max(set);
  }

  /**
   * Returns the smallest item in the set.
   * @throws IllegalStateException if the set is empty.
   */
  public int smallest()  {
    if (set.isEmpty()) {
      throw new IllegalStateException("IntegerSet is empty");
    }
    return Collections.min(set);
  }

  /** Adds an item to the set if it is not already present. */
  public void add(int item) {
    if (!set.contains(item)) {
      set.add(item);
    }
  }

  /** Removes an item from the set if present. */
  public void remove(int item) {
    set.remove(Integer.valueOf(item)); // remove by value, not by index
  }

  /** Set union: modifies this to contain all unique elements in this or other. */
  public void union(IntegerSet other) {
    for (Integer v : other.set) {
      if (!this.set.contains(v)) {
        this.set.add(v);
      }
    }
  }

  /** Set intersection: modifies this to contain only elements in both sets. */
  public void intersect(IntegerSet other) {
    this.set.retainAll(other.set);
  }

  /** Set difference (this \ other): removes all elements found in other. */
  public void diff(IntegerSet other) {
    this.set.removeAll(other.set);
  }

  /** Set complement: modifies this to become (other \ this). */
  public void complement(IntegerSet other) {
    List<Integer> result = new ArrayList<>(other.set);
    result.removeAll(this.set);
    this.set.clear();
    this.set.addAll(result);
  }

  /** Returns true if the set is empty. */
  public boolean isEmpty() {
    return set.isEmpty();
  }

  /**
   * Returns a String representation like "[1, 2, 3]".
   * Order is not specified by the spec; we show ascending order for readability.
   */
  @Override
  public String toString() {
    List<Integer> copy = new ArrayList<>(set);
    Collections.sort(copy);
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < copy.size(); i++) {
      if (i > 0) sb.append(", ");
      sb.append(copy.get(i));
    }
    sb.append("]");
    return sb.toString();
  }
}
