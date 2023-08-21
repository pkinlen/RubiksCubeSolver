// One of the main purposes of this code it to generate training data for
// the rubix cube neural network.
// The neural net will take as input the state of a cube and it will
// determine how many quarter rotations are required to solve it.
// This code will start with a solved (virtual) cube. It will rotate it through n
// random (quarter) rotations and will then add a row to the output file
// containing the number of rotations and a digital representation of the state of the cube.
public class Main {

    ///////////////////////////////////////////////
    public static void main(String[] args) {
        System.out.println("A rubix cube.");

        generateTrainingDataMedium(2);

        //generateTrainingData(1);
        //generateTrainingDataTiny(0);
        //generateTrainingDataEnormous(6);

        runExample();
    }
    /////////////////////////////////////////////
    public static void runExample(){

        Cube cube = new Cube(); // The default starting position is fully solved
        cube.printCube("Starting cube: ", true);
        cube.printCube("And here is a machine readable representation", false);

        TestCode.twentyFourRotationReturnToStart(cube);
        cube.printCube("After 24 rotations should have recovered original cube: ", true);

        TestCode.applyRotations14(cube);
        cube.printCube("After 14 more rotations we have: ", true);

        int numRotations = 100;
        long seed = System.currentTimeMillis();
        cube.applyRandomRotations(numRotations, seed);
        cube.printCube("After " + Integer.toString(numRotations) +" random rotations: ", true);
        cube.printCube("And here is a machine readable representation", false);

    }
    ///////////////////////////////////////////////
    public static void generateTrainingDataTiny(long randomSeed){

        int[]  numExamples = new int[4];

        int i = 0;
        numExamples[i] = 2;       i++;  // num of cubes with 0 quarter rotations
        numExamples[i] = 2;       i++;  // num of cubes with 1 quarter rotation
        numExamples[i] = 2;       i++;  // num of cubes with 2 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 3 quarter rotations

        String outputPath = getOutputFileName(numExamples);
        GenerateTrainingData.generateDataInRandomOrderWriteToFile(outputPath, numExamples, randomSeed);
    }
    ///////////////////////////////////////////////
    public static void generateTrainingData(long randomSeed){

        int[]  numExamples = new int[19];

        int i = 0;
        numExamples[i] = 0;       i++;  // num of cubes with 0 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 1 quarter rotation
        numExamples[i] = 0;       i++;  // num of cubes with 2 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 3 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 4 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 5 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 6 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 7 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 8 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 9 quarter rotations
        numExamples[i] = 1000;       i++;  // num of cubes with 10 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 11 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 12 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 13 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 14 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 15 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 16 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 17 quarter rotations
        numExamples[i] = 0;       i++;  // num of cubes with 18 quarter rotations

        String outputPath = getOutputFileName(numExamples);
        GenerateTrainingData.generateDataInRandomOrderWriteToFile(outputPath, numExamples, randomSeed);
    }
    ///////////////////////////////////////////////
    public static void generateTrainingDataMedium(long randomSeed){

        int[]  numExamples = new int[19];

        int i = 0;
        numExamples[i] = 100;       i++;  // num of cubes with 0 quarter rotations  1
        numExamples[i] = 100;       i++;  // num of cubes with 1 quarter rotation   12
        numExamples[i] = 100;       i++;  // num of cubes with 2 quarter rotations  144
        numExamples[i] = 100;       i++;  // num of cubes with 3 quarter rotations  288+1440 = 1728
        numExamples[i] = 100;       i++;  // num of cubes with 4 quarter rotations
        numExamples[i] = 100;       i++;  // num of cubes with 5 quarter rotations
        numExamples[i] = 100;       i++;  // num of cubes with 6 quarter rotations
        numExamples[i] = 100;       i++;  // num of cubes with 7 quarter rotations
        numExamples[i] = 100;       i++;  // num of cubes with 8 quarter rotations

        String outputPath = getOutputFileName(numExamples);
        GenerateTrainingData.generateDataInRandomOrderWriteToFile(outputPath, numExamples, randomSeed);
    }
    ///////////////////////////////////////////////
    public static void generateTrainingDataBig(long randomSeed){

        int[]  numExamples = new int[19];

        int i = 0;
        numExamples[i] = 100000;       i++;  // num of cubes with 0 quarter rotations
        numExamples[i] = 100000;       i++;  // num of cubes with 1 quarter rotation
        numExamples[i] = 100000;       i++;  // num of cubes with 2 quarter rotations
        numExamples[i] = 100000;       i++;  // num of cubes with 3 quarter rotations
        numExamples[i] = 100000;       i++;  // num of cubes with 4 quarter rotations
        numExamples[i] = 100000;       i++;  // num of cubes with 5 quarter rotations
        numExamples[i] = 100000;       i++;  // num of cubes with 6 quarter rotations
        numExamples[i] = 100000;       i++;  // num of cubes with 7 quarter rotations
        numExamples[i] = 100000;       i++;  // num of cubes with 8 quarter rotations
        numExamples[i] = 100000;       i++;  // num of cubes with 9 quarter rotations
        numExamples[i] = 00;       i++;  // num of cubes with 10 quarter rotations
        numExamples[i] = 00;       i++;  // num of cubes with 11 quarter rotations
        numExamples[i] = 00;       i++;  // num of cubes with 12 quarter rotations
        numExamples[i] = 00;       i++;  // num of cubes with 13 quarter rotations
        numExamples[i] = 00;       i++;  // num of cubes with 14 quarter rotations
        numExamples[i] = 00;       i++;  // num of cubes with 15 quarter rotations
        numExamples[i] = 00;       i++;  // num of cubes with 16 quarter rotations
        numExamples[i] = 00;       i++;  // num of cubes with 17 quarter rotations
        numExamples[i] = 00;       i++;  // num of cubes with 18 quarter rotations

        String outputPath = getOutputFileName(numExamples);
        GenerateTrainingData.generateDataInRandomOrderWriteToFile(outputPath, numExamples, randomSeed);
    }
    ///////////////////////////////////////////////////////////
    public static void generateTrainingDataEnormous(long randomSeed){
        int[]  numExamples = new int[19];

        int i = 0;
        numExamples[i] = 10000;       i++;  // num of cubes with 0 quarter rotations
        numExamples[i] = 30000;       i++;  // num of cubes with 1 quarter rotation
        numExamples[i] = 50000;       i++;  // num of cubes with 2 quarter rotations
        numExamples[i] = 60000;       i++;  // num of cubes with 4 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 5 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 6 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 7 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 8 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 9 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 10 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 11 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 12 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 13 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 14 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 15 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 16 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 17 quarter rotations
        numExamples[i] = 80000;       i++;  // num of cubes with 18 quarter rotations

        String outputPath = getOutputFileName(numExamples);
        GenerateTrainingData.generateDataInRandomOrderWriteToFile(outputPath, numExamples, randomSeed);
    }
    ////////////////////////////////////
    public static String getOutputFileName(int[] arr){

        int minIdx = 1000; // lowest index that contains a positive value
        int maxIdx = -1;   // highest index that contains a positive value
        int sumVal = 0;

        for(int i = 0; i < arr.length; i++){

            if(arr[i] > 0){
                if (i < minIdx)
                    minIdx = i;

                if ( i > maxIdx)
                    maxIdx = i;
            }
            sumVal += arr[i];
        }

        return "../Data/TestData_" + Integer.toString(minIdx) + "_"
                + Integer.toString(maxIdx) + "_" + Integer.toString(sumVal) + ".csv" ;
    }
    ///////////////////////////////////////////////////////////////
}
