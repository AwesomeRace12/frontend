
import java.util.Comparator;
// Class
class City implements Comparator<City>
{
public String city;

public int cost;

public City()
{
	
}
public int compare(City node1, City node2)
{
  if (node1.cost < node2.cost)
   return -1;
  if (node1.cost > node2.cost)
   return 1;
  return 0;
}

public City(String city, int cost)
{
  this.city = city;
  this.cost = cost;
}
}