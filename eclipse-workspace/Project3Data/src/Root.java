
import java.util.ArrayList;
import java.util.List;
// Create class
class Root
{
private List<String> root;
private String city;
// Get the node
public String getNode()
{
  return this.city;
}
// Add the node in the List
public void add(String city)
{
  root.add(city);
}
//Delete the node 
public void delete(String city)
{
  root.remove(city);
}
// Check if the node exists
public Boolean exists(String city)
{
  if(root.contains(city))
   return true;
  return false;
}
//Create the arrayList
public Root()
{
  root = new ArrayList<String>();
}
// Setnode
public void setNode(String Node)
{
  this.city = Node;
}
}