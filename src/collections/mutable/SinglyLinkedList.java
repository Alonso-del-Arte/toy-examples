/*
 * Copyright (C) 2021 Alonso del Arte
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package collections.mutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * EXERCISE: A singly-linked list. Don't worry about making it thread-safe.
 * @param <E> The type of the elements of the list, e.g., <code>String</code>.
 * @author Alonso del Arte
 */
public class SinglyLinkedList<E> implements List<E> {

    private Node<E> head = null;

    private Node<E> tail = null;

    private int length = 0;

    // TODO: Have your IDE generate stubs for List<E> requirements
    // TODO: Write a test, perhaps starting with isEmpty(), verify it fails,
    //       make it pass.
    // TODO: Repeat with the other functions until everything works correctly

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public boolean contains(Object obj) {
        if (this.length == 0) {
            return false;
        }
        boolean found;
        Node<E> curr = this.head;
        do {
            found = curr.element.equals(obj);
            curr = curr.next;
        } while (!found && curr.next != null);
        return found;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.length];
        Node<E> node = this.head;
        for (int i = 0; i < this.length; i++) {
            array[i] = node.element;
            node = node.next;
        }
        return array;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        Node<E> node = new Node<>(e);
        if (this.length == 0) {
            this.head = node;
        } else {
            this.tail.link(node);
        }
        this.tail = node;
        this.length++;
        return true;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public boolean remove(Object o) {
        return true;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public boolean containsAll(Collection<?> c) {
        return true;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return true;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return true;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public boolean removeAll(Collection<?> c) {
        return true;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public boolean retainAll(Collection<?> c) {
        return true;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public void clear() {
        //
    }

    @Override
    public E get(int index) {
        Node<E> node = this.findNode(index);
        return node.element;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public E set(int index, E element) {
        return null;
    }

    private Node<E> findNode(int index) {
        Node<E> curr = this.head;
        int traversed = 0;
        while (traversed < index) {
            curr = curr.next;
            traversed++;
        }
        return curr;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > this.length) {
            String excMsg = "Index " + index
                    + " is out of bounds (should be from 0 to " + this.length
                    + ")";
            throw new IndexOutOfBoundsException(excMsg);
        }
        if (index == this.length) {
            this.add(element);
            return;
        }
        Node<E> node = new Node<>(element);
        if (index == 0) {
            Node<E> currHead = this.head;
            node.link(currHead);
            this.head = node;
        } else {
            Node<E> currentlyAtPreviousIndex = this.findNode(index - 1);
            Node<E> currentlyAtIndex = currentlyAtPreviousIndex.next;
            node.link(currentlyAtIndex);
            currentlyAtPreviousIndex.link(node);
        }
        this.length++;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object obj) {
        boolean found = false;
        int index = 0;
        Node<E> curr = this.head;
        while (!found && index < this.length) {
            found = curr.element.equals(obj);
            curr = curr.next;
            index++;
        }
        if (found) {
            return index - 1;
        } else {
            return -1;
        }
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public int lastIndexOf(Object obj) {
        return -3;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    // STUB TO FAIL THE FIRST TEST
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public SinglyLinkedList() {
        //
    }

    private static class Node<E> {

        final E element;

        Node<E> next = null;

        void link(Node<E> node) {
            this.next = node;
        }

        Node(E elem) {
            this.element = elem;
        }

    }

}
