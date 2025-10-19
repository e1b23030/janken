package oit.is.z3182.kaizi.janken.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Entry {

  // このリストが全ユーザーで共有される
  private ArrayList<String> users = new ArrayList<>();

  public void addUser(String name) {
    // 同じユーザ名がなければ追加
    if (!this.users.contains(name)) {
      this.users.add(name);
    }
  }

  public ArrayList<String> getUsers() {
    return users;
  }
}
