import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmssSSS");

    public static Path takeScreenshot(WebDriver driver, String name) throws Exception {
        if (!(driver instanceof TakesScreenshot)) {
            throw new IllegalArgumentException("Driver does not support screenshots");
        }
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Path dir = Paths.get("target", "screenshots");
        Files.createDirectories(dir);
        String file = name + "-" + LocalDateTime.now().format(TS) + ".png";
        Path dest = dir.resolve(file);
        Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
        return dest;
    }
}