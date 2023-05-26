import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageMenucategoryComponent } from './manage-menucategory.component';

describe('ManageMenucategoryComponent', () => {
  let component: ManageMenucategoryComponent;
  let fixture: ComponentFixture<ManageMenucategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageMenucategoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageMenucategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
