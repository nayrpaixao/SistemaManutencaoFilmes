  import { Component, OnInit } from '@angular/core';
  import { ActivatedRoute, Router } from '@angular/router';
  import { FilmeService } from '../../filme.service';
  import { Filme } from '../../interfaces/filme.model';

@Component({
  selector: 'app-editar-component',
  templateUrl: './editar-component.component.html',
  styleUrls: ['./editar-component.component.css']
})
export class EditarComponentComponent implements OnInit {

    filme: Filme | undefined = undefined;
    mensagemErro = '';
    mensagemSucesso: string = '';

    constructor(
      private route: ActivatedRoute,
      private filmeService: FilmeService,
      private router: Router
    ) { }
  
    ngOnInit(): void {
      const id = this.route.snapshot.paramMap.get('id'); 
      if (id) {
        this.filmeService.obterFilmePorId(+id).subscribe(
          (data: Filme) => {
            this.filme = data;
          },
          (error) => {
            this.mensagemErro = 'Erro ao carregar filme';
          }
        );
      }
    }
  
    salvarEdicoes() {
      if (!this.filme) {
        this.mensagemErro = 'Nenhum filme para editar.';
        return;
      }
    
      this.filmeService.atualizarFilme(this.filme).subscribe(() => {
        this.mensagemSucesso = 'Filme atualizado com sucesso!';
        this.mensagemErro = '';
    
       
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 3000);
      }, (error) => {
        this.mensagemErro = 'Erro ao atualizar filme';
        this.mensagemSucesso = '';
      });
    }
  }
