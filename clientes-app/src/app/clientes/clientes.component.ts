import { Component } from '@angular/core';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent {

  nome: string;
  clientes: Cliente[];

  constructor() {
    this.nome = 'Felipe';
    let clienteUm = new Cliente('Cliente Um', 30);
    let clienteDois = new Cliente('Cliente Dois', 40);
    this.clientes = [clienteUm, clienteDois];
  }

}

class Cliente {

  constructor(
    public nome: string,
    public idade: number) {
  }

}