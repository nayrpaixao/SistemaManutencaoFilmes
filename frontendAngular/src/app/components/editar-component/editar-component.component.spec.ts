import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarComponentComponent } from './editar-component.component';

describe('EditarComponentComponent', () => {
  let component: EditarComponentComponent;
  let fixture: ComponentFixture<EditarComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditarComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditarComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
