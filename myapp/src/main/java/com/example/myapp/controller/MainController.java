package com.example.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Map;

@RestController // JSON を返すコントローラ
public class MainController {
	
	//ゲーム名一覧取得
    @GetMapping("/gameList/view")
    public Map<String, String> gameListView() {
        return Map.of("message", "Main表示");
    }
    
    //ゲームを作成する
    @PostMapping("/createGame")
    public Map<String, String> creatGame() {
        return Map.of("message", "GM表示");
    }
    
    

}
