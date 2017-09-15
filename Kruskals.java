import java.io.File;
import java.util.*;

public class Kruskals 
{ 
	class Vertex 
	  {
	    public String name;
	    public int id;
	    public HashMap<String, Integer> mapEdges;
	    public Vertex(String str) 
	    {
	      String[] array = str.split(",");
	      name = array[0];
	      mapEdges = new HashMap<>();
	      for(int i=1; i< array.length; i = i+2) 
	      {
	        mapEdges.put(array[i], Integer.valueOf(array[i+1]));
	      }
	    }
	    
	    public boolean isExist(Vertex any) 
	    {
	      return this.mapEdges.containsKey(any.name);
	    }
	    
	    public int Distance(Vertex any) 
	    {
	      return this.mapEdges.get(any.name);
	    }
	  }
	
	 class Edge 
	  { 
	    public int distance;
	    public Vertex start;
	    public Vertex end;
	    
	    public Edge(Vertex start, Vertex end) 
	    {
	      this.start = start;
	      this.end = end;
	      this.distance = start.Distance(end);
	    }
	    
	    public int getu() 
	    {
	      return start.id;
	    }
	    
	    public int getv() 
	    {
	      return end.id;
	    }	    
}
	  
  public List<Edge> edges;
  public int num;
  public List<Vertex>vertexs;
  public Kruskals(File file) 
  {
    try 
    {
      Scanner sc = new Scanner(file);
      vertexs = new ArrayList<>();
      while(sc.hasNextLine()) 
      {
        String str = sc.nextLine();
        Vertex v = new Vertex(str);
        v.id = num;
        vertexs.add(v);
        num++;
      }
      edges = new ArrayList<>();
      int len = vertexs.size();
      for(int i=0; i < len; i++) 
      {
        Vertex u = vertexs.get(i);
        for(int j=i; j < len; j++) 
        {
          Vertex v = vertexs.get(j);
          if(u.isExist(v)) 
          {
            edges.add(new Edge(u, v));
          }
        }
      }
    }
    catch(Exception e) 
    {
      e.printStackTrace();
    }
  }
  public ArrayList<Edge> kruskal( List<Edge> edges, int nVertices ) 
  {
      DisjSets disjSet = new DisjSets( nVertices );
      PriorityQueue<Edge> priorityqueue = new PriorityQueue<>( 30, new Comparator<Edge>() 
      {
        public int compare(Edge o1, Edge o2) 
        {
          return o1.distance - o2.distance;
        }
      }
      );
      for(Edge e : edges) 
      {
    	  priorityqueue.add(e);
      }
      ArrayList<Edge> mshort = new ArrayList<>( );
      while( mshort.size( ) != nVertices - 1 ) 
      {
          Edge kruskal = priorityqueue.poll();       
          int uset = disjSet.find( kruskal.getu( ) );
          int vset = disjSet.find( kruskal.getv( ) );
          if( uset != vset ) 
          {
              // Accept the edge as the shortest path
        	  mshort.add( kruskal );
              disjSet.union( uset, vset );
          } 
       }
      return mshort; 
  }
   
  
  public static void main(String[] args) 
  {
    try 
    {
      File datafile = new File("assn9_data.csv");
      Kruskals ks = new Kruskals(datafile);
      ArrayList<Edge> mshort = ks.kruskal(ks.edges, ks.num);
      int sum = 0;
      int k = 0;
      for(Edge kruskal : mshort) 
      {
        System.out.println("Step " + k + ":" +kruskal.start.name + " ---- " + kruskal.end.name + "  the distance is  " + kruskal.distance);
        sum = sum + kruskal.distance;
        k++;
      }
      System.out.println("The sum distances is" + " " + sum);
    }
    catch (Exception e) 
    {
      e.printStackTrace();
    }
  }
}

