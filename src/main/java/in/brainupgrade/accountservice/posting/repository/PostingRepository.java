package in.brainupgrade.accountservice.posting.repository;

import in.brainupgrade.accountservice.posting.domain.Posting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostingRepository extends JpaRepository<Posting, String> {

    List<Posting> findByClientReference(String clientReference);
}
