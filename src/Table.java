import java.awt.*;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

public class Table
{
	private String name;
	private List<Colunm> colunms;
	private Collection<Index> indices;

	public Table(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Colunm> getColunms()
	{
		return colunms;
	}

	public Collection<Index> getIndices()
	{
		return indices;
	}

	public void setIndices(Collection<Index> indices)
	{
		this.indices = indices;
	}

	public void setPrimaryColumn(String columnName)
	{
		for (Colunm colunm : colunms)
		{
			if (colunm.getColumnName().equals(columnName))
			{
				colunm.setPrimary(true);
				break;
			}

		}
	}

	public void setPrimaryColumns(List<String> primaryColumns)
	{
		for (String col : primaryColumns)
			setPrimaryColumn(col);
	}
	public void setColunms(List<Colunm> colunms)
	{
		this.colunms = colunms;
	}

	@Override
	public String toString()
	{
		return name+","+colunms+","+indices;
	}
	
	public void writeHtml(PrintWriter writer)
	{
			writer.println("<table class='" + getName() + "' style='border : 2px solid lavender; width : 100%;text-align: center;'>");
			writer.println("<caption style='caption-side : inherit;'>" + getName() + "</caption>");
			writer.println("<tr style='background : lavender;'>");
			writer.println("<td>컬럼명</td>");
			writer.println("<td>데이터 타입</td>");
			writer.println("<td>길이</td>");
			writer.println("<td>PK</td>");
			writer.println("<td>Null</td>");
			writer.println("<td>최빈값</td>");
			writer.println("</tr>");

			for (Colunm colunm : getColunms())
			{
				colunm.writeHtml(writer);
			}

			writer.println("<tr style='background : lavender;'>");
			writer.println("<td>인덱스 명</td>");
			writer.println("<td colspan='5'>컬럼</td>");
			writer.println("</tr>");

			writer.println("<tr>");
			for (Index idx : getIndices())
			{
				idx.writeHtml(writer);
			}
			writer.println("</tr>");


			writer.println("</table>");
	}


}