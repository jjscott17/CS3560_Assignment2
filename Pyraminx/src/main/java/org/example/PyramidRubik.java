package org.example;

import java.util.*;

// color values for the pyraminx
enum Color {Red,Green,Blue,Yellow}

class Face {
    private Color[] tiles;

    // array of 9 tiles
    public Face(Color color){
        tiles = new Color[9];
        Arrays.fill(tiles, color);
    }

    public Color[] getTiles(){
        return tiles;
    }

    public void setTiles(int index, Color color) {
        tiles[index] = color;
    }
}
public class PyramidRubik {
    // use hashmap for Faces
    private Map<String, Face> faces;
    // use list for edges
    private List<int[]> edges;  // (face1, index1, face2, index2)

    // constructor
    public PyramidRubik() {
        faces = new HashMap<>();
        edges = new ArrayList<>();
        initializeFaces();
        initializeEdges();
    }


    // initialize faces with colors
    private void initializeFaces(){
        faces.put("front", new Face(Color.Red));
        faces.put("left", new Face(Color.Green));
        faces.put("right", new Face(Color.Blue));
        faces.put("bottom", new Face(Color.Yellow));
    }

    private void initializeEdges() {
        // connect edge indices: {faceA, tileA, faceB, tileB}
        int[][] edgeIndices = {
                {0, 4, 1, 2}, // Edge between Face 0 and Face 1
                {0, 6, 2, 2}, // Edge between Face 0 and Face 2
                {0, 8, 3, 2}, // Edge between Face 0 and Face 3
                {1, 6, 2, 4}, // Edge between Face 1 and Face 2
                {1, 8, 3, 4}, // Edge between Face 1 and Face 3
                {2, 8, 3, 6}  // Edge between Face 2 and Face 3
        };

        // iterate through edgeIndices to store faces and tiles
        for (int[] edge : edgeIndices) {
            int faceA = edge[0];
            int tileA = edge[1];
            int faceB = edge[2];
            int tileB = edge[3];

            // get colors from faces
            Color colorA = faces.get(getFaceName(faceA)).getTiles()[tileA];
            Color colorB = faces.get(getFaceName(faceB)).getTiles()[tileB];

            // ensure tiles share the same colors
            faces.get(getFaceName(faceB)).setTiles(tileB, colorA);
            faces.get(getFaceName(faceA)).setTiles(tileA, colorB);
        }
    }

    // label faces for clarity
    private String getFaceName(int index) {
        String[] faceNames = {"front", "left", "right", "bottom"};
        return faceNames[index];
    }

    //
    public boolean validateCube() {
        // verify whether each face has exactly 9 tiles or not
        for(Face face : faces.values()) {
            Color[] tiles = face.getTiles();
            if(tiles.length != 9) {
                return false;
            }
        }

        // hashmap to store count of each color
        Map<Color, Integer> colorCount = new HashMap<>();
        // initialize each key with value 0
        for(Color color : Color.values()) {
            colorCount.put(color, 0);
        }

        // count each instance of each color
        for(Face face : faces.values()) {
            for(Color tile : face.getTiles()) {
                colorCount.put(tile, colorCount.get(tile) + 1);
            }
        }

        // print the color counter for debugging
        for(Color color : colorCount.keySet()) {
            System.out.println(color+": "+colorCount.get(color));
        }

        // verify whether there are 9 instances of each color or not
        for(int count : colorCount.values()) {
            if(count != 9) {
                return false;
            }
        }

        // if nothing returns false, the cube must be valid
        return true;
    }
    public static void main(String[] args) {
        PyramidRubik pyramid = new PyramidRubik();

        // print each face
        for (String faceName : pyramid.faces.keySet()) {
            System.out.println(faceName + ": " + Arrays.toString(pyramid.faces.get(faceName).getTiles()));
        }

        System.out.println("Valid? "+pyramid.validateCube());
        System.out.println("\n***Note: Pyraminx does not appear to be solved");

    }
}
