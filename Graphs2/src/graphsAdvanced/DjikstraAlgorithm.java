package graphsAdvanced;

import java.util.Scanner;

public class DjikstraAlgorithm {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int e = s.nextInt();
		int adjMatrix[][] = new int[n][n];
		for (int i=0;i<e;i++) {
			int v1 = s.nextInt();
			int v2 = s.nextInt();
			int wt = s.nextInt();
			adjMatrix[v1][v2] = wt;
			adjMatrix[v2][v1] = wt;
		}
		djikstra(adjMatrix);
		s.close();
	}

	private static void djikstra(int[][] adjMatrix) {
		int n = adjMatrix.length;
		boolean[] visited = new boolean[n];
		int[] distance = new int[n];
		
		// Source vertex is 0. Initial setup
		distance[0] = 0;
		for (int i=1;i<n;i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		for (int i=0;i<n;i++) {
			int minVertex = findMinVertex(visited,distance);
			visited[minVertex] = true;
			
			for (int j=0;j<n;j++) {
				if (adjMatrix[minVertex][j] > 0 && !visited[j]  && adjMatrix[minVertex][j]<Integer.MAX_VALUE) {
					// unvisited neighbors of minVertex
					// calculate distance to reach j from source via minVertex
					int newDistance = distance[minVertex] + adjMatrix[minVertex][j];
					if (newDistance < distance[j]) {
						distance[j] = newDistance;
					}
				}
			}
			
		}
		
		
		// print distance value for all vertices
		System.out.println("The minimum distance from source 0 \nto other vertices are as follows : ");
		for (int i=0;i<n;i++) {
			System.out.println(i + "   " + distance[i]);
		}
		
		
	}

	private static int findMinVertex(boolean[] visited, int[] distance) {
		int minVertex = -1;
		for (int i=0;i<visited.length;i++) {
			if(!visited[i] && (minVertex==-1 || distance[i] < distance[minVertex])) {
				minVertex = i;
			}
		}
		return minVertex;
	}

}
