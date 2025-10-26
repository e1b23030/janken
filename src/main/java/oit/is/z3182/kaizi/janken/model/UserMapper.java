package oit.is.z3182.kaizi.janken.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserMapper {

  /**
   * ユーザ情報をSELECT
   */
  @Select("SELECT * FROM users")
  ArrayList<User> selectAllUsers();

  /**
   * GETリクエストで利用: idを利用してUserオブジェクトを取得
   */
  @Select("SELECT * FROM users WHERE id = #{id}")
  User selectUserById(int id);

  /**
   * 試合登録で利用: nameを利用してUserオブジェクトを取得
   */
  @Select("SELECT * FROM users WHERE name = #{name}")
  User selectUserByName(String name);
}
