package com.studentcrud.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseProp { //리소스 파일 읽어오는거
    private String url;
    private String userName;
    private String password;

    private static DatabaseProp databaseProp;

    public static DatabaseProp getInstance() {
        if (databaseProp == null) databaseProp = new DatabaseProp();

        return databaseProp;
    }

    private DatabaseProp() {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources/Mysql.properties");
        try {
            if (inputStream != null) {
                prop.load(inputStream);
            }else {
                throw new FileNotFoundException("프로퍼티 파일 " + "Mysql.properties" + "'을 resource에서 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.url = prop.getProperty("url");
        this.userName = prop.getProperty("userName");
        this.password = prop.getProperty("password");
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
