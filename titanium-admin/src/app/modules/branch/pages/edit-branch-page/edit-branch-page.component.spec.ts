import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditBranchPageComponent } from './edit-branch-page.component';

describe('EditBranchPageComponent', () => {
  let component: EditBranchPageComponent;
  let fixture: ComponentFixture<EditBranchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditBranchPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditBranchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
