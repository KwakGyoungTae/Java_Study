import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.Collections.list;
import static java.util.Collections.reverseOrder;

public class Colunm implements Comparable<Colunm>
{
	private String tableName;
	private String columnName;
	private String dataType;
	private int dataLength;
	private boolean primary;
	private boolean nullable;

	private Map<String,Integer> columnPrequency;



	public Colunm(String tableName, String columnName, String dataType, int dataLength, boolean nullable)
	{
		this.tableName = tableName;
		this.columnName = columnName;
		this.dataType = dataType;
		this.dataLength = dataLength;
		this.nullable = nullable;
	}

	public Colunm(ResultSet rs) throws SQLException
	{
		this(rs.getString(3), rs.getString(4), rs.getString("TYPE_NAME"), rs.getInt("COLUMN_SIZE"), rs.getString("IS_NULLABLE").equals("YES"));
	}

	public String getTableName()
	{
		return tableName;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public int getDataLength()
	{
		return dataLength;
	}

	public void setDataLength(int dataLength)
	{
		this.dataLength = dataLength;
	}

	public boolean isPrimary()
	{
		return primary;
	}

	public void setPrimary(boolean primary)
	{
		this.primary = primary;
	}

	public boolean isNullable()
	{
		return nullable;
	}

	public void setNullable(boolean nullable)
	{
		this.nullable = nullable;
	}

	public Map<String, Integer> getColumnPrequency()
	{
		return columnPrequency;
	}

	public void setColumnPrequency(Map<String, Integer> columnPrequency)
	{
		this.columnPrequency = columnPrequency;
	}

	@Override
	public String toString()
	{

		return columnName+","+dataType+","+dataLength+","+primary+","+nullable;
	}

	public  void  writeHtml(PrintWriter writer )
	{
			writer.println("<tr>");
			writer.println("<td>" + getColumnName() + "</td>");
			writer.println("<td>" + getDataType() + "</td>");
			writer.println("<td>" + getDataLength() + "</td>");
			writer.println("<td>" + (isPrimary() ? "PK" : "") + "</td>");
			writer.println("<td>" + (isNullable() ? "" : "NN") + "</td>");
			writer.println("<td style='text-align: left;'>");

			int cnt=0;

			for (Map.Entry<String,Integer> col : columnPrequency.entrySet())
			{
				if (cnt == 0)
					writer.println(col.getKey()+"("+col.getValue()+")");

				if (cnt == 4)
				{
					writer.println(col.getKey()+"("+col.getValue()+")"+", ···");
					break;
				}

				if(cnt>0 && cnt<4)
					writer.println("," + col.getKey() + "(" + col.getValue() + ")");

				cnt++;

			}

			writer.println("</td>");
			writer.println("</tr>");

	}

	public int getSeq()
	{
		return isPrimary() ? 1:isNullable() ? 3 : 2 ;
	}


	@Override
	public int compareTo(Colunm o)
	{
		// 1 = 크다, 0 = 같다, -1 작다

		/*int i = getColumnName().compareTo(o.getColumnName());
		int j = (this.isPrimary() ? 1 : 0) - (o.isPrimary() ? 1 : 0);
		int k = (this.isNullable() ? 1 : 0) - (o.isNullable() ? 1 : 0);

		if (j>0)
			return -1;

		else if(j==0)
		{
			if (k>0)
				return 1;

			else if (k ==0)
			{
				if (i>0)
					return 1;
				else if (i == 0)
					return 0;
				else
					return -1;
			}
			else
				return -1;
		}
		else
			return 1;
	}*/
		int d = getSeq()-o.getSeq();
		return d == 0 ? columnName.compareTo(o.columnName):d;
	}
}
