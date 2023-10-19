import { Component } from '@angular/core';
import { RepartoService } from './shared/services/reparto.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'RepartosOlympusWeb';

  constructor(
    private repartoService:RepartoService
  ){
    this.listar()
  }

  async listar(){
    let d = await this.repartoService.listarRepartos()
    console.log(d);
  }
}
