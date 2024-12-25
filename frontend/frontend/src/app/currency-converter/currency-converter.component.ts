import { Component } from '@angular/core';

@Component({
  selector: 'app-currency-converter',
  templateUrl: './currency-converter.component.html',
  styleUrls: ['./currency-converter.component.css']
})
export class CurrencyConverterComponent {
  fromCurrency: string = '';
  toCurrency: string = '';
  amount: number | null = null;
  result: string = '';
  error: string = '';

  private apiUrl = 'http://localhost:8080/api/convert'; // Cambia según tu endpoint backend
  private bearerToken = 'TU_BEARER_TOKEN'; // Sustituye por el token real

  async convertCurrency() {
    this.result = '';
    this.error = '';

    if (!this.fromCurrency || !this.toCurrency || !this.amount) {
      this.error = 'Por favor, completa todos los campos.';
      return;
    }

    try {
      const response = await fetch(this.apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${this.bearerToken}`,
        },
        body: JSON.stringify({
          fromCurrency: this.fromCurrency,
          toCurrency: this.toCurrency,
          amount: this.amount,
        }),
      });

      if (!response.ok) {
        throw new Error(`Error ${response.status}: ${response.statusText}`);
      }

      const data = await response.json();
      this.result = `Resultado: ${data.convertedAmount} ${data.currencyCode}`;
    } catch (error: any) {
      this.error = error.message || 'Ha ocurrido un error inesperado.';
    }
  }
}
