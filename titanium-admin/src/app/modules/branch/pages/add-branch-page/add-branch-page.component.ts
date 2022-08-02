import { Component, OnInit } from '@angular/core';
import { Branch } from '../../models/branch';
import { BranchService } from '../../services/branch.service';
import { Router } from '@angular/router';
import {
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-add-branch-page',
  templateUrl: './add-branch-page.component.html',
  styleUrls: ['./add-branch-page.component.css'],
})
export class AddBranchPageComponent implements OnInit {
  branchForm!: FormGroup;

  constructor(private branchService: BranchService, private router: Router) {}

  ngOnInit(): void {
    this.branchForm = new FormGroup<any>({
      branchName: new FormControl('', [Validators.required]),
      addressLine1: new FormControl('', [Validators.required]),
      addressLine2: new FormControl(''),
      city: new FormControl('', [Validators.required]),
      state: new FormControl('', [Validators.required]),
      zipCode: new FormControl('', [Validators.required]),
      branchDetails: new FormControl('', [Validators.maxLength(250)]),
    });
  }

  addBranch(branch: Branch) {
    this.branchService.addBranch(branch).subscribe({
      next: (branch) => {
        this.router.navigate(['/branch']);
      },
      error: (e) => {
        console.log(e);
        this.router.navigate(['/500']);
      },
    });
  }

  onSubmit() {
    const branch: Branch = { ...this.branchForm.value };
    this.addBranch(branch);
  }
}
