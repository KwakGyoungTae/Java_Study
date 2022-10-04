import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 여러 클래스에서 필요시마다 호출할 DB 클래스
public class DbConnect {

	// url
	static final String ORACLE_LOCAL = "jdbc:oracle:thin:@localhost:1521:kwak";

	public static void main(String[] args)
	{
		DbConnect db=new DbConnect();
		db.getConnect();
		System.out.println("마침");
	}


	// Connection
	public Connection getConnect() {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(ORACLE_LOCAL, "kwak", "1234");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패:" + e.getMessage());
		}
		return conn;
	}

	// close..select..완성형sql(Statement)
	public void dbClose(ResultSet rs, Statement stmt, Connection conn) {

		try {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// close >> 완성형 sql(Statement)
	public void dbClose(Statement stmt, Connection conn) {
		try {
			if (stmt!=null)
				stmt.close();
			if (conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// close >> select.. 미완성형 sql(PrepareStatement)
	public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs!=null)
				rs.close();
			if (pstmt!=null)
				pstmt.close();
			if (conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// close >> 미완성형 sql(PrepareStatement)
	public void dbClose(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt!=null)
				pstmt.close();
			if (conn!=null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}