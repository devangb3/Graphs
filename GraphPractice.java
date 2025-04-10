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

    public void islandsAndTreasure(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        List<int[]> directions = Arrays.asList(
                new int[]{1,0}, new int[]{-1,0},
                new int[]{0,1}, new int[]{0,-1}
        );
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] == 0) {
                    queue.add(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int distance = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i< size; i++){
                int[] cell = queue.poll();
                int r = cell[0];
                int c = cell[1];
                grid[r][c] = distance;

                for(int[] direction : directions){
                    int row = r + direction[0];
                    int col = c + direction[1];
                    if(row > -1 && row < rows && col > -1 && col < cols && !visited[row][col] && grid[row][col] != -1){
                        visited[row][col] = true;
                        queue.add(new int[]{row,col});
                    }
                }
            }
            distance++;
        }

        for(int[] row : grid) System.out.println(Arrays.toString(row));
    }

    public int orangesRotting(int[][] grid){
        int counter = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int numberOfFresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> directions = List.of(
                new int[]{0,1}, new int[]{0,-1},
                new int[]{1,0}, new int[]{-1,0}
        );
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(grid[i][j] == 2){
                    queue.add(new int[]{i,j});
                }
                else if(grid[i][j] == 1) numberOfFresh++;
            }
        }

        while(!queue.isEmpty()){
            int size = queue.size();
            if(numberOfFresh == 0) return counter;
            for (int i = 0; i < size; i++) {
                int r = queue.peek()[0];
                int c = queue.poll()[1];
                for(int[] direction : directions){
                    int row = r + direction[0];
                    int col = c + direction[1];
                    if(row > -1 && row < rows && col > -1 && col < cols && grid[row][col] != 0 && grid[row][col] != 2){
                        grid[row][col] = 2;
                        numberOfFresh--;
                        queue.add(new int[]{row,col});
                    }
                }
            }
            counter++;
        }
        for (int[] row : grid) System.out.println(Arrays.toString(row));

        if(numberOfFresh != 0) return -1;
        return counter;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }
        HashSet<Integer> visited = new HashSet<>();
        List<Integer> temp;
        for(int[] req : prerequisites){
            temp = map.get(req[0]);
            temp.add(req[1]);
            map.put(req[0], temp);
        }
        for (int i = 0; i < numCourses; i++) {
            if(!canFinishRec(map, visited, i)) return false;
        }
        return true;
    }
    private boolean canFinishRec(HashMap<Integer, List<Integer>> map, HashSet<Integer> visited, Integer course){
        if(visited.contains(course)) return false;
        if(map.get(course).size() == 0) return true;
        visited.add(course);
        for(int num : map.get(course)){
            if(!canFinishRec(map, visited, num)) return false;
        }
        visited.remove(course);
        map.put(course, new ArrayList<>());
        return true;
    }
    public int[] findOrder(int numCourses, int[][] prerequisites){
        HashSet<Integer> order = new LinkedHashSet<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] req : prerequisites){
            List<Integer> temp = map.get(req[0]);
            temp.add(req[1]);
            map.put(req[0], temp);
        }
        HashSet<Integer> visited = new HashSet<>();
        for(int crs=0; crs<numCourses; crs++){
            if(!findOrderRec(map, visited, order, crs)) return new int[]{};
        }
        return order.stream().mapToInt(Integer::intValue).toArray();
    }
    private boolean findOrderRec(HashMap<Integer, List<Integer>> map, HashSet<Integer> visited, HashSet<Integer> order, int crs){
        if(visited.contains(crs)) return false;
        if(map.get(crs).size() == 0) {
            order.add(crs);
            return true;
        }
        visited.add(crs);
        for(int num : map.get(crs))
            if(!findOrderRec(map, visited, order, num)) return false;
        visited.remove(crs);
        map.put(crs, new ArrayList<>());
        order.add(crs);
        return true;
    }
    public boolean validTree(int n, int [][] edges){
        if(n == 0) return true;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            List<Integer> temp = map.get(edge[1]);
            temp.add(edge[0]);
            map.put(edge[1], temp);
            // Add both lists as undirected graph
            temp = map.get(edge[0]);
            temp.add(edge[1]);
            map.put(edge[0], temp);
        }
        HashSet<Integer> visited = new HashSet<>(n);

        return validTreeRec(map, visited, 0, -1) && visited.size() == n;
    }
    private boolean validTreeRec(HashMap<Integer, List<Integer>> map, HashSet<Integer> visited, int edge, int prev){
        if(visited.contains(edge)) return false;
        visited.add(edge);
        for(int num : map.get(edge)){
            if(prev != num)
                if(!validTreeRec(map, visited, num, edge)) return false;
        }
        return true;
    }

    public int countComponents(int n, int[][] edges){
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        int components = 0;
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if(!visited.contains(i)){
                components++;
                countComponentsRec(map, visited, i);
            }

        }
        return components;
    }
    private void countComponentsRec(HashMap<Integer, List<Integer>> map, HashSet<Integer> visited, int element){
        if(visited.contains(element)) return;
        visited.add(element);
        for(int num : map.get(element)){
            if(!visited.contains(num)) countComponentsRec(map, visited, num);
        }
    }
    public int countComponents1(int n, int[][] edges){
        List<Integer> parent = new ArrayList<>();
        List<Integer> score = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            parent.add(i);
            score.add(1);
        }
        int res = n;
        for(int[] edge : edges){
            res -= union(edge[0], edge[1], parent, score);
        }
        return res;
    }
    private int find(List<Integer> parent, int child){
        int root = child;
        while(root != parent.get(root)){
            parent.set(root, parent.get(parent.get(root)));
            root = parent.get(root);
        }

        return root;
    }
    private int union(int n1, int n2, List<Integer> parent, List<Integer> score){
        int p1 = find(parent, n1);
        int p2 = find(parent, n2);
        if(p1 == p2) return 0;
        int sumScore = score.get(p1) + score.get(p2);
        if(score.get(p2) > score.get(p1)){
            parent.set(p1, p2);
            score.set(p2, sumScore);
        }
        else{
            parent.set(p2, p1);
            score.set(p1, sumScore);
        }

        return 1;
    }
    public int findCircleNum(int[][] isConnected){
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = isConnected.length;
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i != j && isConnected[i][j] == 1){
                    map.get(i).add(j);
                }
            }
        }
        int count = 0;
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if(!visited.contains(i)){
                count++;
                findCircleNumRec(map, visited, i);
            }
        }
        return count;
    }
    private void findCircleNumRec(HashMap<Integer, List<Integer>> map, HashSet<Integer> visited, int element){
        if(visited.contains(element)) return;
        visited.add(element);
        for(int num : map.get(element)){
            if(!visited.contains(num)){
                findCircleNumRec(map, visited, num);
            }
        }
    }
    public int findCircleNum1(int[][] isConnected){
        int n = isConnected.length;
        List<Integer> parent = new ArrayList<>();
        List<Integer> score = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            parent.add(i);
            score.add(1);
        }
        int res = isConnected.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i != j && isConnected[i][j] != 0){
                    res -= union1(i,j, parent, score);
                }
            }

        }
        return res;
    }
    private int findParent(int element, List<Integer> parent){
        int root = parent.get(element);
        while(root != parent.get(root)){
            root = parent.get(root);
            parent.set(parent.get(root), parent.get(parent.get(root)));
        }
        return root;
    }
    private int union1(int i, int j, List<Integer> parent, List<Integer> score){
        int p1 = findParent(i, parent);
        int p2 = findParent(j, parent);
        if(p1 == p2) return 0;
        int sumScore = score.get(p1) + score.get(p2);
        if(score.get(p1) > score.get(p2)){
            parent.set(p2, p1);
            score.set(p1, sumScore);
        }
        else{
            parent.set(p1, p2);
            score.set(p2, sumScore);
        }
        return 1;
    }
    int[] ans = new int[2];
    public int[] findRedundantConnection(int[][] edges){
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = edges.length;
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        for(int[] edge : edges){
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        for(int[] edge : edges){
            findRedundantConnectionRec(visited, edge);
        }
        return ans;
    }
    private void findRedundantConnectionRec(HashSet<Integer> visited, int[] edge){
        if(visited.contains(edge[0]) && visited.contains(edge[1])){
            ans = edge;
            return;
        }
        visited.add(edge[0]);
        visited.add(edge[1]);
    }

    public int[] findRedundantConnection1(int[][] edges){

        List<Integer> parent = new ArrayList<>();
        List<Integer> score = new ArrayList<>();
        for (int i = 0; i <= edges.length; i++) {
            parent.add(i);
            score.add(1);
        }
        for(int[] edge : edges){
            union1(parent, score, edge);
        }
        return ans;
    }
    private int find1(List<Integer> parent, int num){
        int root = num;
        while(root != parent.get(root)){
            root = parent.get(root);
            //root = parent.get(root);
        }
        return root;
    }
    private void union1(List<Integer> parent, List<Integer> score, int[] edge){
        int p1 = find1(parent, edge[0]);
        int p2 = find1(parent, edge[1]);
        if(p1 == p2) ans = edge;
        int sumScore = score.get(p1) + score.get(p2);
        if(score.get(p1) > score.get(p2)){
            parent.set(p2, p1);
            score.set(p1, sumScore);
        }
        else{
            parent.set(p1,p2);
            score.set(p2, sumScore);
        }
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(wordList.get(wordList.size()-1).equals(endWord)) return 0;
        int hops  = 0;

        return hops;
    }
//    private int ladderLengthRec(String current, String endWord,List<Integer> score, int index, int hops){
//        if(current.equals(endWord)){
//            return hops;
//        }
//        return Math.min(
//                ladderLengthRec()
//        )
 //   }
    public static void main(String[] args) {
        GraphPractice gp = new GraphPractice();
        System.out.println(Arrays.toString(gp.findRedundantConnection1(new int[][]{
                //{1,2},{1,3},{2,3}
                //{1,2},{2,3},{3,4},{1,4},{1,5}
                {9,10},{5,8},{2,6},{1,5},{3,8},{4,9},{8,10},{4,10},{6,8},{7,9}
        })));

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

        //System.out.println(gp.cloneGraph(a));

    }
}
