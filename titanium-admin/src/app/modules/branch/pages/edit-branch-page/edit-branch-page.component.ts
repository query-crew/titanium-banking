import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Branch } from '../../models/branch';
import { BranchService } from '../../services/branch.service';
declare let window: any;

@Component({
  selector: 'app-edit-branch-page',
  templateUrl: './edit-branch-page.component.html',
  styleUrls: ['./edit-branch-page.component.css'],
})
export class EditBranchPageComponent implements OnInit {
  deleteModal: any;
  branchId: any;
  branch!: Branch;
  branchForm!: FormGroup;

  constructor(
    private branchService: BranchService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.getBranch();
    this.initModal();
    this.createForm();
  }

  getBranch() {
    this.branchId = this.activatedRoute.snapshot.paramMap.get('id');
    this.branchService.getBranch(this.branchId).subscribe({
      next: (res) => {
        this.branch = res;
        this.updateForm();
      },
      error: (e) => {
        this.router.navigate(['/500']);
      },
    });
  }
  initModal() {
    this.deleteModal = new window.bootstrap.Modal(
      document.getElementById('deleteModal')
    );
  }
  createForm() {
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
  updateForm() {
    this.branchForm.setValue({
      branchName: this.branch.branchName,
      addressLine1: this.branch.addressLine1,
      addressLine2: this.branch.addressLine2,
      city: this.branch.city,
      state: this.branch.state,
      zipCode: this.branch.zipCode,
      branchDetails: this.branch.branchDetails,
    });
  }
  openModal() {
    this.deleteModal.show();
  }
  closeModal() {
    this.deleteModal.hide();
  }
  onSubmit() {
    const branch: Branch = { ...this.branchForm.value };
    this.updateBranch(branch);
  }
  deleteConfirm() {
    this.closeModal();
    this.deleteBranch();
  }

  updateBranch(branch: Branch) {
    this.branchService.updateBranch(branch, this.branchId).subscribe({
      next: (branch) => {
        this.router.navigate(['/branch/view/' + this.branchId]);
      },
      error: (e) => {
        console.log(e);
        this.router.navigate(['/500']);
      },
    });
  }

  deleteBranch() {
    this.branchService.deleteBranch(this.branchId).subscribe({
      next: (branch) => {
        this.router.navigate(['/branch']);
      },
      error: (e) => {
        console.log(e);
        this.router.navigate(['/500']);
      },
    });
  }
}
