package lt.code.academy.blogmongo.repository;

import lt.code.academy.blogmongo.document.UserDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, ObjectId> {
    Optional<UserDocument> findByUsername(String username);

}
