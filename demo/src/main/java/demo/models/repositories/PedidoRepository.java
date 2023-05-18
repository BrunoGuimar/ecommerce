package demo.models.repositories;

import demo.models.entities.Pedido;
import demo.models.entities.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    @Query("{ 'descricao' : ?0 }")
    List<Pessoa> findByDescricao(String descricao);
    @Query("{ 'nomeCliente' : ?0 }")
    List<Pessoa> findByNomeCliente(String nomeCliente);
}
