package oit.is.z3182.kaizi.janken.model;

public class Janken {
  private final String playerHand;
  private final String cpuHand = "Gu";
  private final String result;

  public Janken(String playerHand) {
    this.playerHand = playerHand;
    this.result = judge(playerHand);
  }

  private String judge(String playerHand) {
    if (playerHand.equals(this.cpuHand)) {
      return "Draw";
    } else if ((playerHand.equals("Pa") && this.cpuHand.equals("Gu")) ||
        (playerHand.equals("Gu") && this.cpuHand.equals("Choki")) ||
        (playerHand.equals("Choki") && this.cpuHand.equals("Pa"))) {
      return "You Win!";
    } else {
      return "You Lose...";
    }
  }

  public String getPlayerHand() {
    return playerHand;
  }

  public String getCpuHand() {
    return cpuHand;
  }

  public String getResult() {
    return result;
  }
}
