import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.derby.drda.NetworkServerControl;

public class JavaDBTest {
	public static void main(String[] args) {
		netDB();
	}

	private static void netDB() {
		String driver = "org.apache.derby.jdbc.ClientDriver";// ??derbyclient.jar????

		String dbName = "NetworkDB";

		String connectionURL = "jdbc:derby://localhost:1527/" + dbName + ";create=true";

		try {

			/*
			 * 
			 * ????Derby?????????,???????1527,????????????
			 * 
			 * <Derby_Home>/frameworks/NetworkServer/bin/startNetworkServer.bat
			 * 
			 * ????????????Derby?????????,?????Unix,??startNetworkServer.ksh
			 * 
			 */

			NetworkServerControl derbyServer = new NetworkServerControl();// NetworkServerControl????derbynet.jar????

			PrintWriter pw = new PrintWriter(System.out);// ??????????Derby?????????

			derbyServer.start(pw);// ????Derby??????

			Class.forName(driver);

			DriverManager.getConnection(connectionURL);

			// do something

			derbyServer.shutdown();// ???Derby??????

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}

	private static void localDB() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			System.out.println("Loaded the EmbeddedDriver");
			Connection conn = null;
			Properties props = new Properties();
			props.setProperty("user", "user");
			props.setProperty("password", "password");
			try {
				conn = DriverManager.getConnection("jdbc:derby:helloDB;create=true", props);
				System.out.println("create derbyDB");
				conn.setAutoCommit(false);

				Statement statement = conn.createStatement();
				// statement.execute("drop table user_uer");
				System.out.println("create table user_uer");
				statement.execute("create table user_uer ( name varchar(20), score int)");
				statement.execute("insert into user_uer ( name,score) values ('С??',89)");
				statement.execute("insert into user_uer (name ,score) values ('С??',90)");
				ResultSet rSet = statement.executeQuery("select name,score from user_uer");
				System.out.println("------------------------");
				while (rSet.next()) {
					System.out.println(rSet.getString("name"));
					System.out.println(rSet.getInt("score"));
				}
				System.out.println("query user_uer data");
				rSet.close();
				statement.close();
				conn.commit();
				conn.close();
				DriverManager.getConnection("jdbc:derby:helloDB;shutDown=true");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
