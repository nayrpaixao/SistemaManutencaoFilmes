import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarComponentComponent } from './listar-component.component';

describe('ListarComponentComponent', () => {
  let component: ListarComponentComponent;
  let fixture: ComponentFixture<ListarComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListarComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
