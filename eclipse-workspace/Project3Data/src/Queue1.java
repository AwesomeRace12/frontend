//Aakarsh Anugu
//Khan
import java.io.*;
import java.util.*;

public class Queue1
{
private List<Root> ListOfVisitedNode;
private HashMap<String, Integer> minimumDistance;
private HashMap<String, Integer> distanceofVertices;
private PriorityQueue<City> priorityQueue;
private int type1,type2;
private String CostInTime[][], Vertex1, Vertex2;
private List<String> listOfNodes;
private Set<String> List;


// prove the function that it creates priority queue of the nodes
public Queue1(List<String> listOfTheNodes)
{   
  this.listOfNodes = listOfTheNodes;
  minimumDistance = new HashMap<String,Integer>();
  distanceofVertices = new HashMap<String,Integer>();
  List = new HashSet<String>();
  ListOfVisitedNode = new ArrayList<Root>();
  priorityQueue = new PriorityQueue<City>(new City());      
}
//return the node that has the minimum distance
private String getVetex()
{
  String node = priorityQueue.remove().city;
  return node;
}
// Dijkstra's algorithm
public void dijkastra(String costInTime[][], String requiredPath[])
{
  String vartexEvaluation;
  Vertex1 = requiredPath[0];
  Vertex2 = requiredPath[1];
  if(requiredPath[2].equalsIgnoreCase("C"))
  {
   type1 = 2;
   type2 = 3;
  }
  else
  {
   type1 = 3;
   type2 = 2;
  }
  this.CostInTime = costInTime;

  for (String vertex:listOfNodes)
  {
   minimumDistance.put(vertex, Integer.MAX_VALUE);
   distanceofVertices.put(vertex, Integer.MAX_VALUE);
  }

  priorityQueue.add(new City(Vertex1, 0));
  minimumDistance.replace(Vertex1,0);
  distanceofVertices.replace(Vertex1, 0);
  while (!priorityQueue.isEmpty())
  {
   vartexEvaluation = getVetex();
   Root evaluationNodeList = new Root();
   evaluationNodeList.setNode(vartexEvaluation);
   List.add(vartexEvaluation);
   FindTheadjacent(vartexEvaluation, evaluationNodeList);
   if(!isThereVertex(ListOfVisitedNode, vartexEvaluation))
    ListOfVisitedNode.add(evaluationNodeList);
  }
}

// Find the neighboring node in the file
private void FindTheadjacent(String evaluationNode, Root evaluationNodeList)
{
  int a = -1;
  int newd = -1;

  for (int i = 0; i<CostInTime.length; i++)
  {
   if(!CostInTime[i][0].equals(evaluationNode))
    continue;
   String target;
   for (int j = 0; j < listOfNodes.size(); j++)
   {
    target = listOfNodes.get(j);
    if(!CostInTime[i][1].equals(target))
     continue;
    if (!List.contains(target))
    {
     a = Integer.parseInt(CostInTime[i][type1]);
     newd = minimumDistance.get(evaluationNode) + a;
     if (newd < minimumDistance.get(target))
     {
      minimumDistance.replace(target,newd);
      distanceofVertices.replace(target,distanceofVertices.get(evaluationNode) +
        Integer.parseInt(CostInTime[i][type2]));
      for (Root path : ListOfVisitedNode)
      {
       if(path.exists(target))
        path.delete(target);
       break;
      }
      evaluationNodeList.add(target);
     }
     priorityQueue.add(new City(target,minimumDistance.get(target)));
    }  
   }
  }
}
// Check the node in the file
private boolean isThereVertex(List<Root> listOfVisitedVertex, String node)
{
  for (Root p : ListOfVisitedNode)
  {
   if(p.getNode().equals(node))
    return true;
  }
  return false;
}

// Find the destination node in the file.
private static List<String> PathofTheRoot(List<Root> visitedCity, String target)
{
  List<String> completePath = new ArrayList<String>();
  for( Root path : visitedCity)
  {
   if(!path.exists(target))
    continue;
   completePath = PathofTheRoot(visitedCity, path.getNode());
   completePath.add(target);
   return completePath;
  }
  completePath.add(target);
  return completePath;
}

// Main
public static void main(String[] arg) throws FileNotFoundException
{
  String timeCost[][],PathList[][];
  //call the files
  BufferedReader flightdata, requestedata;
  List<String> listOfTheNode;
  PrintWriter output = new PrintWriter("output.txt");//reads the output to the empty file
  try
  {
   // Read the files
   flightdata = new BufferedReader(new FileReader("Flightdata.txt"));
   requestedata = new BufferedReader(new FileReader("Requested.txt"));

   String string;
   listOfTheNode = new ArrayList<String>();
   timeCost = new String[Integer.parseInt(flightdata.readLine())][8];
   PathList = new String[Integer.parseInt(requestedata.readLine())][8];

   int i=0,j; String _node;

   // Make tokens of the data of the flight data
   while((string = flightdata.readLine()) != null)
   {
    j=0;
    StringTokenizer data = new StringTokenizer(string,"|");
    int k = 1;
    while(k<=2)
    {
     if(!listOfTheNode.contains(_node = data.nextToken()))
     {
      timeCost[i][j++] = _node;
      listOfTheNode.add(_node);
     }
     else
      timeCost[i][j++] = _node;
     k++;
    }

    while(data.hasMoreTokens())
    {
     timeCost[i][j++] = data.nextToken();
    }
    i++;         
   }
   i=0;
   while((string = requestedata.readLine()) != null)
   {
    j=0;
    StringTokenizer data = new StringTokenizer(string,"|");
    while(data.hasMoreTokens())       
     PathList[i][j++] = data.nextToken();
    i++;         
   }       
   i=1;
   for(String requsetedPath[] : PathList)
   {
    if(!(listOfTheNode.contains(requsetedPath[0])&& listOfTheNode.contains(requsetedPath[1])))
    {
     //plan cannot be created
     output.println("Path cannot be found");
     continue;
    }
    String type, otherType;

    if(requsetedPath[2].equals("T"))
    {
     type = "Time";
     otherType = "Cost";
    }
    else
    {
     type = "Cost";
     otherType = "Time";
    }
    Queue1 priorityQueue = new Queue1(listOfTheNode);
    priorityQueue.dijkastra(timeCost, requsetedPath);

    output.println("Flight "+i+": "+priorityQueue.Vertex1+", "+priorityQueue.Vertex2+" ("+type+")");
    for (String vertex:listOfTheNode)
    {
     if(!vertex.equals(priorityQueue.Vertex2))
      continue;
     List<String> list = PathofTheRoot(priorityQueue.ListOfVisitedNode,
       priorityQueue.Vertex2);
     for (int k = 0; k < list.size(); k++)
     {
      if(k == list.size()-1 )
       output.print(list.get(k)+". ");
      else
       //output.print("Path 1: ");
       output.print(list.get(k)+" --> ");
     }                  
     output.println(type+": " + priorityQueue.minimumDistance.get(vertex)+" "+otherType+": "+priorityQueue.distanceofVertices.get(vertex));
     break;
    }
    i++;
   }
  } catch (Exception e)
  {
   System.out.println("Exception occured:" + e.toString());
  }
  output.close();
}
}