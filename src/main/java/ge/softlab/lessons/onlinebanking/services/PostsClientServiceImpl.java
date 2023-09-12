package ge.softlab.lessons.onlinebanking.services;

import ge.softlab.lessons.onlinebanking.models.PostResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostsClientServiceImpl implements PostsClientService {

    private final RestTemplate restTemplate;

    @Override
    public List<PostResponseModel> searchPosts() {
        var result = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", PostResponseModel[].class);
        System.out.println(result);
        return List.of(result);
    }

    @Override
    public ResponseEntity<PostResponseModel> getPost(Integer id) {
        try {
            var result = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/" + id, PostResponseModel.class);
            System.out.println(result);
            return result;
        } catch (HttpClientErrorException e){
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public PostResponseModel createPost() {
        PostResponseModel data = new PostResponseModel(null, 1, "სათაური", "ტექსტი");
        var result = restTemplate.postForObject("https://jsonplaceholder.typicode.com/users/1/posts",  data, PostResponseModel.class);
        System.out.println(result);
        return result;
    }

}
