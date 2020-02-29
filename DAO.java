import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class DAO
{
    public static String fileName = "test.db";
    public static String url1 = "jdbc:sqlite:" + Paths.get(".").toAbsolutePath().normalize().toString() +"\\db\\" + fileName;

    private static synchronized Connection connect()
    {

        String url = url1 + fileName;
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static synchronized void createTables()
    {

        String sql = "CREATE TABLE IF NOT EXISTS user (\n"
                + "    id INTEGER PRIMARY KEY,\n"
                + "    name char (20),\n"
                + "    second_name char (20),\n"
                + "    email char (40)\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement())
        {
            stmt.execute(sql);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static synchronized boolean add_user(String name, String second_name, String email)
    {
        String sql = "INSERT INTO user(name, second_name, email) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, name);
            pstmt.setString(2, second_name);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public static synchronized boolean add_user_list(ArrayList<User> user_list)
    {
        String sql = "INSERT INTO user(name, second_name, email) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            conn.setAutoCommit(false);
            for(int i = 0; i < user_list.size(); i++)
            {
                pstmt.setString(1, user_list.get(i).name);
                pstmt.setString(2, user_list.get(i).second_name);
                pstmt.setString(3, user_list.get(i).email);
                pstmt.addBatch();
            }

            int[] result = pstmt.executeBatch();
            conn.commit();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
