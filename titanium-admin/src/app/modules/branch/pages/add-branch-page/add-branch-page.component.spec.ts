import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { BranchService } from '../../services/branch.service';

import { AddBranchPageComponent } from './add-branch-page.component';

describe('AddBranchPageComponent', () => {
  let component: AddBranchPageComponent;
  let fixture: ComponentFixture<AddBranchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddBranchPageComponent],
      imports: [RouterTestingModule, HttpClientTestingModule],
      providers: [BranchService],
    }).compileComponents();

    fixture = TestBed.createComponent(AddBranchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
