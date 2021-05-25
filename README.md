# MB
MiBolsillo

The following classes have been written in Java (Eclipse platform). The way to implement this is to just create a Java Project, putting all of these classes into 1 project will allow for them to interact together, without the need of importing the classes or packages, and to check the code, you just need to run the Main.java class.

I have also included a screenshot of my results. 

NOTE: This program can be improved by adding safe guards, to make sure that the inputs are correct and fit constraints, set by problem. For example, making sure that negative values cannot be included, thus it is important to make sure that the graph details are correct.

I use 3 classes, Graph, Main and Edge. Edge is just a class that defines what an Edge is.
Whilst Graph countains all the methods that actually do things on my graph of nodes. 
Main is where I run specific methods to do specific tests, and this is where we parse in the directed graph, from here you can just change what the values in the direct graph are.

Edge and Main are very self explanitory, and I believe require no explanations.

Graph has multiple functions, and I start off by initializing my graph in my constructor. All that does is just get the parsed graph (String), into 
as nodes and edges. We split the string by the commas, and initialize the different letters as vertices, and the number as the weight of the edge between the vertices.

I have addEdge function that adds the edge into my LinkedList, which is where the graph is.
adj function just makes sure that whatever I inputed, is legal and is a part of the addEdge, it adds whatever edge into 
my graph.

CalculateDistance finds the total weight of a given path, it takes a path as a String, and then it adds up all the weights inside of the given route.
This is done by seeing two given points in a path is an actual edge, if it is, then it adds the weight of the edge into my total weight, if it is not,
then I break and say that the provided path does not exist.

CalculateTripsCount is to find path that works within a certain number of stops, so we just keep traversing path, until we've reached
max stops.

CalculateShortestPath is a DFS algorithm. I wanted to do Dijkstra's Algorithm at first, but this was easier to implement.
We traverse the entire graph, see if we have visited the node or not, if we have then we continue, if we don't, then we dont. 
The path has to of course reach its destination and it does, as we go through entire graph. Then,
we see all the paths that we have taken, and select the one that has the lowest cumilitive weight for the path.

CalculateRoutesCount counts the amount of paths from point A to B. We use our CalculateDistance, that allows us to traverse graph to
see if we can find a path, and we also make sure that this path we are plotting, is less than our maximum distance.

To save time, since we often use getIndex function, I find this index by using a binary search, instead of just simple traversing in O(n).
