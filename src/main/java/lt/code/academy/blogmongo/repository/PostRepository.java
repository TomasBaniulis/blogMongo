package lt.code.academy.blogmongo.repository;

import lt.code.academy.blogmongo.document.PostDocument;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository <PostDocument, ObjectId> {

}
