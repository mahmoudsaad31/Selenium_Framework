package utils.Helper;

import org.apache.commons.io.FileUtils;
import utils.report.LogsUtils;

import java.io.File;
import java.io.IOException;

public class FilesUtils {
    private FilesUtils() {
        super();
    }


    // method to get the latest file in logs to attach to allure report
    public static File getLatestFile(String dirPath) {
        File folder = new File(dirPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) { // if there is no files in directory
            LogsUtils.warn("No files found in the directory:" + dirPath);
            return null;
        }
        File latestFile = files[0];
        for (File file : files) { // for loop for listing all files
            if (file.lastModified() > latestFile.lastModified()) { // get last modified file
                latestFile = file;
            }
        }
        return latestFile;

    }

    // method to remove all files in a directory used to remove old allure results and logs and screenshots
    public static boolean cleanFiles(File file) {
        try {
            if (file.exists() ) {
                FileUtils.cleanDirectory(file);
                LogsUtils.info("Cleaned directory successfully: " + file.getPath());
                return true;
            } else {
                LogsUtils.info("Directory does not exist or is not a directory: " + file.getPath());
            }
        } catch (IOException e) {
            LogsUtils.warn("Could not clean directory (maybe it's in use?): " + file.getPath());
        }
        return false;
    }

    public static void renameFile(File oldName, File newName) {
        try {
            File targetFile = oldName.getParentFile().getAbsoluteFile();
            String targetDirectory = targetFile + File.separator + newName;
            FileUtils.copyFile(oldName, new File(targetDirectory));
            FileUtils.deleteQuietly(oldName);
            LogsUtils.info("Target File Path: \"" + oldName.getPath() + "\", file was renamed to \"" + newName.getName() + "\".");
        } catch (Exception e) {
            LogsUtils.error("Failed to rename file: " + e.getMessage());
        }
    }

}
