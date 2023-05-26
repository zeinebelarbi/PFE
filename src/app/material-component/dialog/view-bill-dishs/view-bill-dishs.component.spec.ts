import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewBillDishsComponent } from './view-bill-dishs.component';

describe('ViewBillDishsComponent', () => {
  let component: ViewBillDishsComponent;
  let fixture: ComponentFixture<ViewBillDishsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewBillDishsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewBillDishsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
