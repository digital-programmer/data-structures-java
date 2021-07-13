package graphsAdvanced;

import java.util.Arrays;
import java.util.Scanner;

class Edge implements Comparable<Edge>{
	int v1;
	int v2;
	int weight;
	
	public Edge(int v1,int v2, int wt) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = wt;
	}

	@Override
	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}

}


public class KruskalAlgorithm {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int e = sc.nextInt();
		
		Edge[] edges = new Edge[e];
		
		// Take input into edge list
		for (int i=0;i<e;i++) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			int wt = sc.nextInt();
			
			Edge ed = new Edge(v1,v2,wt);
			edges[i] = ed;	
		}
		
		Edge[] mst = kruskalAlgorithm(edges,n);
		int minWeight = 0;
		System.out.println("The edges in MST are as follows: ");
		System.out.println("V1 V2  WT");
		for (int i=0;i<mst.length;i++) {
			minWeight += mst[i].weight;
			if(mst[i].v1 < mst[i].v2) 
				System.out.println(mst[i].v1 + "   " + mst[i].v2 + "   "+ mst[i].weight);
			else
				System.out.println(mst[i].v2 + "   " + mst[i].v1 + "   "+ mst[i].weight);
		}
		
		System.out.println("The minimum weight is : " + minWeight);
		sc.close();
		
	}

	public static Edge[] kruskalAlgorithm(Edge[] edges, int n) {
		Arrays.sort(edges);
		Edge[] mst = new Edge[n-1];
		
		int parent[] = new int[n];
		for (int j=0;j<n;j++) {
			parent[j] = j;
		}
		
		int count = 0;
		int i = 0;
		while (count!=n-1) {
			Edge currEdge = edges[i++];
			int v1Parent = findParent(currEdge.v1,parent);
			int v2Parent = findParent(currEdge.v2,parent);
			if(v1Parent!=v2Parent) {
				// include the edge into MST
				mst[count] = currEdge;
				count++;
				parent[v1Parent] = v2Parent;
			}
			
		}
		return mst;
	}

	private static int findParent(int v, int[] parent) {
		if(v == parent[v]) {
			return v;
		}
		
		return findParent(parent[v],parent);
	}

}
