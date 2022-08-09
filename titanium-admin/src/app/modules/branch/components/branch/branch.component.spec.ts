import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchComponent } from './branch.component';

describe('BranchComponent', () => {
  let component: BranchComponent;
  let fixture: ComponentFixture<BranchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [],
      declarations: [BranchComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(BranchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
