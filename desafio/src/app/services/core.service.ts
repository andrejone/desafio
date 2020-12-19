import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Country } from '../dto/country';
import { EconomicIndicator } from '../dto/economic-indicator';

@Injectable({
  providedIn: 'root'
})
export class CoreService {

  private serverUrl = environment.protocol_host + environment.host;

  constructor(
    private http: HttpClient,
  ) { }

  public handleError<T> (operation = 'operation', result?: T) {

    return (error: any): Observable<T> => {
      return Observable.throw(error);
    };
  }

  listCountries(): Observable<Country[]> {
    return this.http.get<any>(this.serverUrl + 'listCountries', {
    }).pipe(
      catchError(this.handleError<any>(''))
    );
  }

  findByCountryCode(codigoPais: string): Observable<EconomicIndicator[]> {
    return this.http.get<any>(this.serverUrl + 'findByCountryCode/' + codigoPais, {
    }).pipe(
      catchError(this.handleError<any>(''))
    );
  }
}
