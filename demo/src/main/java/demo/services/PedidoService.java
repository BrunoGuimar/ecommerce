package demo.services;

import demo.models.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoService {

    private MongoOperations mongoOperations;

    @Autowired
    public void PedidoRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public PedidoService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Pedido salvarPedido(Pedido pedido) {
        mongoOperations.save(pedido);
        return pedido;
    }

    public Pedido buscarPedidoPorId(String id) {
        return mongoOperations.findById(id, Pedido.class);
    }

    public List<Pedido> buscarTodosPedidos() {
        return mongoOperations.findAll(Pedido.class);
    }

    public Pedido atualizarPedido(Pedido pedido) {
        mongoOperations.save(pedido);
        return pedido;
    }

    public void deletarPedido(String id) {
        Pedido pedido = buscarPedidoPorId(id);
        if (pedido != null) {
            mongoOperations.remove(pedido);
        }
    }
}