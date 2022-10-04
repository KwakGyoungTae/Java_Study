package Q4;

import javax.xml.stream.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvToXmlConverter
{
	public static void main(String[] args) throws IOException, XMLStreamException
	{
		List<List<String>> readCsvList = readCsv();
		createXml(readCsvList);
	}

	private static List<List<String>> readCsv() throws IOException
	{
		List<List<String>> resultList = new ArrayList<>();
		try(BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\Users\\nets\\IdeaProjects\\test1\\src/trip.csv")))
		{
			String line = "";
			while ((line = reader.readLine()) != null)
			{
				resultList.add(Arrays.asList(line.split(",")));
			}
		}
		return resultList;
	}

	private static void createXml(List<List<String>> resultList) throws IOException, XMLStreamException
	{
		XMLStreamWriter writer = null;
		try
		{
			writer = XMLOutputFactory.newFactory().createXMLStreamWriter(new FileWriter("C:\\Users\\nets\\IdeaProjects\\test1\\src/trip_reader.xml"));
			writer.writeStartDocument();
			writer.writeStartElement("trip");
			List<String> attrNames = resultList.get(0);


			for (int i= 1,iend=resultList.size(); i<iend; i++)
			{
				writer.writeStartElement("city");
				List<String> attrValues = resultList.get(i);

				for (int j=0,jend=attrNames.size();j<jend;j++)
					writer.writeAttribute(attrNames.get(j),attrValues.get(j));

				writer.writeEndElement();
			}

			writer.writeEndElement();
			writer.flush();
		}
		finally
		{
			if(writer != null)
				writer.close();
		}


	}
}
