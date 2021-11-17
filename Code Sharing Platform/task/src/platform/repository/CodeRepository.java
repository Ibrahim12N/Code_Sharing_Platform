package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import platform.entity.CodeSnippet;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<CodeSnippet, String> {
    @Query(value = "SELECT * FROM CODE_SNIPPET WHERE view_Restricted = false and time_Restricted=false order by creation_time desc limit 10", nativeQuery = true)
    List<CodeSnippet> findOrderByDateDescLimit10();

}