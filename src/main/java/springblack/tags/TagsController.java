package springblack.tags;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Api(value = "Tags", tags = { "Tags" })
@RestController
@RequestMapping("/tags")
public class TagsController {

    private final TagsService tagsService;

    @Autowired
    public TagsController(final TagsService tagsService) {

        this.tagsService = tagsService;

    }

    @ApiOperation(value = "List", notes = "Get all tags.", produces = "application/json")
    @GetMapping
    public ResponseEntity<Page<Tag>> getAllByPrincipal(Pageable pageable) {

        return ResponseEntity.ok(tagsService.getAllPageable(pageable));

    }

//    @GetMapping(Patterns.UUIDv4)
//    public ResponseEntity<Tag> getByIdAndPrincipalOrganization(@PathVariable("uuid") UUID id, Principal principal) {
//
//        return ResponseEntity.ok(tagsService.getByIdAndPrincipalOrganization(id, principal);
//
//    }

    @ApiOperation(value = "Create Tag", notes = "Create a new tag", produces = "application/json")
    @PostMapping
    public ResponseEntity<Tag> create(@RequestBody Tag tag, Principal principal) {

        return ResponseEntity.ok(tagsService.create(tag, principal));

    }

}
