package Q4;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.*;

public class ParseCsv
{
	private XMLInputFactory factory = null;
	private XMLStreamReader reader = null;

	public  void readXML() throws IOException, XMLStreamException
	{
		factory = XMLInputFactory.newInstance();
		File outFile = new File("C:\\Users\\nets\\IdeaProjects\\test1\\src/trip_reader.csv");
		reader= factory.createXMLStreamReader(new FileReader("C:\\Users\\nets\\IdeaProjects\\test1\\src/trip1.xml"));
		String storage="";
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));

		while (reader.hasNext())
		{
			Integer target = reader.next();

				if (target.equals(XMLEvent.START_ELEMENT))
				{
					int cnt = 0;

					for (int i=0;i<5;i++)
					{
						if (i<4)
						{
							System.out.print(reader.getAttributeValue(i)+",");
							pw.print(reader.getAttributeValue(i)+",");
							cnt++;
						}
						else if (i == 4)
						{
							System.out.print(reader.getAttributeValue(i));
							pw.print(reader.getAttributeValue(i));
							cnt++;
						}

						if (cnt%5 ==0)
						{
							System.out.println();
							pw.println();
						}

					}

				}
			}

		pw.close();
		reader.close();
	}


	public static void main(String args[]) throws XMLStreamException, IOException
	{
		ParseCsv pc = new ParseCsv();
		pc.readXML();
	}
}
