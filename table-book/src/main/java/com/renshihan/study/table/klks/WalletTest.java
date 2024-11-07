package com.renshihan.study.table.klks;

public class WalletTest {
    public static void main(String[] args) {
        String hearderMap="X-PLATFORM-TYPE: 3\n" +
                "X-FINGERPRINT-INFO: 456dbc36192b60da\n" +
                "X-CHANNEL: 0\n" +
                "jailbroken: false\n" +
                "version: 1.7.0\n" +
                "versionCode: 10700\n" +
                "X-EXCHANGE-ID: 1002\n" +
                "X-DEVICE-INFO: Android/1.7.0/meizu/meizu_20_CN/14/34/1704711123\n" +
                "content-type: application/json\n" +
                "Accept-Language: zh-HK\n" +
                "uid: 564672209785989\n" +
                "Authorization: barear eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJkMWFiMzJjM2Q5N2I0ODY4OGE5OWFjZmYxMWJiYWZjMiIsInVzZXJJZCI6NTY0NjcyMjA5Nzg1OTg5LCJleGNoYW5nZUlkIjoxMDAyLCJpYXQiOjE3MTk5OTU4MjgsImV4cCI6MTcyMDYwNTgyOH0.tvjKkDtg35GS9qK0EGZHRIhTioX2VzLJ9LQ-UAJfpZ9FyTJpJ6QKNrgGtzYba8gXS5qHdTGW0s7ZmY3pcW4yQg\n" +
                "HEADER-TEAM-ID: 564673092549573\n" +
                "X-CHECK-FLAG: 1\n" +
                "content-length: 27";
        String[] split = hearderMap.split("\\n");

        for (String s : split) {
            System.out.println(s);
        }


    }
}
