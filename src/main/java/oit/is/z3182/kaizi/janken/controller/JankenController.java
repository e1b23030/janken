package oit.is.z3182.kaizi.janken.controller;

import oit.is.z3182.kaizi.janken.model.MatchMapper;
import oit.is.z3182.kaizi.janken.model.Match;
import oit.is.z3182.kaizi.janken.model.User;
import oit.is.z3182.kaizi.janken.model.UserMapper;
import oit.is.z3182.kaizi.janken.model.Janken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class JankenController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @GetMapping("/janken")
  public String janken(Principal principal, ModelMap model) {
    String loginUser = principal.getName();
    model.addAttribute("loginUser", loginUser);
    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users); // "users" という名前でHTMLに渡す

    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("matches", matches);

    return "janken.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam int id, Principal principal, ModelMap model) {

    // ログインユーザー名(String)を取得
    String loginUserName = principal.getName();

    // 1. ログインユーザーの *Userオブジェクト* をDBから取得
    User loginUser = userMapper.selectUserByName(loginUserName);

    // 2. 対戦相手(id)の情報をDBから取得
    User opponent = userMapper.selectUserById(id);

    // 3. Modelに情報をセット
    // "loginUserName" (String) ではなく、"loginUser" (User) を渡す
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);

    return "match.html";
  }

  @GetMapping("/fight")
  public String fight(@RequestParam int id, @RequestParam String hand, Principal principal, ModelMap model) {
    // 1. ユーザー情報を取得
    String loginUserName = principal.getName();
    User loginUser = userMapper.selectUserByName(loginUserName); // 自分のUserオブジェクト
    User opponent = userMapper.selectUserById(id); // 対戦相手のUserオブジェクト

    // 2. CPUの手を決定 (固定で "Gu")
    String cpuHand = "Gu";

    // 3. 試合結果をDBに登録
    Match match = new Match();
    match.setUser1(loginUser.getId()); // 自分のID
    match.setUser2(opponent.getId()); // 相手のID
    match.setUser1Hand(hand); // 自分の手
    match.setUser2Hand(cpuHand); // 相手の手
    matchMapper.insertMatch(match); // DBにINSERT

    // 4. 勝敗判定
    String result = judge(hand, cpuHand);

    // 5. モデルに結果をセットしてmatch.htmlに返す
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);
    model.addAttribute("playerHand", hand);
    model.addAttribute("cpuHand", cpuHand);
    model.addAttribute("result", result); // "result" があるとmatch.htmlで結果が表示される

    return "match.html";
  }

  private String judge(String playerHand, String cpuHand) {
    if (playerHand.equals(cpuHand)) {
      return "Draw";
    } else if ((playerHand.equals("Pa") && cpuHand.equals("Gu")) ||
        (playerHand.equals("Gu") && cpuHand.equals("Choki")) ||
        (playerHand.equals("Choki") && cpuHand.equals("Pa"))) {
      return "You Win!";
    } else {
      return "You Lose...";
    }
  }
}
