import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;
 
public class ReadXMLFile 
{
  
  List<Attraction> attractions = new ArrayList<Attraction>();
  
  public ReadXMLFile(String filename) 
  {
 
    try 
    {
 
      File fXmlFile = new File(filename);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);
      
      doc.getDocumentElement().normalize();
      
      NodeList nList = doc.getElementsByTagName("Attraction");
      
      for (int temp = 0; temp < nList.getLength(); temp++) 
      {
        
        Node nNode = nList.item(temp);
        
        if (nNode.getNodeType() == Node.ELEMENT_NODE) 
		{          
          Element eElement = (Element) nNode;
          attractions.add(new Attraction(eElement.getElementsByTagName("Name").item(0).getTextContent(), eElement.getElementsByTagName("City").item(0).getTextContent(), eElement.getElementsByTagName("Price").item(0).getTextContent()));
        }
      }
    } 
    catch (Exception e) 
    {
      e.printStackTrace();
    }
  } 
}
