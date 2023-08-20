public class TestCode {

    ///////////////////////////////////////////////////////////
    public static void applyRotations14(Cube cube){
        FaceEnum[] faces     = new FaceEnum[14];
        boolean[]  clockwise = new boolean[14];
        int r = 0;

        faces[r] = FaceEnum.Front;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Back;   clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Front;  clockwise[r] = false; r++;
        faces[r] = FaceEnum.Up;     clockwise[r] = true;  r++;

        faces[r] = FaceEnum.Left;   clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Back;   clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Front;  clockwise[r] = false; r++;
        faces[r] = FaceEnum.Up;     clockwise[r] = true;  r++;

        faces[r] = FaceEnum.Front;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Right;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Front;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Up;     clockwise[r] = true;  r++;

        faces[r] = FaceEnum.Front;  clockwise[r] = false; r++;
        faces[r] = FaceEnum.Back;   clockwise[r] = true;  r++;

        cube.rotate(faces, clockwise);
    }
    ///////////////////////////////////////////////////////////
    // After the following 24 rotations the original cube should be recovered
    public static void twentyFourRotationReturnToStart(Cube cube){
        //
        FaceEnum[] faces     = new FaceEnum[24];
        boolean[]  clockwise = new boolean[24];
        int r = 0;

        faces[r] = FaceEnum.Right;  clockwise[r] = false; r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = false; r++;
        faces[r] = FaceEnum.Right;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = true;  r++;

        faces[r] = FaceEnum.Right;  clockwise[r] = false; r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = false; r++;
        faces[r] = FaceEnum.Right;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = true;  r++;

        faces[r] = FaceEnum.Right;  clockwise[r] = false; r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = false; r++;
        faces[r] = FaceEnum.Right;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = true;  r++;

        faces[r] = FaceEnum.Right;  clockwise[r] = false; r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = false; r++;
        faces[r] = FaceEnum.Right;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = true;  r++;

        faces[r] = FaceEnum.Right;  clockwise[r] = false; r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = false; r++;
        faces[r] = FaceEnum.Right;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = true;  r++;

        faces[r] = FaceEnum.Right;  clockwise[r] = false; r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = false; r++;
        faces[r] = FaceEnum.Right;  clockwise[r] = true;  r++;
        faces[r] = FaceEnum.Down;   clockwise[r] = true;  r++;

        cube.rotate(faces, clockwise);
    }
    /////////////////////////////////////////////////////////

}
