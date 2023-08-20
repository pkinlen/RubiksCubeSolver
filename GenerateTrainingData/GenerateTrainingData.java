import java.util.Random;
import java.io.*;

public class GenerateTrainingData {

    // If numExamples contains {11,17, 9, 6}, then we will have
    //     11 examples with 0 rotations (which will all be the same)
    //     17 examples with 1 rotation  (max of 12 unique)
    //     9  examples with 2 rotations ( less than 132 unique)
    //     6  examples with 3 rotations

    public static void generateDataInRandomOrderWriteToFile(String path, int[] numExamples, long randomSeed) {
        Random      rnd      = new Random(randomSeed);
        FaceEnum[]  faces    = FaceEnum.values();
        int         numFaces = faces.length;

        int         totNumExamples = 0;
        for(int i = 0; i < numExamples.length; i++){
            totNumExamples += numExamples[i];
        }
        // In the code below we generate all the outputs rows and then shuffle them.
        // If we wanted a huge training set, we could run out of RAM.
        // As an alternative, we could possibly remove the trials string array,
        // writing to file as the strings are being generated.
        // However, it would require some more code to be written to ensure that the output file is in a
        // randomized order.
        // One way to do that would be to convert the number of examples array into a longer array:
        //       [4,2,3,5] -> [0,0,0,0,1,1,2,2,2,3,3,3,3,3]    ( i.e. 4 zeros, 2 ones, 3 twos and 5 threes )
        // and then generate an index array: [0,1,2,3,4,5,6,7,8,9,10,11,12,13]
        // After that we could suffle the index.

        String[] trials = new String[totNumExamples];
        int      eg  = 0;

        for(int nr = 0; nr < numExamples.length; nr++) {    // number of rotations
            for (int ne = 0; ne < numExamples[nr]; ne++) {  // number of examples

                Cube cube = new Cube();
                FaceEnum faceToRotate = getRandomFace(rnd, faces);
                boolean clockwise = rnd.nextBoolean();

                for (int r = 0; r < nr; r++) {   // rotations
                    FaceEnum nextRotation = getRandomFace(rnd, faces);
                    if (!(nextRotation.equals(faceToRotate))) {
                        // When we are rotating a different face this time to last time
                        // we can rotate in either direction.
                        clockwise = rnd.nextBoolean();
                    }   // else  if we are rotating the same face as the previous time
                        // then we do want to rotate in the same direction
                        // and not in the opposite direction which would undo the previous rotation.
                        // So in this case we want the clockwise variable to remain unchanged.

                    cube.rotate(nextRotation, clockwise);
                    faceToRotate = nextRotation;
                }

                trials[eg] = Integer.toString(nr) + "," + cube.getMachineReadableStringRepresentation();
                eg++;

                //cube.printCube("Have just added a cube with: " + Integer.toString(nr) + " random rotations.", true);
            }
        }
        try {
            shuffleArray(trials, rnd);

            FileWriter writer = new FileWriter(path);

            for ( eg = 0; eg < totNumExamples; eg++)
                writer.write(trials[eg]);

            writer.close();
            System.out.println("Successfully wrote to the file: " + path);
        } catch (IOException e) {
            System.out.println("An error occurred when trying to write to file: " + path);
            e.printStackTrace();
        }
    }
    ///////////////////////////////////////////////
    public static void shuffleArray(String[] array, Random rnd) {

        for (int i = 0; i < array.length;  i++) {
            int index = rnd.nextInt(array.length);
            String temp  = array[index];
            array[index] = array[i];
            array[i]     = temp;
        }
    }

    public static void generateDataAndSequentiallyWriteToFile(String path, int[] numExamples, long randomSeed) {
        // TODO: perhaps the order could be shuffled
        Random      rnd      = new Random(randomSeed);
        FaceEnum[]  faces    = FaceEnum.values();
        int         numFaces = faces.length;

        try {
            FileWriter writer = new FileWriter(path);

            for(int nr = 0; nr < numExamples.length; nr++){     // number of rotations
                for (int ne = 0; ne < numExamples[nr]; ne++) {  // number of examples

                    Cube cube = new Cube();
                    FaceEnum faceToRotate = getRandomFace(rnd, faces);
                    boolean  clockwise    = rnd.nextBoolean();
                    cube.rotate(faceToRotate, clockwise );

                    for (int r = 1; r < nr; r++) {   // rotations
                        FaceEnum nextRotation = getRandomFace(rnd, faces);
                        if (nextRotation.equals(faceToRotate)){
                            // If we are rotating in the same direction as the previous time
                            // then we do want to rotate in the same direction
                            // and not in the opposite direction which would undo the previous rotation
                            cube.rotate(nextRotation, clockwise);
                        } else
                            cube.rotate(nextRotation, rnd.nextBoolean());
                    }
                    writer.write(Integer.toString(nr) + "," +cube.getMachineReadableStringRepresentation());
                }
            }
            writer.close();
            System.out.println("Successfully wrote to the file: " + path);
        } catch (IOException e) {
            System.out.println("An error occurred when trying to write to file: " + path);
            e.printStackTrace();
        }

    }
    ///////////////////////////////////////////////
    private static FaceEnum getRandomFace(Random rnd, FaceEnum[] faces) {

        return faces[rnd.nextInt(faces.length)];
    }
    ///////////////////////////////////////////////
}
