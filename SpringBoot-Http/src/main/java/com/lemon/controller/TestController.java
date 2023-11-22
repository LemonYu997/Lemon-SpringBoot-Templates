package com.lemon.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://localhost:8080/hello");
        httpGet.setHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoie1wicGFzc3dvcmRcIjpcIjEyMzQ1NlwiLFwidXNlcklkXCI6MSxcInVzZXJuYW1lXCI6XCJ6aGFuZ3NhblwifSIsImlhdCI6MTcwMDY1ODg3MCwiZXhwIjoxNzAwNzQ1MjcwfQ.-kSfNPgaBb5ljSXiJndrfeH5o6jIIVMUS6mUDKvxnl8");
        CloseableHttpResponse response = null;

        try {
            //执行请求
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                //获取响应结果
                return EntityUtils.toString(response.getEntity());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }

        return "error";
    }
}
