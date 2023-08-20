// There are 12 Edges on a cube. Each has two exposed faces.
public class Edge {

        public static final int m_notDisplayed = -2; // the valid display faces are -1, 0 and 1

        public int[] m_home;      // An array of 3 int's, one zero, others are either -1 or +1
        public int[] m_position;  // An array of 3 int's, one zero, others are either -1 or +1
        public int[] m_display;   // An array of 3 int's, each between -1 and +1 inclusive.

        Edge(int[] home, int[] position, int[] display){
            m_home     = home.clone();
            m_position = position.clone();
            m_display  = display.clone();

            if(       m_position[0] == 0)
                             m_display [0] = m_notDisplayed;
            else if ( m_position[1] == 0)
                             m_display [1] = m_notDisplayed;
            else // if (m_position[2] == 0)
                             m_display [2] = m_notDisplayed;

        }

        /////////////////////////////////////////////////////
        public void quarterRotate(FaceEnum rotateFace, boolean clockwise){
            if(needToRotate(rotateFace)){
                clockwise = rotateFace.m_plusOneMinusOne == +1 ?  clockwise : !clockwise;

                int rotateAxis = rotateFace.getAxis();
                int otherAxis1 = (rotateAxis == 0               ? 1 : 0);
                int otherAxis2 = (rotateAxis == 2               ? 1 : 2);

                int axisOfZero  = ( m_position[otherAxis1] == 0 ? otherAxis1 : otherAxis2 );
                int nonZeroAxis = ( m_position[otherAxis1] == 0 ? otherAxis2 : otherAxis1 );

                int mult1       = ( m_position[otherAxis1] == 0 ? -1 : +1);
                int mult2       = (clockwise                    ? -1 : +1);

                m_position[axisOfZero]  = m_position[nonZeroAxis] * mult1 * mult2;
                m_position[nonZeroAxis] = 0;

                m_display[axisOfZero]   = m_display[nonZeroAxis];
                m_display[nonZeroAxis]  = m_notDisplayed;
            }
        }
        /////////////////////////////////////////////////////
        public boolean needToRotate(FaceEnum rotateFace){
            int axis = rotateFace.getAxis();

            return (m_position[axis] == rotateFace.getPlusOneMinusOne());
        }
        /////////////////////////////////////////////////////
        public FaceEnum getDisplayFace(int axis){
            int displayAxis = m_display[axis];
            int home        = m_home[displayAxis + 1];

            return FaceEnum.getDisplayFace(displayAxis + 1, home);
        }
        /////////////////////////////////
        public int[] getPosition(){
            return m_position;
        }
        /////////////////////////////////////////////////////
        public void setColorChars(char[] right, char[] left,char[] up,char[] down,char[] front,char[] back){

            int i0 =  4 + m_position[1]  + m_position[2] * 3;
            int i1 =  4 + m_position[0]  + m_position[2] * 3;
            int i2 =  4 + m_position[0]  + m_position[1] * 3;

            ////////////////////////////////////////////////////////
            if(m_position[0] == 1){
                right[i0] = getDisplayFace(0).colorChar();
            } else if (m_position[0] == -1){
                left[i0]  = getDisplayFace(0).colorChar();
            } // else if (m_position[0] == 0) { do nothing }
            ////////////////////////////////////////////////////////
            if(m_position[1] == 1){
                up[i1] = getDisplayFace(1).colorChar();
            } else if (m_position[1] == -1){
                down[i1]  = getDisplayFace(1).colorChar();
            } // else if (m_position[1] == 0) { do nothing }
            ////////////////////////////////////////////////////////
            if(m_position[2] == 1){
                front[i2] = getDisplayFace(2).colorChar();
            } else if (m_position[2] == -1){
                back[i2]  = getDisplayFace(2).colorChar();
            } // else if (m_position[2] == 0) { do nothing }
        }
        /////////////////////////////////
        public String asString(){
            checkArrays(); // If you want the algorithm to speed up, then you can remove this method call.

            String str = new String("");

            for(int i = 0; i <3; i++){
                str +=    Integer.toString(m_position[i]) + ","
                        + Integer.toString(m_display [i]) + ",";
                          // Deliberately excluding m_home array since it is the same every time.
            }
            return str;
        }
        /////////////////////////////////
        /////////////////////////////////
        public boolean checkArrays(){

            int homeSum = Math.abs(m_home[0]) + Math.abs(m_home[1]) + Math.abs(m_home[2]);
            if( homeSum != 2) {
                System.out.println("Edge.checkArrays(): Was expecting sum of abs of m_home array to be 2, here it is: "
                        + Integer.toString(homeSum));
                return false;
            }

            int posSum = Math.abs(m_position[0]) + Math.abs(m_position[1]) + Math.abs(m_position[2]);
            if (posSum != 2) {
                System.out.println("Edge.checkArrays(): Was expecting sum of abs of position array to be 2, here it is: "
                        + Integer.toString(posSum));
                return false;
            }

            int displayMin = Math.min(Math.min(m_display[0], m_display[1]), m_display[2]);
            if( displayMin != m_notDisplayed) {
                System.out.println("Edge.checkArrays(): Was expecting one m_display element to be m_notDisplayed.");
                return false;
            }

            return true;
        }

        /////////////////////////////////
        /////////////////////////////////
        /////////////////////////////////



}
