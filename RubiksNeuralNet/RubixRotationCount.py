# In this file we build and train a neural network used to determine
# the number of (quarter) rotations required to solve a Rubik's cube.
#
# Author: Philip Kinlen, August 2023.
#
#################################################
# imports
import numpy as np
from keras.layers import Dense
from keras.models import Sequential

################################################
def readInData(dataFilePath, maxRotations, doPrint=False):
    # load the dataset
    dataset = np.loadtxt(dataFilePath, delimiter=',', dtype='int')

    # split into input (X) and output (y) variables
    data_x = dataset[:,1:]  # all columns except the first
    data_z = dataset[:,0]   # the first column

    numExamples = data_x.shape[0]

    if doPrint:
        print("Input data shape:" + str(dataset.shape))
        print('Number of examples read in: ' + str(numExamples))

    data_y = np.zeros((numExamples, maxRotations+1))
    i = np.arange(numExamples) # i = [0,1,2,...,numExamples-1]
    data_y[i,data_z[i]] = 1

    return data_x, data_y

################################################
def buildModel(numElmsInEachX, numElmsInEachY, numLayers):
    # The input X[.] will be a representation of the current state of the Rubiks cube.
    # The output Y[i] is the probability that the cube is i (quarter) rotations
    # away from being solved.

    # The input vector X[.] has much more elements than the output.
    # as we go through the layers of the neural net the number
    # of elements will step down. So we define the step variable:
    step      = int((numElmsInEachX - numElmsInEachY) / numLayers)

    # define the keras model
    model = Sequential()

    numOutputElms = 0

    for numElms in range(numElmsInEachX, numElmsInEachX - (numLayers* step), -step):
        print('Adding layer with elms: ' + str(numElms))
        numOutputElms = numElms - step
        model.add(Dense(numOutputElms, input_shape=(numElms,), activation='relu'))

    model.add(Dense(numElmsInEachY, input_shape=(numOutputElms,), activation='softmax'))

    # compile the keras model
    model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

    return model
###############################################
def fitAndSaveModel(model, numBatches, numEpochsForEachBatch, trainingX, trainingY, modelFilePath):

    totalEpochs = numBatches * numEpochsForEachBatch
    # fit the keras model on the dataset

    model.fit(trainingX, trainingY, epochs=totalEpochs, batch_size=numBatches)
    print('Finished fitting the model.')
    model.save_weights(modelFilePath)

###############################################
def runRotationTestsIndividually(model, lowRot, highRot, overallMaxRot, numTests, fileNameRoot):

    for r in range(lowRot, highRot+1):
        fileName = fileNameRoot + str(r) + "_" + str(r) + "_" + str(numTests) + ".csv"
        testX, testY = readInData(fileName, overallMaxRot)
        _, accuracy = model.evaluate(testX, testY, verbose=False)
        print('With ' + str(r) + ' rotations had accuracy: %.2f' % (accuracy*100) +'%' )
###############################################
# Set model parameters and constants:
maxNumRotations = 20
numElmsInEachX  = 120

layersInModel         = 10
numBatches            = 10
numEpochsForEachBatch = 5

###############################################
# File paths:
trainingDataFilePath = '../Data/TestData_0_8_9000.csv'
testFileNameRoot     = '../Data/TestData_'
modelFilePath        = 'modelWeights.keras'
###############################################

model = buildModel(numElmsInEachX, maxNumRotations+1, layersInModel)

doRetrainModel = True

if doRetrainModel :
    trainingX, trainingY = readInData(trainingDataFilePath, maxNumRotations, True)
    fitAndSaveModel(model, numBatches, numEpochsForEachBatch, trainingX, trainingY, modelFilePath)
else :
    model.load_weights(modelFilePath)

runRotationTestsIndividually(model, 0, 10, maxNumRotations, 1000,  testFileNameRoot)

print('Finished.')

