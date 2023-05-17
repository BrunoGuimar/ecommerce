package demo.controllers;

import demo.models.entities.Pessoa;
import demo.models.repositories.PessoaRepository;
import demo.services.PessoaService;
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
    @Autowired
    PessoaService services;

    // Retorna uma lista de todas as pessoas do banco
    @GetMapping
    public List<Pessoa> getPessoas(){
        return repository.findAll();
    }
    // Retorna uma pessoa filtrada por ID
    @GetMapping(path = "/{id}")
    public Optional<Pessoa> getPessoaById(@PathVariable String id){
        return repository.findById(id);
    }
    //Retorna uma pessoa filtrado por Email
    @GetMapping("/email/{email}")
    public Object getPessoaByEmail(@PathVariable String email){
        return repository.findByEmail(email);
    }
    // Retorna uma pessoa filtrado por CPF
    @GetMapping("/cpf/{cpf}")
    public Object getPessoaByCpf(@PathVariable String cpf){
        return repository.findByCpf(cpf);
    }
    // Adiciona uma nova pessoa ao banco
    @PostMapping
    public Object addPessoa(@RequestBody @Valid Pessoa pessoa){
        if(services.ifIsNullOrBlank(pessoa) || services.ifAlreadyExistsCpfOrEmail(pessoa)){
            return "Foram informados valores inválidos ou Pessoa já cadastrada!";
        }
        pessoa.setCpf(pessoa.getCpf().trim().replaceAll("[^a-zA-Z0-9]", ""));
        pessoa.setEmail(pessoa.getEmail().trim());
        pessoa.setNome(pessoa.getNome().trim());
        return repository.save(pessoa);
    }
    // Atualiza as informações da pessoa informando o ID
    @PutMapping("/{id}")
    public Object updatePessoaById(@RequestBody @Valid Pessoa pessoa, @PathVariable String id){
        if(repository.existsById(id)){
            Pessoa newPessoa = repository.findById(id).get();
            return repository.save(services.updatePessoa(pessoa, newPessoa));
        }
        return "Pessoa não cadastrada!";
    }
    // Deleta uma pessoa do banco informando o ID
    @DeleteMapping("/{id}")
    public void deletePessoaById(@PathVariable String id){
        repository.deleteById(id);
    }
    // Deleta todas as pessoas do banco UTILIZAR APENAS EM DESENVOLVIMENTO !
    @DeleteMapping("/all")
    public void deleteAllPessoas(){
        repository.deleteAll();
    }
}
