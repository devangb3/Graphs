package com.example.demo.LearningGraphs;

import java.util.*;

public class GraphPractice {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public int numIslands(char[][] grid) {
        int numberOfIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1' && !visited[i][j]){
                    numIslandsRec(grid, i, j, rows,cols, visited);
                    numberOfIslands++;
                }
            }
        }
        return numberOfIslands;
    }
    private void numIslandsRec(char[][]grid, int i, int j, int row, int col, boolean[][] visited){
        System.out.println(i + " " + j);
        if(i <0 || i >= row || j<0 || j>= col || visited[i][j] || grid[i][j] == '0')
            return;
        visited[i][j] = true;
        numIslandsRec(grid, i+1, j, row, col, visited);
        numIslandsRec(grid, i-1, j, row, col, visited);
        numIslandsRec(grid, i, j+1, row, col, visited);
        numIslandsRec(grid, i, j-1, row, col, visited);
    }
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] == 1 && !visited[i][j]){
                    int area =  maxAreaRec(grid, visited, i, j, rows, cols);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }
    public int maxAreaRec(int[][] grid, boolean[][] visited, int i, int j, int rows, int cols){
        if(i < 0 || j< 0 || i>=  rows || j>= cols || visited[i][j] || grid[i][j] == 0){
            return 0;
        }

        visited[i][j] = true;
        int area = 1;
        area += maxAreaRec(grid, visited, i+1, j, rows, cols);
        area += maxAreaRec(grid, visited, i-1, j, rows, cols);
        area += maxAreaRec(grid, visited, i, j+1, rows, cols);
        area += maxAreaRec(grid, visited, i, j-1, rows, cols);

        return area;
    }
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        boolean isFirst= true;
        Node ans = new Node(node.val);
        HashMap<Integer, Node> visited = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visited.put(node.val, ans);
        while(!queue.isEmpty()){
            Node jk = queue.poll();
            Node aa = visited.get(jk.val);
            for(Node temp : jk.neighbors){
                Node z = visited.containsKey(temp.val) ? visited.get(temp.val) : new Node(temp.val);
                aa.neighbors.add(z);
                if(!visited.containsKey(temp.val)){
                    visited.put(temp.val,z);
                    queue.add(temp);
                }
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        GraphPractice gp = new GraphPractice();
        int[][] grid = new int[][]{new int[]{1,1,1,1,0},
                                            {1,1,0,1,0},
                                            {1,1,0,0,0},
                                            {0,0,0,1,0}
                                   };
        //System.out.println(gp.maxAreaOfIsland(grid));
        Node a = gp.new Node(1);
        Node b = gp.new Node(2);
        Node c = gp.new Node(3);
        Node d = gp.new Node(4);
        a.neighbors.add(b);
        a.neighbors.add(d);

        b.neighbors.add(c);
        b.neighbors.add(a);

        c.neighbors.add(b);
        c.neighbors.add(d);

        d.neighbors.add(a);
        d.neighbors.add(c);

        System.out.println(gp.cloneGraph(a));

    }
}
