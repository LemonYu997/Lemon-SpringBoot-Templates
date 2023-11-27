package app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * MongoDB实体类
 * 注解@Document把一个Java类声明为MongDB的文档，通过collection参数指定对应的集合
 * 若未加@Document，该bean save 到mongo的 comment collection
 * 若添加@Document，则save到 comment collection
 */
@Document("comment")    //省略时，根据类名小写映射到集合
//@CompoundIndex(def = "{'userid': 1, 'nickname': -1}") //复合索引，写法同mongodb
public class Comment implements Serializable {
    //主键标识，自动对应mongodb的主键字段“_id”
    @Id
    private String id;
    //对应mongdb字段名字，一致时无需注解
    @Field("content")
    private String content;
    private LocalDateTime createdatetime;
    //添加一个单字段索引
    @Indexed
    private String userid;
    private String nickname;
    private Integer likenum;
    private String state;
    private String articleid;

    public Comment() {
    }

    public Comment(String id, String content, LocalDateTime createdatetime, String userid, String nickname, Integer likenum, String state, String articleid) {
        this.id = id;
        this.content = content;
        this.createdatetime = createdatetime;
        this.userid = userid;
        this.nickname = nickname;
        this.likenum = likenum;
        this.state = state;
        this.articleid = articleid;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", createdatetime=" + createdatetime +
                ", userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", likenum=" + likenum +
                ", state='" + state + '\'' +
                ", articleid='" + articleid + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(LocalDateTime createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }
}
