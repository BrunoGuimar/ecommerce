package demo.services;

import demo.models.entities.Pessoa;
import demo.models.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaService {
    private final PessoaRepository repository;
    @Autowired
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public boolean ifIsNullOrBlank(Pessoa pessoa){
        if(
                pessoa.getEmail() == null
                        || pessoa.getEmail().trim() == ""
                        || pessoa.getCpf() == null
                        || pessoa.getCpf().trim() == ""
                        || pessoa.getCpf().trim().length() < 11
                        || pessoa.getNome().trim() == ""
                        || pessoa.getNome() == null

        ){
            return true;
        }
        return false;
    }
    public boolean ifAlreadyExistsCpfOrEmail(Pessoa pessoa) {
        List<Pessoa> pessoasByCpf = repository.findByCpf(pessoa.getCpf());
        List<Pessoa> pessoasByEmail = repository.findByEmail(pessoa.getEmail());

        if (!pessoasByCpf.isEmpty() || !pessoasByEmail.isEmpty()) {
            if (pessoasByCpf.isEmpty() || pessoa.getCpf().equals(pessoasByCpf.get(0).getCpf())
                    || pessoasByEmail.isEmpty() || pessoa.getEmail().equals(pessoasByEmail.get(0).getEmail())) {
                return true;
            }
        }
        return false;
    }

    public Pessoa updatePessoa(Pessoa oldPessoa, Pessoa newPessoa){

        if(oldPessoa.getEmail() != null && oldPessoa.getEmail().trim() != ""){
            oldPessoa.setEmail(oldPessoa.getEmail().trim());
            newPessoa.setEmail(oldPessoa.getEmail());
        }
        if(oldPessoa.getCpf() != null && oldPessoa.getCpf().trim() != "" && oldPessoa.getCpf().trim().length() >= 11){
            oldPessoa.setCpf(oldPessoa.getCpf().trim().replaceAll("[^a-zA-Z0-9]", ""));
            newPessoa.setCpf(oldPessoa.getCpf());
        }
        if(oldPessoa.getNome() != null && oldPessoa.getNome().trim() != ""){
            oldPessoa.setNome(oldPessoa.getNome());
            newPessoa.setNome(oldPessoa.getNome());
        }
        return newPessoa;
    }

}
