import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库连接
 * Created by YJH on 2017/3/31.
 */
public class DBUtil {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";

    static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException se) {
            System.out.println("数据库连接失败！");
            se.printStackTrace();
        }
        return con;
    }

    public static void testEx() {
        String sql = "SELECT * FROM tb_file_describe";
        Connection conn = getConnection();
        try {
            conn.createStatement().execute(sql);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int inster(List<FileDescribe> beens) throws SQLException {
        String sql = "insert into tb_file_describe values(?,?,?,?,?,?,?,?)";
        Connection connection = getConnection();
        connection.setAutoCommit(false);
        PreparedStatement ps = connection.prepareStatement(sql);
        for (FileDescribe item : beens) {
            ps.setString(1, item.getId());
            ps.setString(2, item.getFileName());
            ps.setString(3, item.getFilePath());
            ps.setString(4, item.getFileOrDir());
            ps.setString(5, item.getFileSize());
            ps.setString(6, item.getFileLastUpdateTime());
            ps.setString(7, item.getParent().getId());
            ps.setString(8, item.getFileRoot());
            ps.addBatch();
        }

        int[] sussce = ps.executeBatch();
        if (sussce.length > 0) {
            connection.commit();
            connection.setAutoCommit(true);
            connection.close();
            return 1;
        }
        return 0;
    }
}
