import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBranchPageComponent } from './add-branch-page.component';

describe('AddBranchPageComponent', () => {
  let component: AddBranchPageComponent;
  let fixture: ComponentFixture<AddBranchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddBranchPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddBranchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
