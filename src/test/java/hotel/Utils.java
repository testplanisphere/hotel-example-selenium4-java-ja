package hotel;

import java.util.ArrayList;
import java.util.Collection;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utils {

  public static final String BASE_URL = "https://hotel.testplanisphere.dev/ja";

  private Utils() {
    throw new AssertionError();
  }

  public static WebDriver createWebDriver() {
    var githubActions = Boolean.parseBoolean(System.getenv("GITHUB_ACTIONS"));
    var options = new ChromeOptions();
    if (githubActions) {
      options.addArguments("--headless");
    }
    var driver = new ChromeDriver(options);
    driver.manage().window().setSize(new Dimension(1920, 1080));
    return driver;
  }

  public static String getNewWindowHandle(Collection<String> handlesBeforeOpen, Collection<String> handlesAfterOpen) {
    var handles = new ArrayList<>(handlesAfterOpen);
    handles.removeAll(handlesBeforeOpen);
    if (handles.isEmpty()) {
      throw new RuntimeException("新しいウィンドウが見つかりません");
    } else if (handles.size() > 1) {
      throw new RuntimeException("新しいウィンドウが複数あります");
    } else {
      return handles.get(0);
    }
  }

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ign) {
      // ignore
    }
  }
}
