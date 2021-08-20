import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';

import { TemplateModule } from './template/template.module';
import { ClientesModule } from './clientes/clientes.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component'
import { LayoutComponent } from './layout/layout.component';

import { ClientesService } from './services/clientes.service';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LayoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TemplateModule,
    ClientesModule,
  ],
  providers: [
    ClientesService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
