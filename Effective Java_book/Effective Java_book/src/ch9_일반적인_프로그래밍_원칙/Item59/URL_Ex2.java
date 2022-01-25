package ch9_일반적인_프로그래밍_원칙.Item59;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class URL_Ex2 {
    public static void main(String[] args) throws MalformedURLException {
        try (InputStream in = new URL( args[0]).openStream()) {
            in.transferTo(System.out);
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }
}
