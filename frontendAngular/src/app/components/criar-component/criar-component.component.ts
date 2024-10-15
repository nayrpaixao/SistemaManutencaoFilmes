import { Component } from '@angular/core';
import { FilmeService } from '../../filme.service';

@Component({
  selector: 'app-criar',
  templateUrl: './criar-component.component.html',
  styleUrls: ['./criar-component.component.css']
})
export class CriarComponent {

  novoFilme: any = {
    titulo: '',
    genero: '',
    diretor: '',
    sinopse: '',
    dataLancamento: ''
    
  };
  
  filmeCriado: boolean = false;
  erroCriacao: boolean = false;
  mensagemErro: string = '';
  mensagemSucesso: string = '';  

  constructor(private filmeService: FilmeService) { }

  capitalizeWords(text: string): string {
    return text.replace(/\b\w/g, char => char.toUpperCase());
  }

   onCreate(form: any): void {
    this.novoFilme.titulo = this.capitalizeWords(this.novoFilme.titulo);
    this.novoFilme.genero = this.capitalizeWords(this.novoFilme.genero);
    this.novoFilme.diretor = this.capitalizeWords(this.novoFilme.diretor);
    this.novoFilme.sinopse = this.capitalizeWords(this.novoFilme.sinopse);

    this.filmeService.criarFilme(this.novoFilme).subscribe(
      () => {
        this.filmeCriado = true;
        this.erroCriacao = false;
        this.mensagemSucesso = 'Filme criado com sucesso!';
        this.resetarFormulario();
      },
      (error) => {
        this.erroCriacao = true;
        this.filmeCriado = false;

        if (error.status === 409) {
          this.mensagemErro = 'Já existe um filme com este título.';
        } else {
          this.mensagemErro = 'Ocorreu um erro ao criar o filme.';
        }

      }
    );
  }
  resetarFormulario(): void {
    this.novoFilme = {
      titulo: '',
      genero: '',
      diretor: '',
      sinopse: '',
      dataLancamento: ''
    };
    this.mensagemErro = ''
  }
}



