package utils;

public class Constants {

    //относительный путь, будет работать на любой машине
    public static final String CONFIGURATION = System.getProperty("user.dir")+ "/src/test/resources/config/config.properties";
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;
    public static final String TESTDATA_FILEPATH = System.getProperty("user.dir")+ "/src/test/resources/testdata/TESTNGdata.xlsx";
    public static final String SCREENSHOT_FILEPATH = System.getProperty("user.dir")+ "/screenshots/";

}
