package co.id.gooddoctor.patrio.dao;

import co.id.gooddoctor.patrio.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDao extends JpaRepository<Content, Long> {

}
