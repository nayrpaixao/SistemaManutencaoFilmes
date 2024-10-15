import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Filme } from './interfaces/filme.model';

@Injectable({
  providedIn: 'root'
})
export class FilmeService {
  private apiUrl = 'http://localhost:8080/titulo';

  constructor(private http: HttpClient) { }

 
  getAllFilmes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/consulta`);
  }

  
  getFilmePorTitulo(titulo: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/consulta?titulo=${titulo}`);
  }
  criarFilme(filme: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/criar`, filme);
}

filtrarFilmes(params: HttpParams): Observable<any[]> {
  return this.http.get<any[]>(`${this.apiUrl}/listar`, { params });
}

excluirFilme(id: number): Observable<any> {
  return this.http.delete<any>(`${this.apiUrl}/${id}`);
}


atualizarFilme(filme: Filme): Observable<Filme> { 
  return this.http.put<Filme>(`${this.apiUrl}/${filme.id}`, filme);
}

  obterFilmePorId(id: number): Observable<Filme> {
    return this.http.get<Filme>(`${this.apiUrl}/${id}`);
  }

  listarFilmes(): Observable<Filme[]> {
    return this.http.get<Filme[]>(this.apiUrl);
  
  }
   
}