package Q4;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvToXml
{
	public static void main(String args[]) throws IOException, ParserConfigurationException, TransformerException
	{
		List<List<String>> resultStr = readCsv();
		writeXml(resultStr);
	}

	private static void writeXml(List<List<String>> resultStr) throws ParserConfigurationException, TransformerException, FileNotFoundException
	{

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			//trip 엘린먼트 생성
			Element trip = doc.createElement("trip");
			doc.appendChild(trip);

			List<String> attrNames = new ArrayList<String>();
			List<String> attrValues = new ArrayList<String>();

			attrNames = resultStr.get(0);

			for (int i = 1; i< resultStr.size(); i++)
			{
				Element city = doc.createElement("city");
				trip.appendChild(city);

				attrValues = resultStr.get(i);

				for (int j=0;j<5; j++)
					city.setAttribute(attrNames.get(j),attrValues.get(j));

			}
		makeXml(doc);
	}

	private static void makeXml(Document doc) throws FileNotFoundException, TransformerException
	{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");

		DOMSource source = new DOMSource(doc);
		StreamResult streamResult = new StreamResult(new FileOutputStream(new File("C:\\Users\\nets\\IdeaProjects\\test1\\src\\Q4/trip1.xml")));

		transformer.transform(source,streamResult);
	}

	private static List<List<String>> readCsv() throws IOException
	{
		List<List<String>> resultStr = new ArrayList<>();
		BufferedReader bufferReader = null;
		List<String> attrList = new ArrayList<String>();
		List<String> headerList = new ArrayList<String>();

			bufferReader = Files.newBufferedReader(Paths.get("C:\\Users\\nets\\IdeaProjects\\test1\\src\\Q4/trip.csv"));

			String line = "";

			while ((line = bufferReader.readLine()) != null)
			{
				String returnList[] = line.split(",");

					attrList = Arrays.asList(returnList);
					resultStr.add(attrList);
			}

		return resultStr;
	}

}
