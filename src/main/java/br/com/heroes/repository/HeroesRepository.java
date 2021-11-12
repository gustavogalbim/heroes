package br.com.heroes.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.com.heroes.document.Heroes;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String> {
}
