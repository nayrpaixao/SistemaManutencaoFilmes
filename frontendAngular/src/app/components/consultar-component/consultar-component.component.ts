import { Component, OnInit } from '@angular/core';
import { FilmeService } from '../../filme.service';

@Component({
  selector: 'app-consultar',
  templateUrl: './consultar-component.component.html',
  styleUrls: ['./consultar-component.component.css']
})
export class ConsultarComponent implements OnInit {

  filmes: any[] = [];
  searchParams: any = {
    titulo: ''
  };
  buscaRealizada: boolean = false;
  loading: boolean = false;
  mensagemErro: string = "";

  constructor(private filmeService: FilmeService) { }

  ngOnInit(): void {
  
  }

  
  onSearch(): void {
    const { titulo } = this.searchParams;

    
    if (titulo.trim() === '') {
      this.mensagemErro = 'O campo "titulo" não pode ser vazio.';
      this.filmes = []; 
      this.buscaRealizada = true;
      return;
    }

   
    if (titulo.trim().length < 3) {
      this.mensagemErro = 'O título deve ter pelo menos 3 caracteres.';
      this.filmes = []; 
      this.buscaRealizada = true;
      return;
    }

    this.loading = true;
    this.mensagemErro = '';

    
    this.filmeService.getFilmePorTitulo(titulo).subscribe({
      next: (data: any[]) => {
        console.log(data);
        this.filmes = data;
        this.buscaRealizada = true;
        this.loading = false;

        
        if (this.filmes.length === 0) {
          this.mensagemErro = 'Nenhum filme encontrado.';
        }
      },
      error: (error) => {
        console.error('Erro ao buscar filmes:', error);
        this.filmes = [];
        this.buscaRealizada = true;
        this.loading = false;
        this.mensagemErro = 'Erro ao buscar filmes: ' ;
      }
    });
  }
}

