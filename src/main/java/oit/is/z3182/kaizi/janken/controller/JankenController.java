package oit.is.z3182.kaizi.janken.controller;

import oit.is.z3182.kaizi.janken.model.Entry;
import oit.is.z3182.kaizi.janken.model.Janken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/janken")
public class JankenController {

  private final Entry entry;

  @Autowired
  public JankenController(Entry entry) {
    this.entry = entry;
  }

  @GetMapping
  public String janken(Principal principal, ModelMap model) {
    String loginUser = principal.getName();
    this.entry.addUser(loginUser);

    model.addAttribute("loginUser", loginUser);
    model.addAttribute("entryUsers", this.entry.getUsers());
    return "janken.html";
  }

  @GetMapping("/game")
  public String jankengame(@RequestParam String hand, Principal principal, ModelMap model) {
    Janken janken = new Janken(hand);
    model.addAttribute("janken", janken);

    String loginUser = principal.getName();
    model.addAttribute("loginUser", loginUser);
    model.addAttribute("entryUsers", this.entry.getUsers());

    return "janken.html";
  }
}
