import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router'

import { Cliente } from '../cliente'
import { ClientesService } from '../../services/clientes.service'
import { Observable } from 'rxjs';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente: Cliente;
  success: boolean = false;
  errors!: String[] | null;
  id!: number;

  constructor(
    private service: ClientesService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    let params: Observable<Params> = this.activatedRoute.params
    params.subscribe(urlParams => {
      this.id = urlParams['id'];
      if (!this.id) {
        return;
      }
      this.service
        .getClienteById(this.id)
        .subscribe(
          response => this.cliente = response,
          () => this.cliente = new Cliente()
        )
    })
  }

  voltarParaListagem() {
    this.router.navigate(['/clientes/lista'])
  }

  onSubmit() {
    if (this.id) {
      this.atualizar();
      return;
    }
    this.criar();
  }

  private criar() {
    this.service
      .salvar(this.cliente)
      .subscribe(response => {
        this.success = true;
        this.errors = null;
        this.cliente = response;
      }, errorResponse => {
        this.success = false;
        this.errors = errorResponse.error.errors;
      });
  }

  private atualizar() {
    this.service
      .atualizar(this.cliente)
      .subscribe(() => {
        this.success = true;
        this.errors = null;
      }, () => {
        this.errors = ['Erro ao atualizar o cliente.'];
      });
  }
}
