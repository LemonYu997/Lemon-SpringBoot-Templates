package app.service;

import app.dao.CommentDao;
import app.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

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
        return commentDao.findAll();
    }

    //根据id查询评论
    public Comment findCommentById(String id) {
        return commentDao.findById(id).get();
    }
}

