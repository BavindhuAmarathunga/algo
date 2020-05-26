package com.company;// Java program for implementation of Ford Fulkerson algorithm
import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.LinkedList;

import static javafx.scene.input.KeyCode.N;


class MaxFlow {

    private static int vertices;
    int Vr = vertices;    //number of vertices in a graph

    boolean bfs(int residual_Graph[][], int s, int t, int parent[]){

        boolean visited[] = new boolean[Vr];
        for(int i=0; i<Vr; ++i)
                visited[i]=false;

        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.add(s);
        visited[s] = true;
        parent[s]=-1;

        //  BFS Loop
        while (queue.size()!=0){

            int y = queue.poll();

            for (int x=0; x<Vr; x++){

                if (visited[x]==false && residual_Graph[y][x] > 0){

                    queue.add(x);
                    parent[x] = y;
                    visited[x] = true;
                }
            }
        }
        return (visited[t] == true);
    }

    int fordFulkerson(int graph[][], int s, int t) {    //return maximum flow from s to t
        int u;
        int v;

        int residual_Graph[][] = new int[Vr][Vr];

        for (u = 0; u < Vr; u++){
            for (v = 0; v < Vr; v++){
                residual_Graph[u][v] = graph[u][v];
            }
        }

        int parent[] = new int[Vr];

        int max_flow_capacity = 0;  // There is no flow in beginning

        while (bfs(residual_Graph, s, t, parent))       //find min residual capacity and max flow using bfs.
        {
            int path_flow_capacity = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow_capacity = Math.min(path_flow_capacity, residual_Graph[u][v]);
            }

            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                residual_Graph[u][v] -= path_flow_capacity;
                residual_Graph[v][u] += path_flow_capacity;
                System.out.println("Node: "+ u + "  to  Node: " + v + " Capacity: " + path_flow_capacity+"\n"); //print flow capacity node to node.

            }
            max_flow_capacity += path_flow_capacity;        //Add path flow to overall flow

        }
        return max_flow_capacity;       //return overall flow.
    }

    public static void main (String[] args) throws java.lang.Exception
    {

        inputs();

    }


    public static void inputs(){

        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        Scanner input = new Scanner(System.in);
        System.out.print("\nplease Enter..." +
                "\n-----------------------------------------"+      //menu
                "\n1 for the 6 nodes," +
                "\n2 for the 12 nodes," +
                "\n3 for the 24 nodes," +
                "\n4 for the 48 nodes," +
                "\n5 for the Exit." +
                "\n-----------------------------------------"+
                "\nNumber : ");

        int i=input.nextInt();

        try {

            switch (i) {
                case 1:
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                    six();
                    break;
                case 2:
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                    twelve();
                    break;
                case 3:
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                    twenty_four();
                    break;
                case 4:
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                    forty_eight();
                    break;
                case 5:
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                    System.out.println("Thank you....!");
                    break;
                default:
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                    System.out.println("Invalid input ");
                    inputs();
            }
        }catch (Exception e){
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            System.out.println("Invalid input...!");
            inputs();
        }

    }

    public static void six(){       // 6 nodes code
        vertices = 6;
        int graph_six[][] =new int[][] {        //2D array metrics of according to nodes
                {0,12,0,7,0,0},
                {0,0,9,0,8,0},
                {0,0,0,0,0,15},
                {0,6,0,0,6,0},
                {0,0,10,0,0,8},
                {0,0,0,0,0,0}
        };

        double startTime;
        startTime = System.nanoTime();
        MaxFlow m = new MaxFlow();

        System.out.println("\nThe maximum possible flow is " +
                m.fordFulkerson(graph_six, 0, 5));          //flow capacity about from node to node.
        double endTime;
        endTime = System.nanoTime();
        double duration;
        duration = ((endTime - startTime)/1000000);
        System.out.println("Total Execution Time :\t"+duration+" ms");      //time to Total Execution.
        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        //print 2D array values
        System.out.print("--> ");
        for (int k=0; k<6; k++){

            System.out.print(" X"+k+"\t\t");
        }

        for (int i=0;i<6;i++){
            System.out.print("\nY"+i);

            for (int j=0; j<6; j++){
                System.out.print("\t| " + graph_six[i][j] + " |");
            }
        }



        try {


            System.out.println("\n\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            Scanner input_Q = new Scanner(System.in);                                           //menu
            System.out.print("\nDo you want to \nDelete     (please enter [1]) or \nEdit    (please enter [2]) or \nMain menu   (please enter [3])\nEnter here : ");
            int input_q = input_Q.nextInt();
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            switch (input_q) {
                case 1:
                    Scanner row_number = new Scanner(System.in);
                    System.out.print("Enter row number Y : ");          //get y dimension value
                    int row_d = row_number.nextInt();
                    Scanner col_number = new Scanner(System.in);
                    System.out.print("Enter column number X : ");       //get x dimension value
                    int col_d = col_number.nextInt();


                    double startTime1;
                    startTime1 = System.nanoTime();
                    MaxFlow m1 = new MaxFlow();
                    graph_six[row_d][col_d] = 0;        //set values to 2D array
                    System.out.println("\nThe maximum possible flow is " +
                            m1.fordFulkerson(graph_six, 0, 5));
                    double endTime1;
                    endTime1 = System.nanoTime();
                    double duration1;
                    duration1 = ((endTime1 - startTime1)/1000000);
                    System.out.println("Total Execution Time :\t"+duration1+" ms");
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    System.out.print("--> ");
                    for (int k=0; k<6; k++){

                        System.out.print(" X"+k+"\t\t");
                    }

                    for (int i=0;i<6;i++){
                        System.out.print("\nY"+i);

                        for (int j=0; j<6; j++){
                            System.out.print("\t| " + graph_six[i][j] + " |");
                        }
                    }
                    inputs();

                    break;
                case 2:
                    Scanner row_edit = new Scanner(System.in);
                    System.out.print("Enter row number Y : ");
                    int row_e = row_edit.nextInt();
                    Scanner col_edit = new Scanner(System.in);
                    System.out.print("Enter column number X : ");
                    int col_e = col_edit.nextInt();
                    Scanner power = new Scanner(System.in);
                    System.out.print("Enter power : ");
                    int Power = power.nextInt();


                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    double startTime2;
                    startTime2 = System.nanoTime();
                    graph_six[row_e][col_e] = Power;
                    MaxFlow m2 = new MaxFlow();

                    System.out.println("\nThe maximum possible flow is " +
                            m2.fordFulkerson(graph_six, 0, 5));

                    double endTime2;
                    endTime2 = System.nanoTime();
                    double duration2;
                    duration2 = ((endTime2 - startTime2)/1000000);
                    System.out.println("Total Execution Time :\t"+duration2+" ms");

                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");



                    System.out.print("--> ");
                    for (int k=0; k<6; k++){

                        System.out.print(" X"+k+"\t\t");
                    }

                    for (int i=0;i<6;i++){
                        System.out.print("\nY"+i);

                        for (int j=0; j<6; j++){
                            System.out.print("\t| " + graph_six[i][j] + " |");
                        }
                    }
                    inputs();
                    break;
                case 3:
                    inputs();
                    break;

                default:
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                    System.out.println("value error...!\n please re-Enter --->");
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                    six();
            }

        }catch (Exception e){
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            System.out.println("value error...!\n please re-Enter --->");
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            six();
        }

    }

    public static void twelve(){
        vertices = 12;
        int graph_twelve[][] =new int[][] {
                {0,2,9,3,1,12,8,0,6,2,8,10},
                {0,0,4,11,11,1,8,6,12,7,6,7},
                {4,7,0,1,12,8,4,5,6,9,9,10},
                {1,4,9,0,9,1,8,0,6,6,7,11},
                {1,12,9,7,0,2,0,11,4,10,2,6},
                {2,3,10,9,5,0,11,2,2,0,8,9},
                {0,11,9,1,3,11,0,6,12,7,11,8},
                {11,6,11,12,12,12,1,0,4,9,9,4},
                {7,11,2,5,4,12,1,4,0,7,8,10},
                {1,12,0,6,10,12,7,7,8,0,1,6},
                {5,9,1,3,1,9,0,9,9,10,0,11},
                {0,0,0,0,0,0,0,0,0,0,0,0}
        };

        double startTime;
        startTime = System.nanoTime();
        MaxFlow m = new MaxFlow();
        System.out.println("The maximum possible flow is " +
                m.fordFulkerson(graph_twelve, 0, 11));
        double endTime;
        endTime = System.nanoTime();
        double duration;
        duration = ((endTime - startTime)/1000000);
        System.out.println("Total Execution Time:\t"+duration+" s");
        System.out.println("\n");

        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        System.out.print("--> ");
        for (int k=0; k<12; k++){

            System.out.print(" X"+k+"   \t");
        }

        for (int i=0;i<12;i++){
            System.out.print("\nY"+i);

            for (int j=0; j<12; j++){
                System.out.print("\t| " + graph_twelve[i][j] + " |");
            }
        }



        try {


            System.out.println("\n\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            Scanner input_Q = new Scanner(System.in);
            System.out.print("\nDo you want to \nDelete     (please enter [1]) or \nEdit    (please enter [2]) or \nMain menu   (please enter [3])\nEnter here : ");
            int input_q = input_Q.nextInt();
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            switch (input_q) {
                case 1:
                    Scanner row_number = new Scanner(System.in);
                    System.out.print("Enter row number Y : ");
                    int row_d = row_number.nextInt();
                    Scanner col_number = new Scanner(System.in);
                    System.out.print("Enter column number X : ");
                    int col_d = col_number.nextInt();
                    graph_twelve[row_d][col_d] = 0;

                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    double startTime1;
                    startTime1 = System.nanoTime();
                    MaxFlow m1 = new MaxFlow();

                    System.out.println("\nThe maximum possible flow is " +
                            m1.fordFulkerson(graph_twelve, 0, 11));
                    double endTime1;
                    endTime1 = System.nanoTime();
                    double duration1;
                    duration1 = ((endTime1 - startTime1)/1000000);
                    System.out.println("Total Execution Time :\t"+duration1+" ms");
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    System.out.print("--> ");
                    for (int k=0; k<12; k++){

                        System.out.print(" X"+k+"   \t");
                    }

                    for (int i=0;i<12;i++){
                        System.out.print("\nY"+i);

                        for (int j=0; j<12; j++){
                            System.out.print("\t| " + graph_twelve[i][j] + " |");
                        }
                    }
                    inputs();

                    break;
                case 2:
                    Scanner row_edit = new Scanner(System.in);
                    System.out.print("Enter row number Y : ");
                    int row_e = row_edit.nextInt();
                    Scanner col_edit = new Scanner(System.in);
                    System.out.print("Enter column number X : ");
                    int col_e = col_edit.nextInt();
                    Scanner power = new Scanner(System.in);
                    System.out.print("Enter power : ");
                    int Power = power.nextInt();
                    graph_twelve[row_e][col_e] = Power;

                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    double startTime2;
                    startTime2 = System.nanoTime();
                    MaxFlow m2 = new MaxFlow();

                    System.out.println("\nThe maximum possible flow is " +
                            m2.fordFulkerson(graph_twelve, 0, 11));
                    double endTime2;
                    endTime2 = System.nanoTime();
                    double duration2;
                    duration2 = ((endTime2 - startTime2)/1000000);
                    System.out.println("Total Execution Time :\t"+duration2+" ms");
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    System.out.print("--> ");
                    for (int k=0; k<12; k++){

                        System.out.print(" X"+k+"   \t");
                    }

                    for (int i=0;i<12;i++){
                        System.out.print("\nY"+i);

                        for (int j=0; j<12; j++){
                            System.out.print("\t| " + graph_twelve[i][j] + " |");
                        }
                    }
                    inputs();
                    break;
                case 3:
                    inputs();
                    break;

                default:
                    System.out.println("value error...!");
                    twelve();
            }

        }catch (Exception e){
            System.out.println("value error...!");
            twelve();
        }

    }

    public static void twenty_four(){

        vertices = 24;

        int graph_twenty_four[][] =new int[][] {
                {0,9,10,11,3,0,7,7,1,11,3,6,9,7,0,3,9,2,6,10,4,3,4,3},
                {10,0,8,5,0,5,7,1,0,11,9,10,9,8,10,1,6,5,4,2,11,11,8,1},
                {10,11,0,8,12,6,4,12,5,7,2,0,3,7,3,5,1,10,0,10,4,3,4,7},
                {11,4,10,0,8,11,0,0,2,0,2,11,6,8,11,0,11,0,9,10,8,7,2,11},
                {0,11,1,3,0,0,6,11,6,4,0,1,6,1,4,12,5,9,6,5,7,1,1,11},
                {5,2,9,5,9,0,9,6,7,7,8,7,5,2,4,12,2,11,1,6,7,9,1,6},
                {0,0,11,2,5,0,0,2,1,12,5,1,6,0,6,10,2,6,5,12,11,9,3,9},
                {1,2,4,8,4,9,3,0,8,11,8,0,12,6,0,2,12,7,12,1,11,12,9,1},
                {2,0,12,5,8,1,2,10,0,1,7,9,6,5,5,3,0,8,10,3,2,1,12,3},
                {11,12,5,11,6,6,12,8,7,0,5,1,11,3,8,3,12,5,3,6,7,1,2,9},
                {12,2,4,6,9,6,4,3,7,6,0,8,4,12,1,11,5,12,5,2,4,1,12,1},
                {12,6,3,6,7,4,4,5,9,12,2,0,12,7,0,1,6,0,0,7,3,8,1,9},
                {4,12,2,5,4,2,4,5,1,8,4,2,0,4,10,3,6,6,4,11,0,8,7,10},
                {4,3,7,5,6,2,2,6,4,6,5,3,2,0,11,6,1,11,10,5,4,2,12,1},
                {6,12,4,4,1,4,10,11,10,2,2,5,12,0,0,3,2,8,5,2,3,6,1,9},
                {1,10,4,10,4,7,0,7,10,0,7,5,11,11,4,0,0,5,11,4,8,10,2,8},
                {3,5,1,6,3,7,3,8,1,6,4,9,5,4,2,0,0,7,4,12,7,9,11,6},
                {3,0,12,6,10,11,4,12,1,10,8,10,12,5,1,7,10,0,12,5,6,7,12,11},
                {5,11,1,4,10,4,10,4,12,2,7,11,11,11,2,5,12,0,0,12,3,5,6,1},
                {4,11,12,10,7,4,3,4,3,8,4,12,6,2,3,7,6,3,9,0,0,0,8,10},
                {8,5,12,2,12,5,0,6,2,10,1,10,12,8,10,0,12,7,10,0,0,0,0,0},
                {6,11,7,2,3,8,2,2,0,0,12,2,5,5,4,11,12,6,2,10,7,0,3,1},
                {2,1,0,12,6,8,9,3,2,9,9,11,6,1,4,10,0,4,11,1,9,2,0,6},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        double startTime;
        startTime = System.nanoTime();

        MaxFlow m = new MaxFlow();
        System.out.println("The maximum possible flow is " +
                m.fordFulkerson(graph_twenty_four, 0, 23));

        double endTime;
        endTime = System.nanoTime();
        double duration;
        duration = ((endTime - startTime)/1000000);
        System.out.println("Total Execution Time:\t"+duration+" ms");
        System.out.println("\n");

        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        System.out.print("--> ");
        for (int k=0; k<24; k++){

            System.out.print("X"+k+"    \t");
        }

        for (int i=0;i<24;i++){
            System.out.print("\nY"+i);

            for (int j=0; j<24; j++){
                System.out.print("\t| " + graph_twenty_four[i][j] + " |");
            }
        }



        try {


            System.out.println("\n\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            Scanner input_Q = new Scanner(System.in);
            System.out.print("\nDo you want to \nDelete     (please enter [1]) or \nEdit    (please enter [2]) or \nMain menu   (please enter [3])\nEnter here : ");
            int input_q = input_Q.nextInt();
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            switch (input_q) {
                case 1:
                    Scanner row_number = new Scanner(System.in);
                    System.out.print("Enter row number Y : ");
                    int row_d = row_number.nextInt();
                    Scanner col_number = new Scanner(System.in);
                    System.out.print("Enter column number X : ");
                    int col_d = col_number.nextInt();
                    graph_twenty_four[row_d][col_d] = 0;

                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    double startTime1;
                    startTime1 = System.nanoTime();
                    MaxFlow m1 = new MaxFlow();

                    System.out.println("\nThe maximum possible flow is " +
                            m1.fordFulkerson(graph_twenty_four, 0, 23));
                    double endTime1;
                    endTime1 = System.nanoTime();
                    double duration1;
                    duration1 = ((endTime1 - startTime1)/1000000);
                    System.out.println("Total Execution Time :\t"+duration1+" ms");
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    System.out.print("--> ");
                    for (int k=0; k<24; k++){

                        System.out.print(" X"+k+"   \t");
                    }

                    for (int i=0;i<24;i++){
                        System.out.print("\nY"+i);

                        for (int j=0; j<24; j++){
                            System.out.print("\t| " + graph_twenty_four[i][j] + " |");
                        }
                    }
                    inputs();

                    break;
                case 2:
                    Scanner row_edit = new Scanner(System.in);
                    System.out.print("Enter row number Y : ");
                    int row_e = row_edit.nextInt();
                    Scanner col_edit = new Scanner(System.in);
                    System.out.print("Enter column number X : ");
                    int col_e = col_edit.nextInt();
                    Scanner power = new Scanner(System.in);
                    System.out.print("Enter power : ");
                    int Power = power.nextInt();
                    graph_twenty_four[row_e][col_e] = Power;

                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");


                    double startTime2;
                    startTime2 = System.nanoTime();
                    MaxFlow m2 = new MaxFlow();

                    System.out.println("\nThe maximum possible flow is " +
                            m2.fordFulkerson(graph_twenty_four, 0, 23));
                    double endTime2;
                    endTime2 = System.nanoTime();
                    double duration2;
                    duration2 = ((endTime2 - startTime2)/1000000);
                    System.out.println("Total Execution Time :\t"+duration2+" ms");
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    System.out.print("--> ");
                    for (int k=0; k<24; k++){

                        System.out.print(" X"+k+"   \t");
                    }

                    for (int i=0;i<24;i++){
                        System.out.print("\nY"+i);

                        for (int j=0; j<24; j++){
                            System.out.print("\t| " + graph_twenty_four[i][j] + " |");
                        }
                    }
                    inputs();
                    break;
                case 3:
                    inputs();
                    break;

                default:
                    System.out.println("value error...!");
                    twenty_four();
            }

        }catch (Exception e){
            System.out.println("value error...!");
            twenty_four();
        }

    }


    public static void forty_eight(){

        vertices = 48;

        int graph_forty_eight[][] =new int[][] {
                {0,8,12,5,1,4,8,9,0,11,12,4,3,5,7,6,8,5,2,0,9,10,10,5,9,6,7,5,3,7,3,12,8,6,5,7,7,6,12,4,5,2,12,12,10,8,12,9},
                {5,0,9,10,7,9,11,7,7,4,4,11,0,10,8,7,6,5,11,5,0,5,11,4,12,5,0,6,5,5,7,4,11,8,11,0,8,3,3,12,2,10,11,0,3,1,0,1},
                {7,2,0,11,6,7,7,12,12,7,1,12,11,0,9,7,1,2,9,7,4,6,4,3,10,4,8,6,0,3,12,2,1,8,3,10,3,0,3,10,1,3,6,11,0,4,2,5},
                {10,2,5,0,7,6,10,4,0,12,11,7,9,4,7,7,3,4,12,8,10,2,10,5,5,1,8,0,4,7,9,5,6,0,1,4,11,12,4,1,4,0,12,4,5,6,12,5},
                {1,7,6,4,0,12,3,2,0,5,1,7,2,7,5,12,11,1,3,7,1,7,7,1,12,2,5,4,6,0,8,2,6,5,5,12,3,0,11,11,3,1,8,10,11,9,12,7},
                {4,6,9,5,8,0,11,8,8,8,10,12,7,5,5,0,8,3,9,1,2,0,3,9,5,9,3,7,12,6,11,7,7,2,10,1,8,0,2,1,12,0,5,7,4,8,5,5},
                {1,12,3,9,6,0,0,9,5,2,6,0,5,8,10,5,9,0,4,3,12,8,12,6,3,2,7,9,12,1,1,6,6,5,6,5,3,10,0,9,8,4,4,10,12,3,3,9},
                {6,1,1,10,11,5,4,0,3,6,7,12,10,0,8,11,9,9,1,8,5,1,12,4,10,10,11,8,0,1,7,11,4,10,3,9,1,3,10,4,8,7,1,6,3,7,8,12},
                {4,1,8,9,7,7,6,5,0,8,10,10,0,5,7,12,4,8,0,1,8,4,0,12,6,9,9,4,2,7,10,8,5,6,11,11,2,3,2,3,4,11,1,6,3,9,12,4},
                {9,1,12,3,10,0,7,11,0,0,0,6,5,4,10,0,3,10,2,5,12,3,12,3,4,8,5,1,4,3,8,5,2,0,10,12,6,12,6,1,12,7,3,7,7,8,0,4},
                {1,12,2,2,1,9,5,4,8,3,0,11,7,7,1,1,8,3,6,2,11,1,8,9,5,4,0,0,7,4,6,4,8,4,9,10,9,5,3,11,5,10,8,1,5,1,0,6},
                {7,6,12,10,5,6,9,11,3,3,5,0,1,12,9,7,0,7,7,10,11,7,6,10,0,7,4,8,3,1,2,8,2,0,5,2,10,0,9,11,0,12,1,1,5,1,4,1},
                {12,7,2,10,2,3,12,1,1,7,9,7,0,12,11,9,9,7,0,5,2,2,9,5,3,12,12,6,5,9,2,9,2,8,4,1,12,1,5,11,3,9,1,5,9,1,0,6},
                {7,10,11,5,4,7,0,0,9,7,12,2,4,0,6,10,1,10,7,0,8,11,11,2,7,6,6,8,2,9,10,2,4,12,3,1,9,0,11,8,5,7,7,2,10,3,7,1},
                {12,4,10,9,4,2,7,2,9,5,2,12,0,3,0,10,12,12,8,4,6,4,12,6,6,1,10,10,11,7,9,12,9,7,0,10,12,5,9,2,1,3,6,6,1,3,10,1},
                {8,3,10,2,7,0,5,5,12,5,4,6,0,4,9,0,8,12,3,3,8,12,7,5,4,9,5,9,7,9,9,4,12,6,8,12,8,1,2,7,4,8,8,8,10,4,2,11},
                {2,1,5,4,0,12,2,5,4,11,8,4,4,12,10,3,0,2,4,11,2,10,7,8,2,5,10,0,7,1,7,2,11,5,1,1,11,7,7,7,12,8,0,0,3,6,8,7},
                {0,8,7,5,3,5,12,11,2,11,3,0,7,1,5,3,8,0,7,0,8,4,4,5,11,8,5,11,0,10,2,1,5,9,7,12,6,4,5,7,0,8,8,7,7,12,5,7},
                {3,7,4,6,10,4,0,3,4,7,7,9,4,10,12,4,4,12,0,0,5,0,5,0,3,5,4,9,7,12,8,10,10,5,5,9,4,10,2,8,6,1,0,0,3,4,10,11},
                {8,3,4,11,12,6,0,12,11,1,0,5,4,8,7,7,0,3,5,0,10,3,5,2,5,4,4,5,10,5,7,4,2,0,9,8,7,12,8,3,8,7,7,12,4,9,10,10},
                {5,9,7,5,9,12,2,1,8,0,6,11,3,6,2,12,10,12,5,5,0,7,0,12,6,1,10,12,11,11,10,2,5,0,1,2,7,12,7,5,0,0,11,11,3,6,5,5},
                {6,0,8,12,6,2,12,5,2,8,10,1,2,11,0,4,5,7,6,11,6,0,6,4,4,3,12,7,8,5,0,8,9,7,9,7,4,3,9,3,11,11,5,2,7,0,0,11},
                {1,8,9,10,6,10,11,6,10,3,12,8,10,8,8,8,11,4,10,5,12,10,0,9,0,1,3,12,3,5,6,7,2,11,2,9,2,12,9,11,7,0,3,6,8,12,7,12},
                {12,5,1,10,12,8,3,3,12,1,4,7,9,1,9,8,7,4,11,6,6,12,11,0,9,9,3,7,8,6,9,0,6,6,2,7,2,0,5,10,1,9,3,6,6,7,9,12},
                {11,10,4,6,5,5,9,0,3,7,9,0,3,4,0,6,2,4,4,6,9,6,6,9,0,4,10,1,8,1,3,4,5,7,11,4,7,5,4,6,2,5,7,2,12,2,9,1},
                {12,3,12,7,5,12,11,9,11,4,1,5,11,3,7,0,3,4,3,9,2,2,5,3,5,0,1,12,8,12,10,3,2,5,5,4,1,1,10,10,2,12,11,2,1,1,6,5},
                {7,5,5,8,12,1,10,3,9,0,10,10,10,9,4,10,11,10,0,3,12,8,10,6,0,11,0,2,1,7,9,4,1,9,9,12,4,0,10,8,8,9,1,3,3,10,12,9},
                {4,11,1,1,2,2,12,1,9,10,11,0,10,12,7,2,9,9,9,11,12,1,3,9,10,7,1,0,7,4,2,12,6,1,3,5,4,3,2,7,3,8,11,2,4,5,10,11},
                {3,8,3,1,7,9,2,1,12,4,0,5,2,7,4,0,0,6,0,12,7,4,4,1,5,0,12,12,0,11,7,0,10,7,8,12,4,0,8,0,10,7,7,7,12,6,8,8},
                {4,3,5,9,1,8,5,12,4,2,2,12,7,4,0,4,0,12,4,2,8,11,7,7,10,3,7,8,1,0,5,4,12,6,1,9,12,6,4,12,10,6,2,4,2,5,8,7},
                {10,1,6,2,9,2,0,2,2,11,8,10,10,7,1,12,1,10,10,1,2,12,2,7,8,4,2,11,8,5,0,8,7,8,4,9,5,11,5,11,5,2,11,9,6,5,0,8},
                {10,1,1,7,5,3,2,7,0,8,10,0,12,4,7,10,2,5,6,2,10,2,1,10,4,9,12,11,0,11,0,4,4,3,0,4,1,11,8,6,9,7,5,11,2,11,4,2},
                {6,8,3,1,6,8,5,6,8,1,10,3,7,5,2,3,2,6,9,10,12,0,12,3,10,5,7,8,11,6,11,11,0,12,9,2,7,6,3,3,11,5,1,11,11,4,5,12},
                {9,10,4,11,10,1,2,8,0,6,10,7,0,0,0,4,8,0,9,2,7,10,12,10,11,12,7,8,3,11,7,0,3,0,2,4,9,7,9,11,8,2,9,8,1,12,8,5},
                {7,10,9,1,11,9,10,7,12,0,10,2,10,3,2,6,0,1,3,2,8,3,9,1,12,6,6,8,4,6,12,9,4,3,0,2,12,0,3,11,12,1,2,2,1,6,5,12},
                {2,6,12,7,4,0,0,3,1,2,5,4,1,5,6,6,12,2,2,10,3,2,6,12,4,7,4,9,7,8,2,12,7,3,1,0,8,10,9,2,9,11,4,1,6,3,3,0},
                {3,4,7,5,0,11,2,11,4,7,8,0,7,5,9,5,4,10,9,4,5,2,3,4,12,5,11,5,0,3,6,6,11,8,3,0,0,7,10,12,3,8,0,2,3,12,3,9},
                {6,4,2,4,9,6,6,4,7,2,12,4,10,5,1,7,8,11,12,4,8,2,8,0,11,0,8,1,8,9,12,6,5,0,3,1,11,0,8,11,1,4,3,5,1,3,0,10},
                {2,7,8,3,5,9,1,8,9,0,4,0,11,5,2,0,11,3,10,11,3,9,10,9,1,3,1,11,4,0,1,8,5,10,10,5,9,11,0,2,6,4,5,11,6,11,12,5},
                {6,10,11,11,11,8,7,6,1,5,2,11,1,10,10,3,7,1,5,1,4,2,6,7,5,0,6,6,8,7,3,1,10,4,6,11,0,12,10,0,10,3,7,4,1,10,4,3},
                {5,10,5,8,10,10,5,0,6,0,0,7,8,0,0,1,10,2,4,0,10,1,8,9,8,5,3,6,5,0,11,1,7,7,3,6,10,7,6,3,0,3,6,6,1,9,12,10},
                {10,5,8,4,3,1,10,10,5,10,4,5,12,3,10,3,3,2,10,0,3,2,9,8,10,0,0,4,6,6,4,7,12,5,3,0,3,6,10,2,3,0,11,12,8,2,8,10},
                {4,5,3,8,10,9,11,9,1,4,9,12,0,6,4,4,4,7,7,2,10,12,5,8,7,5,12,9,0,9,9,6,4,6,1,5,12,11,5,6,11,2,0,10,5,3,10,0},
                {2,12,1,2,0,5,4,5,9,5,7,8,4,6,5,8,11,0,6,8,1,6,7,6,8,0,1,0,1,5,10,11,5,4,1,9,6,3,9,4,2,11,7,0,2,8,5,0},
                {3,9,6,10,0,8,2,6,3,4,12,12,3,4,12,9,11,12,8,12,4,1,2,6,6,0,7,0,10,1,12,12,7,2,3,4,1,6,9,6,10,6,5,10,0,6,1,8},
                {4,12,10,4,9,5,7,3,2,1,4,7,5,5,2,10,7,1,0,5,8,4,9,3,9,2,9,4,3,7,10,1,10,8,0,4,1,5,9,7,11,12,9,4,11,0,1,5},
                {11,12,5,6,6,10,1,9,0,0,11,3,10,5,11,10,10,11,5,8,1,11,0,8,7,0,3,11,1,5,6,11,1,1,12,12,11,10,5,8,9,11,7,4,3,1,0,6},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        double startTime;
        startTime = System.nanoTime();

        MaxFlow m = new MaxFlow();
        System.out.println("The maximum possible flow is " +
                m.fordFulkerson(graph_forty_eight, 0, 47));

        double endTime;
        endTime = System.nanoTime();
        double duration;
        duration = ((endTime - startTime)/1000000);
        System.out.println("Total Execution Time:\t"+duration+" ms");
        System.out.println("\n");

        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");


        System.out.print("--> ");
        for (int k=0; k<48; k++){

            System.out.print(" X"+k+"   \t");
        }

        for (int i=0;i<48;i++){
            System.out.print("\nY"+i);

            for (int j=0; j<48; j++){
                System.out.print("\t| " + graph_forty_eight[i][j] + " |");
            }
        }



        try {


            System.out.println("\n\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            Scanner input_Q = new Scanner(System.in);
            System.out.print("\nDo you want to \nDelete     (please enter [1]) or \nEdit    (please enter [2]) or \nMain menu   (please enter [3])\nEnter here : ");
            int input_q = input_Q.nextInt();
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
            switch (input_q) {
                case 1:
                    Scanner row_number = new Scanner(System.in);
                    System.out.print("Enter row number Y : ");
                    int row_d = row_number.nextInt();
                    Scanner col_number = new Scanner(System.in);
                    System.out.print("Enter column number X : ");
                    int col_d = col_number.nextInt();
                    graph_forty_eight[row_d][col_d] = 0;

                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    double startTime1;
                    startTime1 = System.nanoTime();
                    MaxFlow m1 = new MaxFlow();

                    System.out.println("\nThe maximum possible flow is " +
                            m1.fordFulkerson(graph_forty_eight, 0, 47));
                    double endTime1;
                    endTime1 = System.nanoTime();
                    double duration1;
                    duration1 = ((endTime1 - startTime1)/1000000);
                    System.out.println("Total Execution Time :\t"+duration1+" ms");
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    System.out.print("--> ");
                    for (int k=0; k<48; k++){

                        System.out.print(" X"+k+"   \t");
                    }

                    for (int i=0;i<48;i++){
                        System.out.print("\nY"+i);

                        for (int j=0; j<48; j++){
                            System.out.print("\t| " + graph_forty_eight[i][j] + " |");
                        }
                    }
                    inputs();

                    break;
                case 2:
                    Scanner row_edit = new Scanner(System.in);
                    System.out.print("Enter row number Y : ");
                    int row_e = row_edit.nextInt();
                    Scanner col_edit = new Scanner(System.in);
                    System.out.print("Enter column number X : ");
                    int col_e = col_edit.nextInt();
                    Scanner power = new Scanner(System.in);
                    System.out.print("Enter power : ");
                    int Power = power.nextInt();
                    graph_forty_eight[row_e][col_e] = Power;

                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");


                    double startTime2;
                    startTime2 = System.nanoTime();
                    MaxFlow m2 = new MaxFlow();

                    System.out.println("\nThe maximum possible flow is " +
                            m2.fordFulkerson(graph_forty_eight, 0, 47));
                    double endTime2;
                    endTime2 = System.nanoTime();
                    double duration2;
                    duration2 = ((endTime2 - startTime2)/1000000);
                    System.out.println("Total Execution Time :\t"+duration2+" ms");
                    System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                    System.out.print("--> ");
                    for (int k=0; k<48; k++){

                        System.out.print(" X"+k+"   \t");
                    }

                    for (int i=0;i<48;i++){
                        System.out.print("\nY"+i);

                        for (int j=0; j<48; j++){
                            System.out.print("\t| " + graph_forty_eight[i][j] + " |");
                        }
                    }
                    inputs();
                    break;
                case 3:
                    inputs();
                    break;

                default:
                    System.out.println("value error...!");
                    forty_eight();
            }

        }catch (Exception e){
            System.out.println("value error...!");
            forty_eight();
        }
    }
}