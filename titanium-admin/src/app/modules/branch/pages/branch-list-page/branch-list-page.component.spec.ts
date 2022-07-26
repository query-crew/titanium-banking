import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchListPageComponent } from './branch-list-page.component';

describe('BranchListPageComponent', () => {
  let component: BranchListPageComponent;
  let fixture: ComponentFixture<BranchListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BranchListPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BranchListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
