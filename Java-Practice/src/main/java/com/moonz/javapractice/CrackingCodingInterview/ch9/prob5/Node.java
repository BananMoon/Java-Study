package com.moonz.javapractice.CrackingCodingInterview.ch9.prob5;

public class Node {
    public String[] results;
    public String query;
    public Node next;
    public Node previous;

    public Node(String query, String[] results) {
        this.query = query;
        this.results = results;
    }
}
