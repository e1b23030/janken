package oit.is.z3182.kaizi.janken.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface MatchMapper {

  @Select("SELECT * FROM matches WHERE isActive = FALSE")
  ArrayList<Match> selectAllMatches();

  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, #{isActive})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertMatch(Match match);

  @Select("SELECT * FROM matches WHERE (user1 = #{user1} AND user2 = #{user2} AND isActive = TRUE) OR (user1 = #{user2} AND user2 = #{user1} AND isActive = TRUE)")
  Match selectActiveMatch(int user1, int user2);

  @Update("UPDATE matches SET isActive = FALSE WHERE id = #{id}")
  void updateMatchIsActive(int id);

  @Select("SELECT * FROM matches WHERE ((user1 = #{user1} AND user2 = #{user2}) OR (user1 = #{user2} AND user2 = #{user1})) AND isActive = FALSE ORDER BY id DESC LIMIT 1")
  Match selectLatestCompletedMatch(int user1, int user2);
}
