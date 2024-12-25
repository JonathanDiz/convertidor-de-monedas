import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from '../app/app.component';
import { CurrencyConverterComponent } from './currency-converter/currency-converter.component';
import { CommonModule } from '@angular/common';

bootstrapApplication(AppComponent, {
  providers: []
});

@NgModule({
	declarations: [CurrencyConverterComponent],
	imports: [
		CommonModule,
		FormsModule,
	],
	exports: [CurrencyConverterComponent]
})
export class CurrencyConverterModule {}
