package lt.code.academy.blogmongo.repository;

import lt.code.academy.blogmongo.document.UserDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <UserDocument, ObjectId> {


}
