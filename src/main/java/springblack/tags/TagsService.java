package springblack.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import springblack.common.exceptions.RecordAlreadyExistsException;
import springblack.common.exceptions.ResourceNotFoundException;
import springblack.identity.users.UsersService;

import java.security.Principal;

@Service
public class TagsService {

    private final TagsRepository tagsRepository;
    private final UsersService   usersService;

    @Autowired
    public TagsService(final TagsRepository tagsRepository, UsersService usersService) {

        this.tagsRepository = tagsRepository;
        this.usersService = usersService;

    }

    public Tag getByName(String name) {

        return tagsRepository.getByName(name).orElseThrow(() -> new ResourceNotFoundException("tag could not be located"));

    }

    public Page<Tag> getAllPageable(Pageable pageable) {

        return tagsRepository.findAll(pageable);

    }

    public Tag create(Tag tag, Principal principal) {

        usersService.mustHavePrivilege(principal, Tag.PRIVILEGE_CREATE);

        try {

            getByName(tag.getName());

            throw new RecordAlreadyExistsException("tag by this name already exists");

        } catch (ResourceNotFoundException e) {

            tag.setOrganization(usersService.getByPrincipal(principal).getOrganization());
            tag.setUser(usersService.getByPrincipal(principal));

            return tagsRepository.save(tag);

        }

    }

}
