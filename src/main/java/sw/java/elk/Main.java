package sw.java.elk;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String pub = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEoprRgU9Bph/EH7FiGgaSFgygAvI7ExXAdFzUbq1HKynMl7eCX0WpJAb7dWhCEcazYb95xtgRdE7ooAWkfTY25g==";
        System.out.println(pub.getBytes().length * 3 /4);
        String pri = "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQg981ZNqKt/8uW30rwInWqxH/hmJrcE1yCOo5eFgCE/fChRANCAASyO1mFX3m5WKdFRlRG5GhzylskPQnolgX+w2VHzJ001duRhe7Cs+GUqhk7oxE/pXiDFSRBgyNkUY510Urj/Kjs";
        System.out.println(pri.getBytes().length* 3 /4);
        System.out.println(Arrays.toString(new byte[93]));
    }
}
