public enum FaceEnum {
                                                          // Center of face
        Right("orange", +1),  //  1, 0, 0
        Left( "red",    -1),  // -1, 0, 0
        Up(   "white",  +1),  //  0, 1, 0
        Down( "yellow", -1),  //  0,-1, 0
        Front("blue",   +1),  //  0, 0, 1
        Back( "green",  -1);  //  0, 0,-1

        /////////////////////////////////////////////////////////
        String m_homeColor;
        int    m_plusOneMinusOne; // white and yellow are on the same axis, but white is top
                                  // so it will have a plus one, yellow is bottom, hence minus one.
        /////////////////////////////////////////////////////////
        private FaceEnum(String homeColor, int plusOneMinusOne) {
            m_homeColor       = homeColor;
            m_plusOneMinusOne = plusOneMinusOne;
        }
        /////////////////////////////////////////////////////
        public String getColor(){
            return m_homeColor;
        }
        /////////////////////////////////////////////////////////
        public int getAxis(){
            if ((this == Right)    ||(this == Left))
                return 0;
            else if ((this == Up)  || (this == Down))
                return 1;
            else
                return 2;
        }
        //////////////////////////////////////////////////////////
        public int getPlusOneMinusOne(){
            return m_plusOneMinusOne;
        }
        //////////////////////////////////////////////////////////
        public static FaceEnum getDisplayFace(int axis,        // will be 0, 1, or 2
                                              int home ){      // will be +1 or -1

                   if (axis == 0) { return(home == 1 ? FaceEnum.Right : FaceEnum.Left);
            } else if (axis == 1) { return(home == 1 ? FaceEnum.Up    : FaceEnum.Down);
            } else if (axis == 2) { return(home == 1 ? FaceEnum.Front : FaceEnum.Back);
            } else {
                System.out.println("Was expecting axis to be 0, 1 or 2 but it was: "
                                   + Integer.toString(axis));
                return FaceEnum.Right;
            }
        }
        ///////////////////////////////////////////////////////////
        public char colorChar(){
            return m_homeColor.charAt(0);
        }

};

