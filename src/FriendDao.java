/*
import java.sql.*;
import java.util.ArrayList;

public class FriendDao
{
	DbConnect db = new DbConnect();
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	FriendDto fdto = new FriendDto();


	public FriendDao()
	{
		try
		{
			Class.forName("com.oracle.database.jdbc");
		}
		catch (Exception e)
		{
			System.out.println("드라이버 로드 오류 : "+e.getMessage());
		}
	}

	public ArrayList<FriendDao> friendSelect()
	{
		ArrayList<FriendDao> resultList = new ArrayList<>();

		try
		{
			con = db.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from friend");

			System.out.println("정상 연결");

			while (rs.next())
			{
				int num = rs.getInt("num");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String addr = rs.getString("addr");




			}
		}
		catch (SQLException e)
		{
			System.out.println("con 오류 : "+e.getMessage());
			throw new RuntimeException(e);
		}finally
		{
			db.dbClose(rs,stmt,con);
		}

		return resultList;
	}
}
*/
