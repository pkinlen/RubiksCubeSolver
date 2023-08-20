# RubixCubeSolver

This project explores how a neural network can be used to solve a Rubix Cube.
The neural network takes as input (X[.]) a digital representation of the current state of a cube.
The output is a vector Y[.] where Y[i] is the probability that the cube can be solved in i steps.
Where each step is a quarter rotation of one face. 
Suppose the net predicts that the cube can be solved in 8 steps.
What we can do is then try the 12 different rotations (six faces can each rotate clockwise or anticlockewise). We would then expect the net will predict that one of those 12 rotations will yield a state which can be solved in 8-1=7 steps. The other 11 rotations will give us a state which takes 8+1=9 steps. We choose the rotation that reduces the number of steps. 
Thus we can solve the cube. 

The GenerateTrainingData folder contains code written in Java that can be used to generate training and test data. 

If you're interested in reading a blog post about this solver, [here's the link.](https://abitofmaths.blogspot.com/2023/08/using-neural-network-to-solve-rubiks.html)
