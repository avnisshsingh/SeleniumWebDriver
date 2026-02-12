package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

    private Properties prop;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    // ===================== CHROME =====================
    public ChromeOptions getChromeOptions() {

        ChromeOptions options = new ChromeOptions();

        // ---- CI / Docker Stability Flags ----
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // Headless Mode
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }

        // Incognito Mode
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            options.addArguments("--incognito");
            options.setExperimentalOption(
                    "excludeSwitches",
                    new String[]{"enable-automation"});
        }

        // Remote Execution (Docker Grid)
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {

            String browserVersion = prop.getProperty("browserVersion");

            if (browserVersion != null && !browserVersion.trim().isEmpty()) {
                options.setBrowserVersion(browserVersion);
            }

            options.setPlatformName("linux");

            // ✅ W3C Compliant Grid Capabilities
            options.setCapability("se:vncEnabled", true);
            options.setCapability(
                    "se:name",
                    "Opencart Automation : " + prop.getProperty("testname"));
        }

        return options;
    }

    // ===================== FIREFOX =====================
    public FirefoxOptions getFirefoxOptions() {

        FirefoxOptions options = new FirefoxOptions();

        // Headless
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            options.addArguments("--headless");
        }

        // Private Mode
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            options.addArguments("-private");
        }

        // Remote Execution
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {

            String browserVersion = prop.getProperty("browserVersion");

            if (browserVersion != null && !browserVersion.trim().isEmpty()) {
                options.setBrowserVersion(browserVersion);
            }

            options.setPlatformName("linux");

            // ✅ W3C Compliant Grid Capabilities
            options.setCapability("se:vncEnabled", true);
            options.setCapability(
                    "se:name",
                    "Opencart Automation : " + prop.getProperty("testname"));
        }

        return options;
    }

    // ===================== EDGE =====================
    public EdgeOptions getEdgeOptions() {

        EdgeOptions options = new EdgeOptions();

        // CI / Docker Stability Flags
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // Headless
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }

        // InPrivate Mode
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            options.addArguments("--inprivate");
            options.setExperimentalOption(
                    "excludeSwitches",
                    new String[]{"enable-automation"});
        }

        // Remote Execution
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {

            String browserVersion = prop.getProperty("browserVersion");

            if (browserVersion != null && !browserVersion.trim().isEmpty()) {
                options.setBrowserVersion(browserVersion);
            }

            options.setPlatformName("linux");

            // ✅ W3C Compliant Grid Capabilities
            options.setCapability("se:vncEnabled", true);
            options.setCapability(
                    "se:name",
                    "Opencart Automation : " + prop.getProperty("testname"));
        }

        return options;
    }
}
