package io.github.fascari.agenda.api.rest;

import io.github.fascari.agenda.model.entity.Contatos;
import io.github.fascari.agenda.model.repository.ContatosRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatosController {

    private final ContatosRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contatos save(@RequestBody Contatos contato) {
        return repository.save(contato);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @GetMapping
    public Page<Contatos> list(@RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size", defaultValue = "10") Integer tamanhoPagina) {
        Sort sort = Sort.by(Sort.Direction.ASC, "nome");
        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);
        return repository.findAll(pageRequest);
    }

    @PatchMapping("{id}/favorito")
    public void favorite(@PathVariable Integer id) {
        Optional<Contatos> contato = repository.findById(id);
        contato.ifPresent(c -> {
            boolean favorito = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!favorito);
            repository.save(c);
        });
    }

    @PutMapping("{id}/foto")
    public byte[] addPhoto(@PathVariable Integer id, @RequestParam("foto") Part arquivo) {
        Optional<Contatos> contato = repository.findById(id);
        return contato.map(c -> {
            try {
                InputStream is = arquivo.getInputStream();
                byte[] bytes = new byte[(int) arquivo.getSize()];
                IOUtils.readFully(is, bytes);
                c.setFoto(bytes);
                repository.save(c);
                is.close();
                return bytes;
            } catch (IOException e) {
                return null;
            }
        }).orElse(null);
    }
}
