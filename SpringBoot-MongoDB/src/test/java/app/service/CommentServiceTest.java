package app.service;

import app.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @Test
    public void findCommentList() {
        List<Comment> commentList = commentService.findCommentList();
        for (Comment comment : commentList) {
            System.out.println(comment);
        }
    }

    @Test
    public void findCommentByLikenum() {
        List<Comment> comments = commentService.findCommentByLikenum();
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    public void updateCommentByLikenum() {
        commentService.updateCommentByLikenum();
    }
}
