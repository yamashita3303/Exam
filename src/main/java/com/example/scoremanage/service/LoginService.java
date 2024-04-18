package com.example.scoremanage.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    // IDとパスワードの組み合わせに対応する名前を返すメソッド
    public String getNameForLoginIdAndPassword(String loginId, String password) {
        if ("2347066".equals(loginId) && "ohara".equals(password)) {
            return "谷中";
        } else if ("456".equals(loginId) && "ohara".equals(password)) {
            return "田中";
        } else {
            return null; // 対応するIDとパスワードが見つからない場合はnullを返す
        }
    }
}
