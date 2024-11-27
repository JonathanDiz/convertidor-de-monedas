import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class ApiService {
	private backendUrl = 'http://localhost:8080/api/rates';
	
	constructor(private http: HttpClient) { }
	
	getExchangeRates(): Observable<any> {
		return this.http.get(this.backendUrl);
	}
}
