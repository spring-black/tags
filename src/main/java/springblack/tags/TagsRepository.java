package springblack.tags;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TagsRepository extends PagingAndSortingRepository<Tag, UUID> {

    Optional<Tag> getByName(String name);

}
