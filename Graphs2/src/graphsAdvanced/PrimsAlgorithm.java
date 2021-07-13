package graphsAdvanced;

import java.util.Scanner;

public class PrimsAlgorithm {

	public static void prims(int adjMatrix[][]) {
		int n = adjMatrix.length;
		boolean visited[] = new boolean[n];
		int parent[] = new int[n];
		int weight[] = new int[n];
		
		// source is vertex 0,  initial setup
		parent[0] = -1;
		weight[0] = 0;
		
		for(int i=1;i<n;i++) {
			weight[i] = Integer.MAX_VALUE;
		}
		
		// pick a vertex and mark it visited
		for (int i=0;i<n;i++) {
			int minVertex = findMinVertex(visited,weight);
			visited[minVertex] = true;
			
			// Explore neighbor of minimum vertex
			for (int j=0;j<n;j++) {
				if(adjMatrix[minVertex][j] != 0 && !visited[j]) {
					if(weight[j] > adjMatrix[minVertex][j]) {
						// update values
						weight[j] = adjMatrix[minVertex][j];
						parent[j] = minVertex;
					}
				}
			}
		}
		int minWeight = 0;
		System.out.println("The Spanning tree is as follows: ");
		System.out.println("V1  V2  WT");
		for (int i=1;i<n;i++) {
			minWeight += weight[i];
			if (i<parent[i])
				System.out.println(i + "   " + parent[i] + "   " + weight[i]);
			else
				System.out.println(parent[i] + "   " + i + "   "+ weight[i]);
		}
		System.out.println("The minimum weight is : " + minWeight);
		
	}
	
	
	private static int findMinVertex(boolean[] visited, int[] weight) {
		int minVertex = -1;
		for (int i=0;i<visited.length;i++) {
			if(!visited[i] &&  ((minVertex == -1) ||  weight[i] < weight[minVertex])) {
				minVertex = i;
			}
		}
		return minVertex;
	}


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
		prims(adjMatrix);
		s.close();
	}

}
