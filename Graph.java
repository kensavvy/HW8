/******************************************************************
 *
 *   Kendall Savino / COMP 272 002
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 *  Graph traversal exercise
 *
 *  The Graph class is a representing an oversimplified Directed Graph of vertices
 *  (nodes) and edges. The graph is stored in an adjacency list
 */

public class Graph {
  int numVertices;                  // vertices in graph
  LinkedList<Integer>[] adjListArr; // Adjacency list
  List<Integer> vertexValues;       // vertex values

  // Constructor 
  public Graph(int numV) {
    numVertices = numV;
    adjListArr = new LinkedList[numVertices];
    vertexValues = new ArrayList<>(numVertices);

    for (int i = 0; i < numVertices; i++) {
      adjListArr[i] = new LinkedList<>();
      vertexValues.add(0);
    }
  }

  /*
   * method setValue
   * 
   * Sets a vertex's (node's) value.
   */ 
  
  public void setValue(int vertexIndex, int value) {
    if (vertexIndex >= 0 && vertexIndex < numVertices) {
      vertexValues.set(vertexIndex, value);
    } else {
      throw new IllegalArgumentException(
             "Invalid vertex index: " + vertexIndex);
    }
  }


  public void addEdge(int src, int dest) {
    adjListArr[src].add(dest);
  }

  /*
   * method printGraph
   * 
   * Prints the graph as an adjacency matrix
   */ 
  
  public void printGraph() {
    System.out.println(
         "\nAdjacency Matrix Representation:\n");
    int[][] matrix = new int[numVertices][numVertices];

    for (int i = 0; i < numVertices; i++) {
      for (Integer dest : adjListArr[i]) {
        matrix[i][dest] = 1;
      }
    }

    System.out.print("  ");
    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
    }
    System.out.println();

    for (int i = 0; i < numVertices; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < numVertices; j++) {
        if (matrix[i][j] == 1) {
          System.out.print("| ");
        } else {
          System.out.print(". ");
        }
      }
      System.out.println();
    }
  }


  /**
   * method findRoot
   *
   * This method returns the value of the root vertex, where root is defined in
   * this case as a node that has no incoming edges. If no root vertex is found
   * and/or more than one root vertex, then return -1.
   * 
   */
  
  public int findRoot() {

    // ADD YOUR CODE HERE - DO NOT FORGET TO ADD YOUR NAME/SECTION AT TOP OF FILE

    // Initialize empty integer array to count number of incoming edges
    int[] incomingEdges = new int[numVertices];

    // Loop through vertices in graph
    for (int i = 0; i < numVertices; i++) {

      // Count all vertices the current vertex points to
      for (int adjacent : adjListArr[i]) {
        incomingEdges[adjacent]++;
      }

    }

    // Initialize index of possible root, assuming it is not one
    int rootIndex = -1;

    // Initialize tracker to count number of vertices with zero incoming edges
    int vertexCount = 0;

    // Loop through vertices in graph
    for (int i = 0; i < numVertices; i++) {

      // If vertex has zero incoming edges, save the index and incrememnt vertex count
      if (incomingEdges[i] == 0) {
        rootIndex = i;
        vertexCount++;
      }

    }
    
    // Initialize variable to store result
    int rootValue;

    // If only one vertex has zero incoming edges, root is found, get value of root vertex
    if (vertexCount == 1) {
      rootValue = vertexValues.get(rootIndex);

    // If there's no root, or more than one, set result value to -1
    } else {
      rootValue = -1;
    }

    // Return final value
    return rootValue;
  } 

}
