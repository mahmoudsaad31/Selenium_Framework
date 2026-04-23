package utils.Helper;

import utils.report.LogsUtils;

public class TerminalUtils {

    //allure, generate, //path ,-o ,//path ,--single-file
    //allure generate "+ //path + " -o "+ //path +" --single-file
    public static void executeCommand(String... command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.inheritIO(); // Redirect input/output to the current process
            Process process = processBuilder.start();
            process.waitFor();
            LogsUtils.info("Command executed successfully: " + String.join(" ", command));
        } catch (Exception e) {
            LogsUtils.error("Failed to execute command: " + e.getMessage());
        }
    }

}
