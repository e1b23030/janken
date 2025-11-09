package oit.is.z3182.kaizi.janken.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface MatchInfoMapper {

  @Select("SELECT * FROM matchinfo WHERE (user1 = #{userId} OR user2 = #{userId}) AND isActive = TRUE")
  ArrayList<MatchInfo> selectActiveMatchInfosByUserId(int userId);

  @Select("SELECT * FROM matchinfo WHERE user1 = #{opponentId} AND user2 = #{myId} AND isActive = TRUE LIMIT 1")
  MatchInfo findPendingMatch(int opponentId, int myId);

  @Insert("INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertMatchInfo(MatchInfo matchInfo);

  @Update("UPDATE matchinfo SET isActive = FALSE WHERE id = #{id}")
  void updateIsActiveToFalse(int id);
}
