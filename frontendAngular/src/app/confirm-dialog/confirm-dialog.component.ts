import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-dialog',
  template: `
    <h2 mat-dialog-title>Confirmação</h2>
    <mat-dialog-content>
      <p>Tem certeza que deseja excluir este filme?</p>
    </mat-dialog-content>
    <mat-dialog-actions>
      <button mat-button (click)="onNoClick()">Cancelar</button>
      <button mat-button (click)="onConfirm()">Confirmar</button>
    </mat-dialog-actions>
  `
})
export class ConfirmDialogComponent {

  constructor(public dialogRef: MatDialogRef<ConfirmDialogComponent>) {}

  onNoClick(): void {
    this.dialogRef.close(false); 
  }

  onConfirm(): void {
    this.dialogRef.close(true);
}
}