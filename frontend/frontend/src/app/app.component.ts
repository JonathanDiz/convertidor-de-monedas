import { Component } from '@angular/core';
import { CurrencyConverterComponent } from './currency-converter/currency-converter.component';

@Component({
  selector: 'app-root',
  standalone: true, 
  imports: [CurrencyConverterComponent],
  template: `
    <h1>Conversor de Monedas</h1>
    <app-currency-converter></app-currency-converter>
  `
})
export class AppComponent {}
