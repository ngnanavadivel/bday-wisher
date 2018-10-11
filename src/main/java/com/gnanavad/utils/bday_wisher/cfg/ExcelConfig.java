package com.gnanavad.utils.bday_wisher.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExcelConfig {

    private static final String      EXCEL_CONFIG_PROPERTIES = "./excel-config.properties";

    private Properties               props                   = new Properties();

    private static final ExcelConfig _instance               = new ExcelConfig();

    private ExcelConfig() {
        InputStream stream = null;
        try {
            stream = new FileInputStream(new File(EXCEL_CONFIG_PROPERTIES));
            if (stream != null) {
                props.load(stream);
            }
        } catch (IOException e) {
            System.out.println("ERROR: Failed to load excel sheet configuration file : " + EXCEL_CONFIG_PROPERTIES);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // Just swallow it ;)
                }
            }
        }
    }

    public Properties getProps() {
        return props;
    }

    public static String getProperty(String key) {
        return getInstance().getProps()
                            .getProperty(key);
    }

    public static String getExcelSheetFileName() {
        return getProperty(KeyConstants.EXCEL_SHEET_FILE_NAME);
    }

    public static String getMailerEmailId() {
        return getProperty(KeyConstants.MAILER_EMAIL_ID);
    }

    public static String getMailerEncodedPassword() {
        return getProperty(KeyConstants.MAILER_ENCODED_PASSWORD);
    }

    public static Integer getWorkSheetIndex() {
        return getIntegerFromString(KeyConstants.WORKSHEET_INDEX);
    }

    public static Integer getNameColumnIndex() {
        return getIntegerFromString(KeyConstants.NAME_CELL_INDEX);
    }

    public static Integer getBatchColumnIndex() {
        return getIntegerFromString(KeyConstants.BATCH_CELL_INDEX);
    }

    public static Integer getBranchCodeColumnIndex() {
        return getIntegerFromString(KeyConstants.BRANCH_CODE_CELL_INDEX);
    }

    public static Integer getDOBColumnIndex() {
        return getIntegerFromString(KeyConstants.DOB_CELL_INDEX);
    }

    public static Integer getEmailColumnIndex() {
        return getIntegerFromString(KeyConstants.EMAIL_CELL_INDEX);
    }

    public static Integer getStudentPhoneNumberColumnIndex() {
        return getIntegerFromString(KeyConstants.PHONE_NUMBER_CELL_INDEX);
    }

    private static Integer getIntegerFromString(String key) {
        String index = getProperty(key);
        if (index != null) {
            return Integer.parseInt(index);
        }
        System.out.println("ERROR: Property '" + key + "' is empty!");
        return null;
    }

    public static ExcelConfig getInstance() {
        return _instance;
    }

}
