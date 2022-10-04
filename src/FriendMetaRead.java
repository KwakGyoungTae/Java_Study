import java.sql.*;

public class FriendMetaRead
{

	public static void main(String[] args) throws SQLException
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData metaData;

		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:kwak", "kwak", "1234");
			pstmt = conn.prepareStatement("SELECT * FROM friend");
			rs = pstmt.executeQuery();
			metaData = rs.getMetaData();

			for (int i=1,cols = metaData.getColumnCount(); i<=cols;i++)
			{
				System.out.print(metaData.getColumnName(i)+"\t\t");
				System.out.print(metaData.getColumnTypeName(i)+" ");
				System.out.print(metaData.isNullable(i)+" ");
				System.out.print(metaData.getPrecision(i)+" ");
			}
			System.out.println();

			while (rs.next())
			{
				for (int i=1,cols = metaData.getColumnCount();i<=cols;i++)
					System.out.print(rs.getObject(i)+"\t");
				System.out.println();
			}

			rs.close();
			pstmt.close();
			conn.close();

		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

	}
}
