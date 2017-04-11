import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    private static List<FileDescribe> files = new ArrayList<>();

    public static void main(String[] args) {

        DBUtil.getConnection();
        DBUtil.testEx();
        System.out.println("数据库连接成功");
        long startTime = System.currentTimeMillis();
        File[] rootFiles = File.listRoots();
        for (File item : rootFiles) {
            getFile(item.getPath(), new FileDescribe());
        }
        System.out.println("文件个数:" + files.size());
        long endTime = System.currentTimeMillis();
        System.out.println("获取文件运行时间:" + (endTime - startTime) + "ms");


        startTime = System.currentTimeMillis();
        try {
            DBUtil.inster(files);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        System.out.println("数据入库运行时间:" + (endTime - startTime) + "ms");
    }

    public static void getFile(String filePath, FileDescribe fileDescribe) {
        File file = new File(filePath);
        if (file.exists()) {
            File[] fileList = file.listFiles();
            if (null == fileList) {
                return;
            }
            if (fileList.length == 0) {
                return;
            } else {
                for (File item : fileList) {
                    FileDescribe been = new FileDescribe();
                    been.setId(UUID.randomUUID().toString());
                    been.setFileName(item.getName());
                    been.setFilePath(item.getPath());
                    been.setFileLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.lastModified()));
                    been.setParent(fileDescribe);
                    been.setFileRoot(getFileRootName(item.getPath()));
                    if (item.isDirectory()) {
                        been.setFileOrDir("文件夹");
                        getFile(item.getPath(), been);
                    } else {
                        been.setFileSize(getFileSize(item.length()));
                        been.setFileOrDir("文件");
                    }
                    files.add(been);
                }
            }
        }

    }

    private static String getFileSize(long size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte(s)";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    private static String getFileRootName(String filePath) {
        if (filePath.startsWith("/")) {
            return "/";
        } else {
            return filePath.substring(0, 2);
        }
    }
}
