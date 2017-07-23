import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class GetMyFiles {

    static Properties props;


    public boolean startFTP() throws IOException {
        final String DIR1 = "C:/LocalFolder/order";

        final String DIR2 = "C:/LocalFolder/desadv";

        FileInputStream fis = new FileInputStream("src/main/resources/config.properties");

        props = new Properties();

        try {

            props.load(fis);

            String serverAddress = props.getProperty("serverAddress").trim();
            String userId = props.getProperty("userId").trim();
            String password = props.getProperty("password").trim();
            String remoteDirectory = props.getProperty("remoteDirectory").trim();
            String localDirectory = props.getProperty("localDirectory").trim();


            FTPClient ftp = new FTPClient();

            ftp.connect(serverAddress);

            if (!ftp.login(userId, password)) {
                ftp.logout();
                return false;
            }
            int reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }


            ftp.enterLocalPassiveMode();

            System.out.println("Remote system is " + ftp.getSystemType());

            ftp.changeWorkingDirectory(remoteDirectory);
            System.out.println("Current directory is " + ftp.printWorkingDirectory());

            FTPFile[] ftpFiles = ftp.listFiles();

            if (ftpFiles != null && ftpFiles.length > 0) {

                for (FTPFile file : ftpFiles) {
                    if (!file.isFile()) {
                        continue;
                    }

                    System.out.println("File is " + file.getName());


                    String fileName = file.getName();
                    int i = fileName.indexOf('_');

                    SimpleDateFormat newDate = new SimpleDateFormat("YYYYMMddhhmmss");

                    String curTime = newDate.format(new Date());


                    if (!(fileName.substring(0, i).equals("done"))) {
                        if (fileName.substring(0, i).equals("order")) {
                            final File dir1 = new File(DIR1);
                            if (!dir1.exists()) {
                                if (dir1.mkdir()) {
                                    System.out.println("Каталог " + dir1.getAbsolutePath()
                                            + " успешно создан.");
                                } else {
                                    System.out.println("Каталог " + dir1.getAbsolutePath()
                                            + " создвть не удалось.");
                                }
                            } else {
                                System.out.println("Каталог " + dir1.getAbsolutePath()
                                        + " уже существует.");
                            }

                            OutputStream output;
                            output = new FileOutputStream(dir1 + "/" + file.getName());
                            ftp.retrieveFile(file.getName(), output);
                            output.close();

                            ftp.setModificationTime(fileName, curTime);
                            ftp.rename(fileName, "done_" + fileName);


                        } /*else if (fileName.substring(0, i).equals("desadv")) {

                            final File dir2 = new File(DIR2);
                            if (!dir2.exists()) {
                                if (dir2.mkdirs()) {
                                    System.out.println("Каталог " + dir2.getAbsolutePath()
                                            + " успешно создан.");
                                } else {
                                    System.out.println("Каталог " + dir2.getAbsolutePath()
                                            + " создвть не удалось.");
                                }
                            } else {
                                System.out.println("Каталог " + dir2.getAbsolutePath()
                                        + " уже существует.");
                            }

                            OutputStream output;
                            output = new FileOutputStream(dir2 + "/" + file.getName());


                            ftp.retrieveFile(file.getName(), output);

                            output.close();
                            ftp.rename(fileName, "done_" + fileName);
                            ftp.setModificationTime(fileName, curTime);*/


                    } else /*if (Long.valueOf(curTime) - Long.valueOf(ftp.getModificationTime(fileName)) > 1)*/ {
                        ftp.deleteFile(fileName);
                    }

                }

            }






            ftp.logout();
            ftp.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

   static Thread run = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    GetMyFiles getMyFiles = new GetMyFiles();

                    getMyFiles.startFTP();
                    Thread.sleep(10000); //1000 - 1 сек
                } catch (InterruptedException ex) {
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });


    public static void main(String[] args) throws IOException {
        run.start();

/*        GetMyFiles getMyFiles = new GetMyFiles();

        getMyFiles.startFTP();*/
    }
}