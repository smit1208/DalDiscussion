package com.macs.group6.daldiscussion;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class contains configuration for application.
 * @author Kush Rao
 */
public class AppConfig {
    /**
     * Resource path to application.properties file
     */
    private static final String CONFIG_PATH = "src/main/resources/application.properties";

    /**
     * Property name of SMTP host
     */
    private static final String PROP_SMTP_HOST = "smtp.host";
    /**
     * Enviroment variable name of SMTP host
     */
    private static final String ENV_SMTP_HOST = "SMTP_HOST";

    /**
     * Property name of SMTP port
     */
    private static final String PROP_SMTP_PORT = "smtp.port";
    /**
     * Environment name of SMTP port
     */
    private static final String ENV_SMTP_PORT = "SMTP_PORT";
    /**
     * Default SMTP port
     */
    private static final int DEF_SMTP_PORT = 2525;

    /**
     * Property name of SMTP username
     */
    private static final String PROP_SMTP_USERNAME = "smtp.username";
    /**
     * Environment variable name of SMTP username
     */
    private static final String ENV_SMTP_USERNAME = "SMTP_USERNAME";

    /**
     * Property name of SMTP password
     */
    private static final String PROP_SMTP_PASSWORD = "smtp.password";
    /**
     * Environment variable name of SMTP password
     */
    private static final String ENV_SMTP_PASSWORD = "SMTP_PASSWORD";

    /**
     * Property name of SMTP sender email
     */
    private static final String PROP_SMTP_FROM_EMAIL = "smtp.from.email";
    /**
     * Environment variable name of SMTP sender email
     */
    private static final String ENV_SMTP_FROM_EMAIL = "SMTP_FROM_EMAIL";

    /**
     * Property name of SMTP sender name
     */
    private static final String PROP_SMTP_FROM_NAME = "smtp.from.name";
    /**
     * Environment name of SMTP sender name
     */
    private static final String ENV_SMTP_FROM_NAME = "SMTP_FROM_NAME";

    /**
     * Property name of URL of this web application
     */
    private static final String PROP_WEB_URL = "web.url";
    /**
     * Environment variable name of URL of this web application
     */
    private static final String ENV_WEB_URL = "WEB_URL";

    /**
     * Property name of URL of resetting password page
     */
    private static final String PROP_RESET_PASSWORD_URL = "reset_password.url";
    /**
     * Environment name of URL of resetting password page
     */
    private static final String ENV_RESET_PASSWORD_URL = "RESET_PASSWORD_URL";

    /**
     * Property name of flag if Spring Boot security must be configured or not
     */
    private static final String PROP_SECURITY_CONFIG = "security.config";
    /**
     * Environment variable name of flag if Spring Boot security must be configured or not
     */
    private static final String ENV_SECURITY_CONFIG = "SECURITY_CONFIG";

    private static AppConfig __instance;

    private String _smtpHost;
    private int _smtpPort;
    private String _smtpUsername;
    private String _smtpPassword;
    private String _smtpFromEmail;
    private String _smtpFromName;
    private String _webUrl;
    private String _resetPasswordUrl;
    private String _securityConfig;

    /**
     * Singleton implementation for AppConfig
     * @return AppConfig instance
     */
    public static AppConfig getInstance() {
        if (__instance == null) {
            __instance = new AppConfig();
            __instance.load();
        }
        return __instance;
    }

    /**
     * Get SMTP host used for sending email in application.
     * @return a SMTP host
     */
    public String getSmtpHost() {
        return _smtpHost;
    }

    /**
     * Get SMTP port used for sending email in application.
     * @return a SMTP port
     */
    public int getSmtpPort() {
        return _smtpPort;
    }

    /**
     * Get SMTP username used for sending email in application.
     * @return a SMTP username
     */
    public String getSmtpUsername() {
        return _smtpUsername;
    }

    /**
     * Get SMTP password used for sending email in application.
     * @return a SMTP password
     */
    public String getSmtpPassword() {
        return _smtpPassword;
    }

    /**
     * Get SMTP sender email used for sending email in application.
     * @return a SMTP sender email
     */
    public String getSmtpFromEmail() {
        return _smtpFromEmail;
    }

    /**
     * Get SMTP sender name used for sending email in application.
     * @return a SMTP sender name
     */
    public String getSmtpFromName() {
        return _smtpFromName;
    }

    /**
     * Get URL of this web application used for generating URLs inside application.
     * @return a URL of this web application
     */
    public String getWebUrl() {
        return _webUrl;
    }

    /**
     * Get URL of resetting password page.
     * @return a URL for resetting password
     */
    public String getResetPasswordUrl() {
        return _resetPasswordUrl;
    }

    /**
     * Get if Spring Boot security must be configured or not.
     * @return a true if Spring Boot security must be configured, a false if default security must be configured
     */
    public boolean getSecurityConfig() {
        return "true".equalsIgnoreCase(_securityConfig) || "yes".equalsIgnoreCase(_securityConfig);
    }

    /**
     * Load configuration from application.properties file.
     * @return a AppConfig instance
     */
    public AppConfig load() {
        try {
            InputStream inputStream = new FileInputStream(CONFIG_PATH);
            Properties properties = new Properties();
            properties.load(inputStream);

            _smtpHost = readString(PROP_SMTP_HOST, ENV_SMTP_HOST, properties);
            _smtpPort = readInteger(PROP_SMTP_PORT, ENV_SMTP_PORT, properties, DEF_SMTP_PORT);
            _smtpUsername = readString(PROP_SMTP_USERNAME, ENV_SMTP_USERNAME, properties);
            _smtpPassword = readString(PROP_SMTP_PASSWORD, ENV_SMTP_PASSWORD, properties);
            _smtpFromEmail = readString(PROP_SMTP_FROM_EMAIL, ENV_SMTP_FROM_EMAIL, properties);
            _smtpFromName = readString(PROP_SMTP_FROM_NAME, ENV_SMTP_FROM_NAME, properties);

            _webUrl = readString(PROP_WEB_URL, ENV_WEB_URL, properties);
            _resetPasswordUrl = readString(PROP_RESET_PASSWORD_URL, ENV_RESET_PASSWORD_URL, properties);

            _securityConfig = readString(PROP_SECURITY_CONFIG, ENV_SECURITY_CONFIG, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Get Integer value of property from environment variable or from application.properties
     * @param propName is name of property from application.properties
     * @param envName is name of environment variable used to read value
     * @param properties is Properites instance which is read from application.properties
     * @param defaultValue is default value used when there is no values from environment variable or from application.properties
     * @return a Integer value of property
     */
    private int readInteger(String propName, String envName, Properties properties, int defaultValue) {
        try {
            String valueString = readString(propName, envName, properties);
            int value = Integer.parseInt(valueString);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Get String value of property from environment variable or from application.properties
     * @param propName is name of property from application.properties
     * @param envName is name of environment variable used to read value
     * @param properties is Properites instance which is read from application.properties
     * @return a String value of property
     */
    private String readString(String propName, String envName, Properties properties) {
        if (System.getenv().containsKey(envName)) {
            String envValue = System.getenv(envName).trim();
            if (envValue.length() > 0) {
                return envValue;
            }
        }
        if (properties.containsKey(propName)) {
            return properties.getProperty(propName);
        }
        return "";
    }
}
