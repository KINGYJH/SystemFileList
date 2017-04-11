import java.io.Serializable;

/**
 * 文件描述
 * Created by YJH on 2017/4/11.
 */
public class FileDescribe implements Serializable {

    private String id;
    private String fileName;
    private String filePath;
    private String fileOrDir;
    private String fileSize;
    private String fileLastUpdateTime;
    private FileDescribe parent;
    private String fileRoot;

    public FileDescribe() {
        super();
    }

    public FileDescribe(String id, String fileName, String filePath, String fileOrDir, String fileSize, String fileLastUpdateTime, FileDescribe parent,String fileRoot) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileOrDir = fileOrDir;
        this.fileSize = fileSize;
        this.fileLastUpdateTime = fileLastUpdateTime;
        this.parent = parent;
        this.fileRoot = fileRoot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileOrDir() {
        return fileOrDir;
    }

    public void setFileOrDir(String fileOrDir) {
        this.fileOrDir = fileOrDir;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileLastUpdateTime() {
        return fileLastUpdateTime;
    }

    public void setFileLastUpdateTime(String fileLastUpdateTime) {
        this.fileLastUpdateTime = fileLastUpdateTime;
    }

    public FileDescribe getParent() {
        return parent;
    }

    public void setParent(FileDescribe parent) {
        this.parent = parent;
    }

    public String getFileRoot() {
        return fileRoot;
    }

    public void setFileRoot(String fileRoot) {
        this.fileRoot = fileRoot;
    }
}
