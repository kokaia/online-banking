package ge.softlab.lessons.onlinebanking.services;

import ge.softlab.lessons.onlinebanking.models.PostResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostsClientService {
    List<PostResponseModel> searchPosts();

    ResponseEntity<PostResponseModel> getPost(Integer id);

    PostResponseModel createPost();
}
