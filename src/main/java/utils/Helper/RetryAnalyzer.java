package utils.Helper;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.report.LogsUtils;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 2; // عدد المحاولات بعد الفشل الأول

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            System.out.println("Retry OF Excution of the Falied Test Named :"+result.getName());
            System.out.println("Retry number :"+retryCount);
            LogsUtils.warn("Retry OF Excution of the Falied Test Named :",result.getName());
            return true;
        }
        return false;
    }
}