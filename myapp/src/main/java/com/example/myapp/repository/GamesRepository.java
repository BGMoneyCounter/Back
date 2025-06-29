package com.example.myapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myapp.entity.Games;

@Repository
public interface GamesRepository extends JpaRepository<Games,Integer>{
    // ゲーム名で検索（完全一致）
    Optional<Games> findByGameName(String gameName);

    // ゲーム名で部分一致検索
    List<Games> findByGameNameContaining(String keyword);

    // パスワードで検索（セキュリティ的には推奨されないが必要なら）
    Optional<Games> findByPass(String pass);

    // 終了フラグが true のゲーム一覧
    List<Games> findByEndFlagTrue();

    // 終了フラグが false のゲーム一覧
    List<Games> findByEndFlagFalse();

    // gameId で存在確認
    boolean existsByGameId(int gameId);

    // gameId で取得
    Optional<Games> findByGameId(int gameId);
}