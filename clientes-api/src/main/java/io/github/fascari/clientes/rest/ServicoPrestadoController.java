package io.github.fascari.clientes.rest;

import io.github.fascari.clientes.model.entity.Cliente;
import io.github.fascari.clientes.model.entity.ServicoPrestado;
import io.github.fascari.clientes.model.repository.ClienteRepository;
import io.github.fascari.clientes.model.repository.ServicoPrestadoRepository;
import io.github.fascari.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.fascari.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
public class ServicoPrestadoController {
    private final ClienteRepository clienteRepository;
    private final ServicoPrestadoRepository repository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
        Integer idCliente = dto.getIdCliente();

        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente."));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setData(LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes) {
        return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }
}
