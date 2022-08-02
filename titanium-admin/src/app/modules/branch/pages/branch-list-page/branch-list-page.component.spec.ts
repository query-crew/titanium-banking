import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { BranchService } from '../../services/branch.service';

import { BranchListPageComponent } from './branch-list-page.component';

describe('BranchListPageComponent', () => {
  let component: BranchListPageComponent;
  let fixture: ComponentFixture<BranchListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BranchListPageComponent],
      imports: [RouterTestingModule, HttpClientTestingModule],
      providers: [BranchService],
    }).compileComponents();

    fixture = TestBed.createComponent(BranchListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
