package oit.is.z3182.kaizi.janken.service;

import oit.is.z3182.kaizi.janken.model.Match;
import oit.is.z3182.kaizi.janken.model.MatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncKekka {

  @Autowired
  MatchMapper matchMapper;

  /**
   * 試合結果を1回だけDBに問い合わせる
   * (JSのポーリングから呼び出される)
   *
   * @param myId       自分のID
   * @param opponentId 相手のID
   * @return 試合結果 (見つからなければ null)
   */
  public Match pollKekka(int myId, int opponentId) {

    Match match = matchMapper.selectActiveMatch(myId, opponentId);

    if (match != null) {
      matchMapper.updateMatchIsActive(match.getId());
      return match;
    } else {

      Match completedMatch = matchMapper.selectLatestCompletedMatch(myId, opponentId);

      if (completedMatch != null) {
        return completedMatch;
      }
    }

    return null;
  }
}
