// There are 8 corners on a cube
public class Corner {

      public int[] m_home;      // An array of 3 int's, each is either -1 or +1
      public int[] m_position;  // An array of 3 int's, each is either -1 or +1. Current Position
      public int[] m_display;   // An array of 3 int's, each between -1 and 1 inclusive. Specifies the orientation.

      Corner(int[] home, int[] position, int[] display){
             m_home     = home.clone();
             m_position = position.clone();
             m_display  = display.clone();
      }
      /////////////////////////////////////////////////////
      public void quarterRotate(FaceEnum rotateFace, boolean clockwise){
          if(needToRotate(rotateFace)){

              clockwise = rotateFace.m_plusOneMinusOne == +1 ?  clockwise : !clockwise;

              int rotateAxis = rotateFace.getAxis();
              int otherAxis1 = (rotateAxis == 0 ? 1 : 0);
              int otherAxis2 = (rotateAxis == 2 ? 1 : 2);

              int pos1 = m_position[otherAxis1];
              int pos2 = m_position[otherAxis2];

              int indexToChange;

              if (pos1 == pos2)
                  indexToChange = (clockwise ? otherAxis2 : otherAxis1);
              else
                  indexToChange = (clockwise ? otherAxis1 : otherAxis2);

              m_position[indexToChange] = - m_position[indexToChange];

              int d1 = m_display[otherAxis1];
              m_display[otherAxis1] = m_display[otherAxis2];
              m_display[otherAxis2] = d1;
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
            int home        = m_home[displayAxis+1];

            return FaceEnum.getDisplayFace(displayAxis+1, home);
      }
      /////////////////////////////////
      public int[] getPosition(){
          return m_position;
      }
      /////////////////////////////////
      public void setColorChars(char[] right, char[] left,char[] up,char[] down,char[] front,char[] back){

          int i0 =  (m_position[1] + 1)  + (m_position[2] + 1) * 3;
          int i1 =  (m_position[0] + 1)  + (m_position[2] + 1) * 3;
          int i2 =  (m_position[0] + 1)  + (m_position[1] + 1) * 3;
          ////////////////////////////////////////////////////////
          if(m_position[0] == 1){
              right[i0] = getDisplayFace(0).colorChar();
          } else {
              left[i0]  = getDisplayFace(0).colorChar();
          }
          ////////////////////////////////////////////////////////
          if(m_position[1] == 1){
              up[i1]   = getDisplayFace(1).colorChar();
          } else {
              down[i1] = getDisplayFace(1).colorChar();
          }
          ////////////////////////////////////////////////////////
          if(m_position[2] == 1){
              front[i2] = getDisplayFace(2).colorChar();
          } else {
              back[i2]  = getDisplayFace(2).colorChar();
          }
      }
      /////////////////////////////////
      public String asString(){

              checkSum();  // If you want the algorithm to speed up, then you can remove this method call.
              String str = new String("");

              for(int i = 0; i <3; i++){
                  str +=   Integer.toString(m_position[i]) + ","
                         + Integer.toString(m_display[i]) + ",";
                           // Deliberately excluding m_home array since it is the same every time.
              }
              return str;
      }
      /////////////////////////////////
      public boolean checkSum(){

          int homeSum = Math.abs(m_home[0]) + Math.abs(m_home[1]) + Math.abs(m_home[2]);
          if( homeSum != 3) {
              System.out.println("Corner.checkSum(): Was expecting sum of abs of m_home array to sum to 3, here it is: "
                      + Integer.toString(homeSum));
              return false;
          }

          int posSum = Math.abs(m_position[0]) + Math.abs(m_position[1]) + Math.abs(m_position[2]);
          if (posSum != 3) {
              System.out.println("Corner.checkSum(): Was sum of abs of position to be 3, here it is: "
                      + Integer.toString(posSum));
              return false;
          }

          int displaySum = m_display[0] + m_display[1] +m_display[2];
          if( displaySum != 0) {
              System.out.println("Corner.checkSum(): Was expecting m_display array to sum to 0, here it is: "
                      + Integer.toString(displaySum));
              return false;
          }

          return true;
      }
      ////////////////////////////////////////////////////
}
