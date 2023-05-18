package demo.controllers;

import demo.models.entities.Pedido;
import demo.models.repositories.PedidoRepository;
import demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    @Autowired
    PedidoService service;

    @Autowired
    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = service.salvarPedido(pedido);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable String id) {
        Pedido pedido = service.buscarPedidoPorId(id);
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> buscarTodosPedidos() {
        List<Pedido> pedidos = service.buscarTodosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable String id, @RequestBody Pedido pedido) {
        Pedido pedidoExistente = service.buscarPedidoPorId(id);
        if (pedidoExistente != null) {
            pedido.setId(id);
            Pedido pedidoAtualizado = service.atualizarPedido(pedido);
            return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable String id) {
        Pedido pedidoExistente = service.buscarPedidoPorId(id);
        if (pedidoExistente != null) {
            service.deletarPedido(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
