package app.dao;

import app.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentDao extends MongoRepository<Comment, String> {
}
