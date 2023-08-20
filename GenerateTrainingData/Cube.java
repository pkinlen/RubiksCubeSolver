import java.util.Random;

public class Cube {

    Corner[] m_corners; //  8 corners
    Edge[]   m_edges;    // 12 edges
    /////////////////////////////////////////////////////
    // The default cube is a fully solved cube
    public Cube(){
        // The default starting cube is a solved cube.
        m_corners = new Corner[8];

        int c = 0;
        //                                  home,                   position,              display
        m_corners[c] = new Corner(new int[]{-1, -1, -1},  new int[]{-1, -1, -1}, new int[]{-1, 0, 1}); c++;
        m_corners[c] = new Corner(new int[]{-1, -1,  1},  new int[]{-1, -1,  1}, new int[]{-1, 0, 1}); c++;
        m_corners[c] = new Corner(new int[]{-1,  1, -1},  new int[]{-1,  1, -1}, new int[]{-1, 0, 1}); c++;
        m_corners[c] = new Corner(new int[]{-1,  1,  1},  new int[]{-1,  1,  1}, new int[]{-1, 0, 1}); c++;
        m_corners[c] = new Corner(new int[]{ 1, -1, -1},  new int[]{ 1, -1, -1}, new int[]{-1, 0, 1}); c++;
        m_corners[c] = new Corner(new int[]{ 1, -1,  1},  new int[]{ 1, -1,  1}, new int[]{-1, 0, 1}); c++;
        m_corners[c] = new Corner(new int[]{ 1,  1, -1},  new int[]{ 1,  1, -1}, new int[]{-1, 0, 1}); c++;
        m_corners[c] = new Corner(new int[]{ 1,  1,  1},  new int[]{ 1,  1,  1}, new int[]{-1, 0, 1});

        m_edges = new Edge[12];
        int e = 0;
        //                              home,                  position,             display
        m_edges[e] = new Edge(new int[]{-1, -1, 0},  new int[]{-1, -1, 0}, new int[]{-1, 0, 1}); e++;
        m_edges[e] = new Edge(new int[]{-1,  1, 0},  new int[]{-1,  1, 0}, new int[]{-1, 0, 1}); e++;
        m_edges[e] = new Edge(new int[]{ 1, -1, 0},  new int[]{ 1, -1, 0}, new int[]{-1, 0, 1}); e++;
        m_edges[e] = new Edge(new int[]{ 1,  1, 0},  new int[]{ 1,  1, 0}, new int[]{-1, 0, 1}); e++;

        m_edges[e] = new Edge(new int[]{-1, 0, -1},  new int[]{-1, 0, -1}, new int[]{-1, 0, 1}); e++;
        m_edges[e] = new Edge(new int[]{-1, 0,  1},  new int[]{-1, 0,  1}, new int[]{-1, 0, 1}); e++;
        m_edges[e] = new Edge(new int[]{ 1, 0, -1},  new int[]{ 1, 0, -1}, new int[]{-1, 0, 1}); e++;
        m_edges[e] = new Edge(new int[]{ 1, 0,  1},  new int[]{ 1, 0,  1}, new int[]{-1, 0, 1}); e++;

        m_edges[e] = new Edge(new int[]{0, -1, -1},  new int[]{0, -1, -1}, new int[]{-1, 0, 1}); e++;
        m_edges[e] = new Edge(new int[]{0, -1,  1},  new int[]{0, -1,  1}, new int[]{-1, 0, 1}); e++;
        m_edges[e] = new Edge(new int[]{0,  1, -1},  new int[]{0,  1, -1}, new int[]{-1, 0, 1}); e++;
        m_edges[e] = new Edge(new int[]{0,  1,  1},  new int[]{0,  1,  1}, new int[]{-1, 0, 1});
    }
    /////////////////////////////////////////////////////
    public Cube(Corner[] corners, Edge[] edges){
        m_corners = corners;
        m_edges   = edges;
    }
    /////////////////////////////////////////////////////
    // We deal with quarter rotations:
    public void rotate(FaceEnum[] rotateFaces, boolean[] clockwise){
        for (int r = 0; r < rotateFaces.length; r++){
            rotate(rotateFaces[r], clockwise[r]);
        }
    }
    /////////////////////////////////////////////////////
    // Does a quarter rotation.
    public void rotate(FaceEnum rotateFace, boolean clockwise){
        // A rotation will cause a change in 4 corners and the other 4 will be unaltered.
        for (int c = 0; c < m_corners.length; c++){
            m_corners[c].quarterRotate(rotateFace, clockwise);
        }

        for (int e = 0; e < m_edges.length; e++){
            m_edges[e].quarterRotate(rotateFace, clockwise);
        }
    }
    /////////////////////////////////////////////////////
    public void printCube(String msg, boolean humanReadable) {

        if (msg != null)
            System.out.println(msg);

        String str;
        if (humanReadable)
            str = getHamanReadableStringRepresetation();
        else
            str = getMachineReadableStringRepresentation();

        System.out.println(str);
    }
    ///////////////////////////////////////////////////
    // The output of this method will be a string that looks something like the following:
    // w w w
    // w w w
    // w w w
    //
    // b b b   o o o   g g g   r r r
    // b b b   o o o   g g g   r r r
    // b b b   o o o   g g g   r r r
    //
    // y y y
    // y y y
    // y y y
    public String getHamanReadableStringRepresetation(){

        char[] right   = new char[9];   right[4] = FaceEnum.Right.colorChar();  // Element 4 is the centre of each face
        char[] left    = new char[9];   left [4] = FaceEnum.Left. colorChar();
        char[] up      = new char[9];   up   [4] = FaceEnum.Up.   colorChar();
        char[] down    = new char[9];   down [4] = FaceEnum.Down. colorChar();
        char[] front   = new char[9];   front[4] = FaceEnum.Front.colorChar();
        char[] back    = new char[9];   back [4] = FaceEnum.Back .colorChar();


        for (int c = 0; c < m_corners.length; c++){
                m_corners[c].setColorChars(right,left,up,down,front,back);
        }

        for (int e = 0; e < m_edges.length; e++){
            m_edges[e].setColorChars(right,left,up,down,front,back);
        }

        String str = new String("");
        str += Character.toString(up[0]) + " " + Character.toString(up[1]) + " " + Character.toString(up[2]) + "\n";
        str += Character.toString(up[3]) + " " + Character.toString(up[4]) + " " + Character.toString(up[5]) + "\n";
        str += Character.toString(up[6]) + " " + Character.toString(up[7]) + " " + Character.toString(up[8]) + "\n";

        String f1 = Character.toString(front[6]) + " " + Character.toString(front[7]) + " " + Character.toString(front[8]);
        String f2 = Character.toString(front[3]) + " " + Character.toString(front[4]) + " " + Character.toString(front[5]);
        String f3 = Character.toString(front[0]) + " " + Character.toString(front[1]) + " " + Character.toString(front[2]);

        String b1 = Character.toString(back [8]) + " " + Character.toString(back [7]) + " " + Character.toString(back [6]);
        String b2 = Character.toString(back [5]) + " " + Character.toString(back [4]) + " " + Character.toString(back [3]);
        String b3 = Character.toString(back [2]) + " " + Character.toString(back [1]) + " " + Character.toString(back [0]);

        String r1 = Character.toString(right[8]) + " " + Character.toString(right[5]) + " " + Character.toString(right[2]);
        String r2 = Character.toString(right[7]) + " " + Character.toString(right[4]) + " " + Character.toString(right[1]);
        String r3 = Character.toString(right[6]) + " " + Character.toString(right[3]) + " " + Character.toString(right[0]);

        String l1 = Character.toString(left [2]) + " " + Character.toString(left [5]) + " " + Character.toString(left [8]);
        String l2 = Character.toString(left [1]) + " " + Character.toString(left [4]) + " " + Character.toString(left [7]);
        String l3 = Character.toString(left [0]) + " " + Character.toString(left [3]) + " " + Character.toString(left [6]);

        str += "\n" + f1 + "   " + r1 + "   " + b1 + "   " + l1 + "\n";
        str +=        f2 + "   " + r2 + "   " + b2 + "   " + l2 + "\n";
        str +=        f3 + "   " + r3 + "   " + b3 + "   " + l3 + "\n\n";

        str +=  Character.toString(down[6]) + " " + Character.toString(down[7]) + " " + Character.toString(down[8]) + "\n";
        str +=  Character.toString(down[3]) + " " + Character.toString(down[4]) + " " + Character.toString(down[5]) + "\n";
        str +=  Character.toString(down[0]) + " " + Character.toString(down[1]) + " " + Character.toString(down[2]) + "\n";

        return str;
    }
    ///////////////////////////////////////////////////
    public String getMachineReadableStringRepresentation(){
        String str = new String("");

        for (int c = 0; c < m_corners.length; c++){
            str += m_corners[c].asString();
        }

        for (int e = 0; e < m_edges.length; e++){
            str += m_edges[e].asString();
        }

        // We remove the final comma and then add a newline char.
        return str.substring(0, str.length() - 1) + "\n";
    }
    ////////////////////////////////////////////////
    public void applyRandomRotations(int numRotations, long seed){
        // We use the timer as the random seed
        Random rnd = new Random(seed);
        FaceEnum[] faces = FaceEnum.values();

        for (int r = 0; r < numRotations; r++) {
            rotate(faces[rnd.nextInt(faces.length)], rnd.nextBoolean());
        }
    }
    /////////////////////////////////////////////////////////

}
