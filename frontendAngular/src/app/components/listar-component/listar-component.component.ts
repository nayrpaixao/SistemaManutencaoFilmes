import { Component, OnInit } from '@angular/core';
import { FilmeService } from '../../filme.service';
import { Filme } from '../../interfaces/filme.model';
import { HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-consultar',
  templateUrl: './listar-component.component.html',
  styleUrls: ['./listar-component.component.css']
})
export class ListarComponent implements OnInit { 
  searchParams: {
    id: number | undefined;
    titulo: string;
    diretor: string;
    genero: string;
    sinopse: string;
    dataLancamento: Date | undefined; 
  } = {
    id: undefined,
    titulo: '',
    diretor: '',
    genero: '',
    sinopse: '',
    dataLancamento: undefined 
  };
  
  filmes: Filme[] = [];
  buscaRealizada = false;
  loading = false;
  mensagemErro = '';
  mensagemSucesso = '';

  constructor(private filmeService: FilmeService, private router: Router, private dialog: MatDialog) {}

  ngOnInit(): void {
    
  }

  
  onFilter(): void {
    this.loading = true;

   
    let params = new HttpParams();
    if (this.searchParams.titulo) {
      params = params.set('titulo', this.searchParams.titulo);
    }
    if (this.searchParams.diretor) {
      params = params.set('diretor', this.searchParams.diretor);
    }
    if (this.searchParams.genero) {
      params = params.set('genero', this.searchParams.genero);
    }
    if (this.searchParams.dataLancamento) {
      params = params.set('anoLancamento', this.searchParams.dataLancamento.toString());
    }
    
  
    params = params.set('ativo', 'true'); 

    
    this.filmeService.filtrarFilmes(params).subscribe(
      (data: Filme[]) => {
        this.filmes = data;

       
        this.filmes.sort((a: Filme, b: Filme) => a.titulo.localeCompare(b.titulo));

        this.buscaRealizada = true;
        this.loading = false;
        this.mensagemErro = '';
      },
      (error) => {
        this.mensagemErro = 'Erro ao buscar filmes';
        this.loading = false;
      }
    );
  }

 
  confirmarExclusao(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.excluirFilme(id);
      }
    });
  }

 
  excluirFilme(id: number): void {
    this.loading = true;
    this.filmeService.excluirFilme(id).subscribe({
      next: () => {
        this.mensagemSucesso = 'Filme excluído com sucesso!';
        this.loading = false;
        this.onFilter(); 
      },
      error: () => {
        this.mensagemErro = 'Erro ao tentar excluir o filme.';
        this.loading = false;
      }
    });
  }

  
  editarFilme(id: number): void {
    this.router.navigate(['/editar', id]); 
  }
}
