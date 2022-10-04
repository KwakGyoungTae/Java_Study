import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

public class GetTableData
{
	public static void main(String[] args) throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:kwak", "KWAK", "1234");)
		{
			List<Table> tables = getTables(conn);

			writeHtml(tables);
		}
	}

	private static List<Table> getTables(Connection conn) throws Exception
	{
		DatabaseMetaData dbMetaData = conn.getMetaData();
		List<Table> tables = getTables(dbMetaData.getTables(null, "KWAK", "%", new String[]{"TABLE"}));

		for (Table table : tables)
		{
			table.setIndices(getIndices(dbMetaData, table));
			table.setColunms(getColunms(dbMetaData, table));
			table.setPrimaryColumns(getPrimaryColumns(dbMetaData, table));
			//table.getColunms().stream().sorted(Comparator.comparing(Colunm::isPrimary).reversed().thenComparing(Colunm::isNullable).thenComparing(Colunm::getColumnName)).forEach(System.out::println);
			Collections.sort(table.getColunms());

			for (Colunm col : table.getColunms())
				System.out.println(Arrays.asList(col));


			for (Colunm colunm : table.getColunms())
				colunm.setColumnPrequency(getColumnKindPrequency(table.getName(),colunm.getColumnName(),conn));

		}
		return tables;
	}

	private static Map<String,Integer> getColumnKindPrequency(String tableName, String columnName, Connection conn) throws SQLException
	{
		Map<String,Integer> columnPrequency = new LinkedHashMap<>();

			//String sql ="select ?,count(?) as cnt from ? group by ?";
			String sql = String.format("select %s, count(%s) as cnt from %s group by %s order by cnt desc", columnName, columnName, tableName, columnName);

			try(PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery())
			{
				while (rs.next() && columnPrequency.size()<6)
					columnPrequency.put(rs.getString(columnName), rs.getInt("cnt"));
			}

		return columnPrequency;
	}

	private static List<String> getPrimaryColumns(DatabaseMetaData dbMetaData, Table table) throws SQLException
	{
		try (ResultSet rs = dbMetaData.getPrimaryKeys(null, "KWAK", table.getName()))
		{
			return getPrimaryColumns(rs);
		}
	}

	private static List<Colunm> getColunms(DatabaseMetaData dbMetaData, Table table) throws Exception
	{
		try (ResultSet rs = dbMetaData.getColumns(null, "KWAK", table.getName(), "%"))
		{
			return getColumns(rs);
		}
	}

	private static Collection<Index> getIndices(DatabaseMetaData dbMetaData, Table table) throws SQLException
	{
		try (ResultSet rs = dbMetaData.getIndexInfo(null, "KWAK", table.getName(), true, false))
		{
			return getIndexCollection(rs);
		}
	}

	public static List<Table> getTables(ResultSet rs) throws SQLException
	{
		List<Table> tables = new ArrayList<>();

		while (rs.next())
			tables.add(new Table(rs.getString("TABLE_NAME")));

		tables.sort(Comparator.comparing(v -> v.getName()));

		return tables;
	}

	public static List<Colunm> getColumns(ResultSet rs) throws Exception
	{
		List<Colunm> columns = new ArrayList<>();

		while (rs.next())
			columns.add(new Colunm(rs));

		return columns;
	}


	public static List<String> getPrimaryColumns(ResultSet rs) throws SQLException
	{
		List<String> list = new ArrayList<>();

		while (rs.next())
			list.add(rs.getString("COLUMN_NAME"));

		return list;
	}

	public static Collection<Index> getIndexCollection(ResultSet rs) throws SQLException
	{
		Map<String, Index> map = new HashMap<>();
		try
		{
			while (rs.next())
			{
				String idxName = rs.getString("INDEX_NAME");
				String columnName = rs.getString("COLUMN_NAME");

				if (idxName != null)
					map.computeIfAbsent(idxName, v -> new Index(v)).addColumn(columnName);
			}
		}catch (Exception e)
		{
			e.getMessage();
		}
		return map.values();
	}

	private static void writeHtml(List<Table> tables) throws IOException
	{
		File file = new File("C:\\Users\\nets\\IdeaProjects\\test1\\src/table.html");

		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));)
		{
			writer.println("<html><head>");
			writer.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>");
			writer.println("<script type='text/javascript'>");
			writer.println("$(function() {");
			writer.println("$('.span0').click(function(){ $('.table0').toggle('slow');})");
			writer.println("$('.span1').click(function(){ $('.table1').toggle('slow');})");
			writer.println("$('.span2').click(function(){ $('.table2').toggle('slow');})");
			writer.println("});");
			writer.println("</script>");
			writer.println("</head>");
			writer.println("<body>");
			writer.println("<h2>테이블 명 목록</h2>");

			for (int i = 0, iend = tables.size(); i < iend; i++)
				writer.println("<span class='" + tables.get(i).getName() + "'>" + tables.get(i).getName() + "</span>" + "<br>");

			writer.println("<hr style='border: lightskyblue 2px solid;'>");

			for (Table table : tables)
				table.writeHtml(writer);

			writer.println("</body>");
			writer.println("</html>");
		}
	}
}
