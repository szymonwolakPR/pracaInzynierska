package shcases.repository;

import shcases.domain.Grodek;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Grodek entity.
 */
@SuppressWarnings("unused")
public interface GrodekRepository extends MongoRepository<Grodek,String> {

}
