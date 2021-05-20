//Written By Damien King
//Implements Warshall's Algorithm using a Directed Graph

public class DirectedGraph {


    int [][] list = new int[50][50];
    String [] cities = new String[50];

    public DirectedGraph() {
        // Add code
    }

    void addRelation(String from, String to) {
        // Add code
        boolean fc = false;
        boolean tc = false;

        int fLoc = 0;
        int tLoc = 0;
        int end = 0;

        for(int i = 0; i < cities.length; i++){
            if(cities[i] == null) {
                end = i;
                break;
            }
            else if(from.equals(cities[i])) {
                fc = true;
                fLoc = i;
            }
            else if(to.equals(cities[i])) {
                tc = true;
                tLoc = i;
            }
        }
        //if from is in the list....
        if(fc){
            //if to is also in the list...
            if(tc){
                list[fLoc][tLoc] = 1;
                list[tLoc][fLoc] = 1;
            }
            //if to is not in list add to cities and mark connection
            else{
                cities[end] = to;
                list[fLoc][end] = 1;
                list[end][fLoc] = 1;
            }
        }

        //if from is not in the list...
        else{
            cities[end] = from;
            if(tc){
                list[end][tLoc] = 1;
                list[tLoc][end] = 1;
            }
            //if to is not in list add to cities and mark connection
            else{
                cities[end+1] = to;
                list[end][end+1] = 1;
                list[end+1][end] = 1;
            }
        }
    }

    void printAdjMatrix() {
        // Add code

        for(int i = 0; i < cities.length; i++){
            if(cities[i] == null)
                break;
            System.out.println("Relations from " +cities[i]);
            for(int j = 0; j < cities.length; j++){
                if(list[i][j] == 1)
                System.out.println(cities[j]);
            }
            System.out.println();
        }
    }

    void calcWarshall() {
        // add code
        //tracks the rows or the first []
        for (int y = 0; y < cities.length; y++) {
            //tracks the columns or the second []
            for (int x = 0;  x < cities.length; x++) {

                if(list[y][x] == 1) {
                    for (int z = 0; z < cities.length; z++) {
                        if(list[z][y] == 1){
                            list[x][z] = 1;
                        }
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
        DirectedGraph g = new DirectedGraph();

        g.addRelation("Atlanta",  "Chattanooga");
        g.addRelation("Chattanooga",  "Nashville");
        g.addRelation("Chattanooga",  "Knoxville");
        g.addRelation("Atlanta",  "Birmingham");
        g.addRelation("Atlanta",  "Columbia");
        g.addRelation("Columbia", "Charleston");
        g.addRelation("Columbia",  "Greenville");
        g.addRelation("Greenville",  "Atlanta");
        g.addRelation("Charleston",  "Savanna");
        g.addRelation("Savanna",  "Atlanta");
        g.addRelation("Savanna", "Jacksonville");
        g.addRelation("Jacksonville", "Atlanta");
        g.addRelation("Knoxville", "Greenville");




        g.printAdjMatrix();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        g.calcWarshall();
        g.printAdjMatrix();
    }

}
