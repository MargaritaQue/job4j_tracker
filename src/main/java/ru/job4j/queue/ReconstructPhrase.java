package ru.job4j.queue;

import java.util.Deque;

public class ReconstructPhrase {

    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder w = new StringBuilder();
        int siz = evenElements.size();
        for (int i = 0; i < siz; i++) {
            if (i % 2 == 0) {
                w.append(evenElements.pop());
            } else {
                evenElements.pollFirst();
            }
        }
        return w.toString();
    }

    private String getDescendingElements() {
        StringBuilder s = new StringBuilder();
        int si = descendingElements.size();
        for (int i = 0; i < si; i++) {
            s.append(descendingElements.peekLast());
            descendingElements.pollLast();
        }
        return s.toString();
    }

    public String getReconstructPhrase() {
        return getEvenElements() + getDescendingElements();
    }
}