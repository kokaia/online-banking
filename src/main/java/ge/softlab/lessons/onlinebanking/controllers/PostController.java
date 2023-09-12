package ge.softlab.lessons.onlinebanking.controllers;


import ge.softlab.lessons.onlinebanking.models.PostResponseModel;
import ge.softlab.lessons.onlinebanking.services.PostsClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostsClientService service;

    @GetMapping
    public ResponseEntity<?> search(){
        return ResponseEntity.ok(service.searchPosts());
    }

    @GetMapping("{id}")
    public ResponseEntity<PostResponseModel> getPost(@PathVariable Integer id){
        return service.getPost(id);
    }

    @PostMapping()
    public PostResponseModel createPost(){
        return service.createPost();
    }
}
