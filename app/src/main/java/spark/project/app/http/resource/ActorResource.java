package spark.project.app.http.resource;

import com.spark.domainmodel.ApiResponse;
import com.spark.domainmodel.model.Actor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spark.project.app.repository.ActorRepository;

import java.util.List;

@RestController
@RequestMapping("v1/api/spark/actors")
public record ActorResource(ActorRepository actorRepository) {

    @GetMapping
    public ResponseEntity<ApiResponse<List<Actor>>>getAll(){
        ApiResponse<List<Actor>> data = ApiResponse
                .<List<Actor>>builder()
                .data(actorRepository.findAll())
                .build();
        return ResponseEntity.ok(data);
    }
}
