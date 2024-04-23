package com.example.scoremanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scoremanage.model.Teacher;
import com.example.scoremanage.repository.LoginRepository;

@Service
public class LoginService {

    @Autowired
    private LoginRepository userRepository;

    // ユーザーの認証を行うメソッド
    public String authenticateUser(String id, String password) {
        // IDを使ってユーザーを検索
        Teacher user = userRepository.findById(id).orElse(null);
        if (user == null) {
            // ユーザーが存在しない場合は "IDが違います" を返す
            return "IDが違います";
        }
        // 登録されているIDと入力されたIDが同じかどうかを判定
        if (!user.getId().equals(id)) {
            return "IDが違います";
        }
        // パスワードが一致するかを確認
        if (!user.getPassword().equals(password)) {
            return "パスワードが違います";
        }
        // 認証成功
        return "認証成功";
    }
}
