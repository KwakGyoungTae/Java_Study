package Q4;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


public class XmlToCsv {



	public static void main(String[] args){


		try {

			File file = new File("C:\\Users\\nets\\IdeaProjects\\test1\\src/trip.xml");
			File outFile = new File("C:\\Users\\nets\\IdeaProjects\\test1\\src/trip.csv");
			String attrHeaders[] = {"cityname","country","mainground","landmark","language"};

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder bulider = factory.newDocumentBuilder();
			Document doc = bulider.parse(file);
			String header = "";
			String attrStr = "";


			doc.getDocumentElement().normalize();
			NodeList nodes = doc.getElementsByTagName("city");

			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));


			for (int i=0;i<attrHeaders.length;i++)
				header += attrHeaders[i] + ",";
			println(pw, header);






			for (int i=0;i< nodes.getLength();i++)
			{
				Node node = nodes.item(i);


				if (node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;

					for (int j=0;j<attrHeaders.length;j++)
						attrStr += element.getAttribute(attrHeaders[j])+",";

					println(pw,attrStr);
					attrStr = "";

				}
			}


			pw.close();

		} catch (Exception e)
		{
			e.printStackTrace();
			//System.out.println(e);

		}
	}

	public static void println(PrintWriter w,String... values)
	{
		for (int i=0,iend=values.length;i<iend;i++)
		{
			w.print(values[i]);
			if (i < iend-1)
				w.print(",");

		}
		w.println();

	}


}
