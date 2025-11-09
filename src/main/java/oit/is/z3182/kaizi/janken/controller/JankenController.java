package oit.is.z3182.kaizi.janken.controller; // ユーザーのパッケージ名

import oit.is.z3182.kaizi.janken.model.Match;
import oit.is.z3182.kaizi.janken.model.MatchMapper;
import oit.is.z3182.kaizi.janken.model.User;
import oit.is.z3182.kaizi.janken.model.UserMapper;
import oit.is.z3182.kaizi.janken.model.MatchInfo;
import oit.is.z3182.kaizi.janken.model.MatchInfoMapper;
import oit.is.z3182.kaizi.janken.service.AsyncKekka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class JankenController {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @Autowired
  private MatchInfoMapper matchInfoMapper;

  @Autowired
  private AsyncKekka asyncKekka;

  @GetMapping("/janken")
  public String janken(Principal principal, ModelMap model) {
    String loginUserName = principal.getName();
    User loginUser = userMapper.selectUserByName(loginUserName);
    model.addAttribute("loginUser", loginUser);

    ArrayList<User> users = userMapper.selectAllUsers();
    model.addAttribute("users", users);

    ArrayList<Match> matches = matchMapper.selectAllMatches();
    model.addAttribute("matches", matches);

    ArrayList<MatchInfo> activeMatches = matchInfoMapper.selectActiveMatchInfosByUserId(loginUser.getId());
    model.addAttribute("activeMatches", activeMatches);

    return "janken.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam int id, Principal principal, ModelMap model) {
    String loginUserName = principal.getName();
    User loginUser = userMapper.selectUserByName(loginUserName);

    User opponent = userMapper.selectUserById(id);

    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);

    return "match.html";
  }

  @GetMapping("/fight")
  public String fight(@RequestParam int id, @RequestParam String hand, Principal principal, ModelMap model) {
    String loginUserName = principal.getName();
    User loginUser = userMapper.selectUserByName(loginUserName);
    User opponent = userMapper.selectUserById(id);

    MatchInfo pendingMatch = matchInfoMapper.findPendingMatch(opponent.getId(), loginUser.getId());

    if (pendingMatch != null) {
      Match match = new Match();
      match.setUser1(opponent.getId());
      match.setUser2(loginUser.getId());
      match.setUser1Hand(pendingMatch.getUser1Hand());
      match.setUser2Hand(hand);
      match.setIsActive(true);
      matchMapper.insertMatch(match);

      matchInfoMapper.updateIsActiveToFalse(pendingMatch.getId());

    } else {
      MatchInfo myWait = new MatchInfo();
      myWait.setUser1(loginUser.getId());
      myWait.setUser2(opponent.getId());
      myWait.setUser1Hand(hand);
      myWait.setIsActive(true);
      matchInfoMapper.insertMatchInfo(myWait);
    }

    model.addAttribute("loginUser", loginUser);
    model.addAttribute("opponent", opponent);
    return "wait.html";
  }

  @GetMapping("/api/kekka")
  @ResponseBody
  public ResponseEntity<Match> getKekka(@RequestParam int opponentId, Principal principal) {
    User loginUser = userMapper.selectUserByName(principal.getName());

    Match match = asyncKekka.pollKekka(loginUser.getId(), opponentId);

    if (match != null) {
      return ResponseEntity.ok(match);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

}
