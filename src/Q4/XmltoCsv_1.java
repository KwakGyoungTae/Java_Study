package Q4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XmltoCsv_1
{
	public static void main(String[] args) throws Exception
	{
		// DOM Parser
		writeCSV(getNodeList(new File("src\\info.xml")), new File("Output.csv"));
	}

	private static void writeCSV(NodeList list, File outputFile) throws IOException
	{
		List<String> headers = getHeaders(list);

		try(PrintWriter bw = new PrintWriter(new BufferedWriter(new FileWriter(outputFile))))
		{
			bw.println(String.join(",", headers));

			List<String> cols = new ArrayList<>();
			for (int i = 0; i < list.getLength(); i++)
			{
				Element element = (Element) list.item(i);
				for (String header : headers)
					cols.add(element.getAttribute(header));

				bw.println(String.join(",", cols));
				cols.clear();
			}
		}
	}

	private static List<String> getHeaders(NodeList list)
	{
		List<String> headers = new ArrayList<>();
		NamedNodeMap attrs = list.item(0).getAttributes();
		for (int i = 0; i < attrs.getLength(); i++)
			headers.add(attrs.item(i).getNodeName());
		return headers;
	}

	private static NodeList getNodeList(File xmlFile) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(xmlFile);

		Element root = document.getDocumentElement();

		// 태그명과 일치하는 모든 자식요소
		return root.getElementsByTagName("music");
	}
}