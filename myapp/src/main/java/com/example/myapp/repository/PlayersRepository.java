package com.example.myapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myapp.entity.Players;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Integer> {

    // プレイヤーIDで検索
    Optional<Players> findByPlayerId(int playerId);

    // ゲームIDで所属プレイヤー一覧を取得
    List<Players> findByGameGameId(int gameId);

    // GMフラグが true のプレイヤーを取得（GM一覧）
    List<Players> findByGmFlagTrue();

    // GMフラグが false のプレイヤー（一般プレイヤー一覧）
    List<Players> findByGmFlagFalse();

    // 所持金が指定値以上のプレイヤー
    List<Players> findByMoneyGreaterThanEqual(int money);

    // 存在確認
    boolean existsByPlayerId(int playerId);
}
