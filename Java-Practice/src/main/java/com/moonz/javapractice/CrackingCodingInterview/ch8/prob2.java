package com.moonz.javapractice.CrackingCodingInterview.ch8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class prob2 {
    List<Point> path = new ArrayList<>();

    public List<Point> getPath (boolean[][] maze) {
        if (maze == null || maze.length == 0) {
            return null;
        }
        Set<Point> alreadyVisited = new HashSet<>();
        if (getPath(maze, maze.length-1, maze[0].length-1, path, alreadyVisited)) {
            return path;
        }
        return null;
    }

    private boolean getPath(boolean[][] maze, int row, int col, List<Point> path, Set<Point> alreadyVisited) {
        if (row < 0 || col < 0 || !maze[row][col]) {
            return false;
        }
        Point p = new Point(row, col);
        if (alreadyVisited.contains(p)) {
            return false;
        }
        boolean isAtStart = row==0 && col==0;
        if (isAtStart || getPath(maze, row-1, col, path, alreadyVisited) || getPath(maze, row, col-1, path, alreadyVisited)) {
            path.add(p);
            return true;
        }
        alreadyVisited.add(p);
        return false;
    }

    static class Point {
        int row;
        int col;

        Point(int r, int c) {
            row = r;
            col = c;
        }
    }
}
