//Adam Schoelz

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AlgorithmsAssignment4B {
	public static int minTime(String[] map){
		int min = 0;
		int[][] m = new int[map.length][map[0].length()];
		ArrayList<Pair> bikes = new ArrayList<Pair>();
		ArrayList<Pair> spots = new ArrayList<Pair>();
		
		//Convert map to int array
		for(int i = 0; i < map.length;i++){
			for(int j = 0; j < map[i].length(); j++){
				m[i][j] = convertChar(map[i].charAt(j));
				if(m[i][j] == 1){
					bikes.add(new Pair(i, j));
				}
				if(m[i][j] == 2){
					spots.add(new Pair(i, j));
				}
			}
		}
		
		if(bikes.size() < spots.size()){
			return -1;
		}
		//Find shortest path from bike to space
		//Remove bike, space
		//Repeat
		
		while(!bikes.isEmpty()){
			Pair minBike = new Pair(0,0), minSpot = new Pair(0,0);
			int minDist = Integer.MAX_VALUE;
			for(int i = 0; i < bikes.size(); i++){
				for(int j = 0; j < spots.size(); j++){
					int dist = traverse(bikes.get(i).getLeft(), bikes.get(i).getRight(), spots.get(j).getLeft(), spots.get(j).getRight(), m);
					if(dist < minDist){
						minBike = bikes.get(i);
						minSpot = spots.get(j);
						minDist = dist;
					}
				}
			}
			
			if(minDist == Integer.MAX_VALUE){
				return -1;
			}
			else{
				bikes.remove(bikes.indexOf(minBike));
				spots.remove(spots.indexOf(minSpot));
				min += minDist;
			}
		}
		
		
		return min;
	}
	
	public static int convertChar(char c){
		int toReturn;
		switch(c){
		case '.':
			toReturn = 0;
			break;
		case 'B':
			toReturn = 1;
			break;
		case 'S':
			toReturn = 2;
			break;
		case 'F':
			toReturn = 3; 
			break;
		case 'U':
			toReturn = 4;
			break;
		default:
			toReturn = -1;
			break;
		}
		
		return toReturn;
	}

	public static int traverse(int startx, int starty, int x, int y, int[][] graph){
		int[][] dist = new int[graph.length][graph[0].length];
		boolean[][] visited = new boolean[graph.length][graph[0].length];
		PriorityQueue<Dist> q = new PriorityQueue<Dist>();
		
		for(int i = 0; i < graph.length; i++){
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			Arrays.fill(visited[i], false);
		}
		
		//Start at start
		dist[startx][starty] = 0;
		visited[startx][starty] = true;
		
		for(int i = 0; i < dist.length; i++){
			for(int j = 0; j < dist[i].length; j++){
				q.add(new Dist(i, j, dist[i][j]));
			}
		}
		
		while(!q.isEmpty()){
			Dist u = q.remove();
			//For each neighbor of u check if exists, can be passed through, and distance is less
			//Left
			if(u.getX() - 1 >= 0 && graph[u.getX() - 1][u.getY()] < 3){
				int alt = dist[u.getX()][u.getY()] + 1;
				if(alt < dist[u.getX() - 1][u.getY()]  && alt >= 0){
					dist[u.getX() - 1][u.getY()] = alt;
					q.add(new Dist(u.getX() - 1, u.getY(), alt));
				}
			}
			//Right
			if(u.getX() + 1 < graph.length && graph[u.getX() + 1][u.getY()] < 3){
				int alt = dist[u.getX()][u.getY()] + 1;
				if(alt < dist[u.getX() + 1][u.getY()]  && alt >= 0){
					dist[u.getX() + 1][u.getY()] = alt;
					q.add(new Dist(u.getX() + 1, u.getY(), alt));
				}
			}
			//Down
			if(u.getY() - 1 >= 0 && graph[u.getX()][u.getY() - 1] < 3){
				int alt = dist[u.getX()][u.getY()] + 1;
				if(alt < dist[u.getX()][u.getY() - 1] && alt >= 0){
					dist[u.getX()][u.getY() - 1] = alt;
					q.add(new Dist(u.getX(), u.getY() - 1, alt));
				}
			}
			//Up
			if(u.getY() + 1 < graph[0].length && graph[u.getX()][u.getY() + 1] < 3){
				int alt = dist[u.getX()][u.getY()] + 1;
				if(alt < dist[u.getX()][u.getY() + 1]  && alt >= 0){
					dist[u.getX()][u.getY() + 1] = alt;
					q.add(new Dist(u.getX(), u.getY() + 1, alt));
				}
			}
		}
		return dist[x][y];
	}
	
}

class Dist implements Comparable<Dist>{
	private int x, y, dist;
	
	Dist(int x, int y, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public int getDist(){return dist;}
	public void setDist(int dist){this.dist = dist;}

	@Override
	public int compareTo(Dist arg0) {
		if(arg0.getDist() < this.getDist()) return 1;
		else if (this.getDist() < arg0.getDist()) return -1;
		else return 0;
	}
}

class Pair{
	private int left, right;
	
	Pair(int l, int r){
		setLeft(l);
		setRight(r);
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}
	
}