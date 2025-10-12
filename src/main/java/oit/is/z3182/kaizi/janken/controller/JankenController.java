package oit.is.z3182.kaizi.janken.controller;

import oit.is.z3182.kaizi.janken.model.Janken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JankenController {

  /**
   * トップページ (index.html) を表示
   *
   * @return
   */
  @GetMapping("/")
  public String index() {
    return "index.html";
  }

  /**
   * index.htmlからのPOSTリクエストを処理し、janken.htmlにリダイレクト
   *
   * @param username
   * @param model
   * @return
   */
  @PostMapping("/janken")
  public String jankenPost(@RequestParam String username, ModelMap model) {
    model.addAttribute("username", username);
    return "janken.html";
  }

  /**
   * janken.htmlを直接表示する
   *
   * @return
   */
  @GetMapping("/janken")
  public String jankenGet() {
    return "janken.html";
  }

  /**
   * じゃんけんの結果を処理する
   *
   * @param hand  プレイヤーの手
   * @param model
   * @return
   */
  @GetMapping("/jankengame")
  public String jankengame(@RequestParam String hand, ModelMap model) {
    // じゃんけんの処理
    Janken janken = new Janken(hand);

    // 結果をModelに格納
    model.addAttribute("janken", janken);

    return "janken.html";
  }
}
