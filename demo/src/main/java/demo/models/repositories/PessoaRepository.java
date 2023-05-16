package demo.models.repositories;

import demo.models.entities.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface PessoaRepository extends MongoRepository<Pessoa, String> {
    @Query("{ 'email' : ?0 }")
    List<Pessoa> findByEmail(String email);
    @Query("{ 'cpf' : ?0 }")
    List<Pessoa> findByCpf(String cpf);
}
