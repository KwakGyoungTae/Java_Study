import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Index
{
	private String indexName;
	private List<String> colunmList;

	public Index(String indexName)
	{
		this.indexName = indexName;
		this.colunmList = new ArrayList<>();
	}

	public String getIndexName()
	{
		return indexName;
	}

	public void setIndexName(String indexName)
	{
		this.indexName = indexName;
	}

	public List<String> getColunmList()
	{
		return colunmList;
	}

	public void setColunmList(List<String> colunmList)
	{
		this.colunmList = colunmList;
	}

	public void addColumn(String column)
	{
		colunmList.add(column);
	}
	@Override
	public String toString()
	{
		return indexName+","+colunmList;
	}
	public  void  writeHtml(PrintWriter writer )
	{
		{
			writer.println("<td>" + getIndexName() + "</td>");
			writer.println("<td colspan='4'>" + String.join(",", getColunmList()) + "</td>");
		}
	}
}
