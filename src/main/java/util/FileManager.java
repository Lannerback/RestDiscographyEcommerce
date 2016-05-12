package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileManager {

    static final Logger logger = Logger.getLogger(FileManager.class);

    private String path;
    private MultipartFile image;
    private String subPath;

    public FileManager() {
    }

    public FileManager(MultipartFile image, String subPath) {
        this.image = image;
        this.subPath = subPath;
    }

    public FileManager(String path, MultipartFile image, String subPath) {
        this.path = path;
        this.image = image;
        this.subPath = subPath;
    }

    public void saveFile() throws FileAlreadyExistsException, IOException {
        try {
            image.transferTo(new File(path + subPath));
        } catch (FileAlreadyExistsException e) {
            logger.error(e);
            throw e;
        } catch (IOException e) {
            logger.error(e);
            throw e;
        }
    }

    public void saveFile(MultipartFile image, String subPath) throws FileAlreadyExistsException, IOException {
        try {
            image.transferTo(new File(path + subPath));
        } catch (FileAlreadyExistsException e) {
            logger.error(e);
            throw e;
        } catch (IOException e) {
            logger.error(e);
            throw e;
        }
    }

    public void makeDir(String subPath) throws SecurityException {
        File imageDir = new File(path + subPath);
        //if (!imageDir.exists()) {
        try {
            imageDir.mkdir();
            logger.info("Directory created into: " + imageDir.getPath());
        } catch (SecurityException e) {
            logger.error(e);
            throw e;
        }

        /*}else {
            logger.info("Directory already exist");
        }  */
    }

    public void deleteDirOrFile(String filePath)throws FileNotFoundException {
        javax.swing.JOptionPane.showMessageDialog(null,filePath);

        File file = new File(path + filePath );
        if(!file.exists())
            throw new FileNotFoundException();
        if(file.isDirectory()){
        File[] files = file.listFiles();     
        if(null!=files){
            for(int i=0; i<files.length; i++) {
                files[i].delete();
                /*
                if(files[i].isDirectory()) {
                   deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }*/
                }
            }        
        }
            file.delete();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

}
