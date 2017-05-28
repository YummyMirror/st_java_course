package ru.anatoli.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by anatoli.anukevich on 5/28/2017.
 */
public class FtpHelper {
    private FTPClient ftpClient;
    private ApplicationManager applicationManager;

    public FtpHelper(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        ftpClient = new FTPClient();
    }

    public void uploadFile(String fileToUpload, String nameOfTargetFile, String nameOfBackupFile) throws IOException {
        ftpClient.connect("localhost");
        ftpClient.login("mantis", "mantis");
        ftpClient.deleteFile(nameOfBackupFile);
        ftpClient.rename(nameOfTargetFile, nameOfBackupFile);
        ftpClient.enterLocalPassiveMode();
        File file = new File(fileToUpload);
        FileInputStream fileInputStream = new FileInputStream(file);
        ftpClient.storeFile(nameOfBackupFile, fileInputStream);
        ftpClient.disconnect();
    }

    public void restore(String nameOfBackupFile, String nameOfTargetFile) throws IOException {
        ftpClient.connect("localhost");
        ftpClient.login("mantis", "mantis");
        ftpClient.deleteFile(nameOfTargetFile);
        ftpClient.rename(nameOfBackupFile, nameOfTargetFile);
        ftpClient.disconnect();
    }
}
