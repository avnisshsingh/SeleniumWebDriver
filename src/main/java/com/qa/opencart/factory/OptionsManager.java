package com.qa.opencart.factory;

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

        // ----- Mandatory for Jenkins / CI -----
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // Headless
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }

        // Incognito
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            options.addArguments("--incognito");
            options.setExperimentalOption(
                    "excludeSwitches",
                    new String[] { "enable-automation" });
        }

        // Remote execution (Grid / Docker)
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {

            String browserVersion = prop.getProperty("browserVersion");

            if (browserVersion != null && !browserVersion.trim().isEmpty()) {
                options.setBrowserVersion(browserVersion);
            }

            options.setPlatformName("linux");
            options.setCapability("enableVNC", true);
            options.setCapability(
                    "name",
                    "OpenAppTest - " + prop.getProperty("testname"));
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

        // Private mode
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            options.addArguments("-private");
        }

        // Remote execution
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {

            String browserVersion = prop.getProperty("browserVersion");

            if (browserVersion != null && !browserVersion.trim().isEmpty()) {
                options.setBrowserVersion(browserVersion);
            }

            options.setPlatformName("linux");
            options.setCapability("enableVNC", true);
        }

        return options;
    }

    // ===================== EDGE =====================
    public EdgeOptions getEdgeOptions() {

        EdgeOptions options = new EdgeOptions();

        // Jenkins / CI flags
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        // Headless
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }

        // InPrivate (NOT incognito)
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            options.addArguments("--inprivate");
            options.setExperimentalOption(
                    "excludeSwitches",
                    new String[] { "enable-automation" });
        }

        return options;
    }
}