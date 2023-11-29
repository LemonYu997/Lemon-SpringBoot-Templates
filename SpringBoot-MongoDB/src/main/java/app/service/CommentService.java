package app.service;

import app.dao.CommentDao;
import app.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    //添加一个评论
    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }

    //更新评论
    public void updateComment(Comment comment) {
        commentDao.save(comment);
    }

    //根据id删除评论
    public void deleteCommentById(String id) {
        commentDao.deleteById(id);
    }

    //查询所有评论
    public List<Comment> findCommentList() {
        Query query = new Query();
        //查询指定字段，只查id
        query.fields().include("_id");
        return mongoTemplate.find(query, Comment.class);
    }

    //根据id查询评论
    public Comment findCommentById(String id) {
        return commentDao.findById(id).get();
    }

    //根据likenum范围查询评论 1000~2000
    public List<Comment> findCommentByLikenum() {
        Query query = new Query();
        query.addCriteria(Criteria.where("likenum").gte(1000).lte(2000));
        return mongoTemplate.find(query, Comment.class);
    }

    //根据likenum范围修改评论 1000~2000
    public void updateCommentByLikenum() {
        Query query = new Query();
        query.addCriteria(Criteria.where("likenum").gte(1000).lte(2000));
        Update update = new Update();
        update.set("nickname", "很多喜欢");
        mongoTemplate.updateMulti(query, update, Comment.class);
    }

    //可选条件查询
    public List<Comment> findCommentSelect(String articleid, String userid) {
        Query query = new Query();
        if (articleid != null) {
            query.addCriteria(Criteria.where("articleid").is(articleid));
        }
        if (userid != null) {
            query.addCriteria(Criteria.where("userid").is(userid));
        }


        return mongoTemplate.find(query, Comment.class);
    }
}

