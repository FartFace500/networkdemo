package dk.cphbusiness.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {
    public static void main(String[] args){
    String subject = "GET /pages/index.html HTTP/1.1\n" +
            "Host: www.example.com\n" +
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n" +
            "Accept-Language: en-US,en;q=0.5\n" +
            "Accept-Encoding: gzip, deflate, br\n" +
            "Connection: keep-alive\n" +
            "Upgrade-Insecure-Requests: 1";
    HttpRequest httpRequest = new HttpRequest();
    Map<String,String> testMap = httpRequest.parse(subject);
        for (Map.Entry<String, String> stringStringEntry : testMap.entrySet()) {
            System.out.println(stringStringEntry.getKey() +":"+ stringStringEntry.getValue());
        }

    }
    public Map<String,String> parse(String header){
    ArrayList<String> stringArrayList = new ArrayList<>(List.of(header.split("\n")));
    Map<String, String> parsemap = new HashMap<>();
    String [] temp = new String[2];
        for (int i = 0; i < stringArrayList.size(); i++) {
            if (i != 0){
                temp = stringArrayList.get(i).split(": ");
                parsemap.put(temp[0],temp[1]);
            }
            parsemap.put("Info",stringArrayList.get(0));
        }
        return parsemap;
    }
}
