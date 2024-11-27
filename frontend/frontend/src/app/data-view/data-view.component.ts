import { Component, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ChartConfiguration, ChartOptions } from 'chart.js';

@Component({
	selector: 'app-data-view',
	templateUrl: './data-view.component.html',
	styleUrls: ['./data-view.component.less'],
})
export class DataViewComponent {
	backendUrl: string = 'http://localhost:8080/api/history';
	@Input() apiUrl!: string;
	@Input() queryParams: any = {};
	@Input() apiUrl: string = 'https://api.exchangerate-api.com/v4/latest/USD';
	rates: { [key: string]: number } = {};
	selectedCurrency: string = 'EUR'; // Moneda por defecto
	history: { date: string; rate: number; difference: number }[] = [];
	
	data: any = null; // Datos consumidos
	filteredData: any = null; // Datos filtrados para búsqueda local
	isLoading = false; // Estado de carga
	error: string | null = null; // Manejo de errores
	
	// Configuración del gráfico
	lineChartData: ChartConfiguration['data'] = {
		datasets: [
			{
				data: [],
				label: 'Tasa de Cambio',
				borderColor: '#4caf50',
				fill: false,
				tension: 0.4,
			},
		],
		labels: [],
	};
	lineChartOptions: ChartOptions = {
		responsive: true,
	};
	
	// Paginación
	currentPage = 1;
	itemsPerPage = 10;
	totalItems = 0;
	
	// Búsqueda
	searchQuery = ''; // Consulta del buscador
	
	constructor(private http: HttpClient) {}
	
	// Enviar el historial al backend
	sendHistoryToBackend(): void {
		const latestRecord = this.history[this.history.length - 1];
		this.http.post(this.backendUrl, latestRecord).subscribe({
			next: (response) => {
				console.log('Historial enviado al backend:', response);
			},
			error: (err) => {
				console.error('Error al enviar historial:', err);
			},
		});
	}
	
	ngOnInit(): void {
		this.fetchData();
		this.loadHistory();
		this.updateChart();
	}
	
	// Obtener las tasas de cambio actuales
	fetchRates(): void {
		this.http.get(this.apiUrl).subscribe({
			next: (data: any) => {
				this.rates = data.rates;
				this.updateHistory(data.date, this.rates[this.selectedCurrency]);
			},
			error: (err) => {
				console.error('Error al obtener tasas de cambio:', err);
			},
		});
	}
	
	// Guardar en historial
	updateHistory(date: string, rate: number): void {
		const prevRate = this.history.length ? this.history[this.history.length - 1].rate : null;
		const difference = prevRate !== null ? rate - prevRate : 0;
		
		// Agregar el nuevo registro al historial
		const newRecord = { date, rate, difference };
		this.history.push(newRecord);
		
		// Guardar historial en localStorage
		localStorage.setItem('exchangeRateHistory', JSON.stringify(this.history));
		this.sendHistoryToBackend(); // Enviar datos al backend
		this.updateChart();
	}
	
	// Cargar historial del localStorage
	loadHistory(): void {
		const storedHistory = localStorage.getItem('exchangeRateHistory');
		if (storedHistory) {
			this.history = JSON.parse(storedHistory);
		}
	}
	
	updateChart(): void {
		const filteredHistory = this.getFilteredHistory();
		
		this.lineChartData.labels = this.history.map((record) => record.date);
		this.lineChartData.datasets[0].data = this.history.map((record) => record.rate);
	}	
	
	// Cargar los datos desde el backend
	backendHistory: any[] = [];

	fetchHistoryFromBackend(): void {
	  this.http.get(this.backendUrl).subscribe({
	    next: (response: any) => {
	      this.backendHistory = response;
	    },
	    error: (err) => {
	      console.error('Error al cargar historial del backend:', err);
	    },
	  });
	}
	
	// Filtrar el historial según el rango de fechas
	getFilteredHistory(): { date: string; rate: number; difference: number }[] {
		if (!this.startDate || !this.endDate) {
			return this.history;
		}
		
		return this.history.filter((record) => {
			const recordDate = new Date(record.date).getTime();
			const start = new Date(this.startDate).getTime();
			const end = new Date(this.endDate).getTime();
			
			return recordDate >= start && recordDate <= end;
		});
	}
	
	fetchData(): void {
		if (!this.apiUrl) {
			this.error = 'No se proporcionó una URL para la API.';
			return;
		}
		
		this.isLoading = true;
		this.error = null;
		
		// Agregar parámetros de paginación
		const params = {
			...this.queryParams,
			page: this.currentPage,
			limit: this.itemsPerPage,
		};
		
		this.http.get(this.apiUrl, { params }).subscribe({
			next: (response: any) => {
				this.data = response.items || response; 
				this.filteredData = this.data;
				this.totalItems = response.total || 0; // Total de elementos
				this.isLoading = false; 
			},
			error: (err) => {
				this.error = `Error al consumir la API: ${err.message || 'Error desconocido'}`;
				this.isLoading = false;
			},
		});
	}
	
	// Cambiar página
	changePage(newPage: number): void {
		this.currentPage = newPage;
		this.fetchData();
	}
	
	// Método para buscar en los datos localmente
	onSearch(query: string): void {
		this.searchQuery = query.trim().toLocaleLowerCase();
		if (this.data) {
			this.filteredData = this.data.filter((item: any) =>
				JSON.stringify(item).toLocaleLowerCase().includes(this.searchQuery)
		);
		}
	}
}