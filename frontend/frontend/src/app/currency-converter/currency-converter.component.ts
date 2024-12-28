import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-currency-converter',
  templateUrl: './currency-converter.component.html',
  styleUrls: ['./currency-converter.component.less'],
  standalone: true,
  imports: [CommonModule, FormsModule],
})
export class CurrencyConverterComponent {
  fromCurrency: string = '';
  toCurrency: string = '';
  amount: number | null = null;
  result: string | null = null;
  error: string | null = null;

  // Método para realizar la conversión
  convert(): void {
    if (!this.fromCurrency || !this.toCurrency || !this.amount) {
      this.error = 'Por favor, completa todos los campos.';
      this.result = null;
      return;
    }

    // Simulación de lógica de conversión
    const rate = this.getMockExchangeRate(this.fromCurrency, this.toCurrency);
    if (rate) {
      this.result = `${this.amount} ${this.fromCurrency} equivale a ${(
        this.amount * rate
      ).toFixed(2)} ${this.toCurrency}`;
      this.error = null;
    } else {
      this.error = 'No se encontró la tasa de cambio para las monedas seleccionadas.';
      this.result = null;
    }
  }

  // Método simulado para obtener tasa de cambio
  private getMockExchangeRate(from: string, to: string): number | null {
    const rates: { [key: string]: number } = {
      'USD:EUR': 0.85,
      'EUR:USD': 1.18,
    };
    return rates[`${from}:${to}`] || null;
  }
}
