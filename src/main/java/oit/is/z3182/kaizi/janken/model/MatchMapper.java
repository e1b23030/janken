package oit.is.z3182.kaizi.janken.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface MatchMapper {

  @Select("SELECT * FROM matches")
  ArrayList<Match> selectAllMatches();

  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertMatch(Match match);
}
