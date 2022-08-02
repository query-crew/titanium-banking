import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { BranchService } from '../../services/branch.service';

import { EditBranchPageComponent } from './edit-branch-page.component';

describe('EditBranchPageComponent', () => {
  let component: EditBranchPageComponent;
  let fixture: ComponentFixture<EditBranchPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditBranchPageComponent],
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [BranchService],
    }).compileComponents();

    fixture = TestBed.createComponent(EditBranchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
