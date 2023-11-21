import { Component, AfterViewInit, Input } from '@angular/core';
import { Destino } from 'src/app/models/destino';

@Component({
  selector: 'app-menu-select',
  templateUrl: './menu-select.component.html',
  styleUrls: ['./menu-select.component.css']
})
export class MenuSelectComponent {

  // Asegúrate de que el tipo de datos sea adecuado para tu caso

  @Input() titulo: String = "";
  selectedDistrito: any;
  @Input() listado: any[] = [];
  inputBox!: HTMLElement;

  ngAfterViewInit() {
    this.inputBox = document.querySelector('.input-box') as HTMLElement;

    this.inputBox.onclick = () => {
      this.inputBox.classList.toggle('open');
      let list = this.inputBox.nextElementSibling as HTMLElement;
      if (list.style.maxHeight) {
        list.style.maxHeight = '';
        list.style.boxShadow = '';
      } else {
        list.style.maxHeight = list.scrollHeight + 'px';
        list.style.boxShadow =
          '0 1px 2px 0 rgba(0, 0, 0, 0.15),0 1px 3px 1px rgba(0, 0, 0, 0.1)';
      }
    };

    let rad = document.querySelectorAll('.radio');
    rad.forEach((item) => {
      item.addEventListener('change', () => {
        this.inputBox.innerHTML = (item.nextElementSibling as HTMLElement).innerHTML;
        this.inputBox.click();
      });
    });
  }
  onDistritoChange(distrito: Destino): void {
    this.selectedDistrito = distrito;
  }
}
