package com.macs.group6.daldiscussion;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final String CONFIG_PATH = "src/main/resources/application.properties";

    private static final String PROP_SMTP_HOST = "smtp.host";
    private static final String ENV_SMTP_HOST = "SMTP_HOST";

    private static final String PROP_SMTP_PORT = "smtp.port";
    private static final String ENV_SMTP_PORT = "SMTP_PORT";
    private static final int DEF_SMTP_PORT = 2525;

    private static final String PROP_SMTP_USERNAME = "smtp.username";
    private static final String ENV_SMTP_USERNAME = "SMTP_USERNAME";

    private static final String PROP_SMTP_PASSWORD = "smtp.password";
    private static final String ENV_SMTP_PASSWORD = "SMTP_PASSWORD";

    private static final String PROP_SMTP_FROM_EMAIL = "smtp.from.email";
    private static final String ENV_SMTP_FROM_EMAIL = "SMTP_FROM_EMAIL";

    private static final String PROP_SMTP_FROM_NAME = "smtp.from.name";
    private static final String ENV_SMTP_FROM_NAME = "SMTP_FROM_NAME";

    private static final String PROP_WEB_URL = "web.url";
    private static final String ENV_WEB_URL = "WEB_URL";

    private static final String PROP_RESET_PASSWORD_URL = "reset_password.url";
    private static final String ENV_RESET_PASSWORD_URL = "RESET_PASSWORD_URL";

    private static final String PROP_SECURITY_CONFIG = "security.config";
    private static final String ENV_SECURITY_CONFIG = "SECURITY_CONFIG";

    private static AppConfig __instance;

    public String smtpHost;
    public int smtpPort;
    public String smtpUsername;
    public String smtpPassword;
    public String smtpFromEmail;
    public String smtpFromName;
    public String webUrl;
    public String resetPasswordUrl;
    public String securityConfig;

    public static AppConfig instance() {
        if (__instance == null) {
            __instance = new AppConfig();
            __instance.load();
        }
        return __instance;
    }

    public AppConfig load() {
        try {
            InputStream inputStream = new FileInputStream(CONFIG_PATH);
            Properties properties = new Properties();
            properties.load(inputStream);

            smtpHost = readString(PROP_SMTP_HOST, ENV_SMTP_HOST, properties);
            smtpPort = readInteger(PROP_SMTP_PORT, ENV_SMTP_PORT, properties, DEF_SMTP_PORT);
            smtpUsername = readString(PROP_SMTP_USERNAME, ENV_SMTP_USERNAME, properties);
            smtpPassword = readString(PROP_SMTP_PASSWORD, ENV_SMTP_PASSWORD, properties);
            smtpFromEmail = readString(PROP_SMTP_FROM_EMAIL, ENV_SMTP_FROM_EMAIL, properties);
            smtpFromName = readString(PROP_SMTP_FROM_NAME, ENV_SMTP_FROM_NAME, properties);

            webUrl = readString(PROP_WEB_URL, ENV_WEB_URL, properties);
            resetPasswordUrl = readString(PROP_RESET_PASSWORD_URL, ENV_RESET_PASSWORD_URL, properties);

            securityConfig = readString(PROP_SECURITY_CONFIG, ENV_SECURITY_CONFIG, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

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
