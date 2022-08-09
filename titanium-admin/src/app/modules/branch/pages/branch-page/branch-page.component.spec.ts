import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BranchService } from '../../services/branch.service';

import { BranchPageComponent } from './branch-page.component';

describe('BranchPageComponent', () => {
  let component: BranchPageComponent;
  let fixture: ComponentFixture<BranchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BranchPageComponent],
      imports: [RouterTestingModule, HttpClientTestingModule],
      providers: [BranchService],
    }).compileComponents();

    fixture = TestBed.createComponent(BranchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
