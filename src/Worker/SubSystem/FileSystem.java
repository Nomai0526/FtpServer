package Worker.SubSystem;

public class FileSystem {
    private String root;
    private String currDirectory;
    private String fileSeparator = "/";

    public FileSystem(){
        //获取的是该文件所在的目录
        //应该把这个目录修改为项目下的Files文件夹
        this.currDirectory = System.getProperty("user.dir") + "/test";
        //获取的是该文件所在的目录
        this.root = System.getProperty("user.dir");
    }
}
