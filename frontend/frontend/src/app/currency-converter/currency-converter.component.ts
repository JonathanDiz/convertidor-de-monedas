import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-currency-converter',
  standalone: true, // Define como componente standalone
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './currency-converter.component.html',
  styleUrls: ['./currency-converter.component.less'],
})
export class CurrencyConverterComponent {
  currencyForm!: FormGroup;
  currencies: string[] = ['USD', 'EUR', 'CLP', 'JPY', 'GBP'];
  convertedAmount: number | null = null;

  constructor(private fb: FormBuilder) {
    // Inicializa el formulario reactivo
    this.currencyForm = this.fb.group({
      fromCurrency: ['USD', Validators.required],
      toCurrency: ['EUR', Validators.required],
      amount: [1, [Validators.required, Validators.min(0.01)]],
    });
  }

  convert(): void {
    if (this.currencyForm.valid) {
      const { fromCurrency, toCurrency, amount } = this.currencyForm.value;

      // Simula una tasa de cambio (reemplaza esto con la integración de la API)
      const exchangeRate = 0.85; // Ejemplo estático
      this.convertedAmount = amount * exchangeRate;

      console.log(`Convertido: ${amount} ${fromCurrency} a ${this.convertedAmount} ${toCurrency}`);
    }
  }
}
