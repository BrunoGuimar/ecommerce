package demo.controllers;

import demo.infraestructure.MyBean;
import demo.models.entities.Pessoa;
import demo.models.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {


    @Autowired
    PessoaRepository repository;

    MyBean myBean;


    @GetMapping
    public List<Pessoa> getPessoas(){
        if(myBean.validator())//PAREI AQUI
        return repository.findAll();
    }
    @GetMapping(path = "/{id}")
    public Optional<Pessoa> getPessoaById(@PathVariable String id){
        return repository.findById(id);
    }
    @GetMapping("/email/{email}")
    public List<Pessoa> getPessoaByEmail(@PathVariable String email){
        return repository.findByEmail(email);
    }
    @GetMapping("/cpf/{cpf}")
    public Object getPessoaByCpf(@PathVariable String cpf){
        return repository.findByCpf(cpf);
    }
    @PostMapping
    public Pessoa addPessoa(@RequestBody @Valid Pessoa pessoa){
        return repository.save(pessoa);
    }
    @PutMapping("/{id}")
    public Pessoa updatePessoaById(@RequestBody @Valid Pessoa pessoa, @PathVariable String id){
        Pessoa pessoaUpdated = repository.findById(id).get();
        pessoaUpdated = pessoa;
        return repository.save(pessoaUpdated);
    }
    @DeleteMapping("/{id}")
    public void deletePessoaById(@PathVariable String id){
        repository.deleteById(id);
    }
    @DeleteMapping("/all")
    public void deleteAllPessoas(){
        repository.deleteAll();
    }
}
