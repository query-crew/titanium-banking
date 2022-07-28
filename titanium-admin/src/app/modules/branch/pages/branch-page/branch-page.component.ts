import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Branch } from '../../models/branch';
import { BranchService } from '../../services/branch.service';

@Component({
  selector: 'app-branch-page',
  templateUrl: './branch-page.component.html',
  styleUrls: ['./branch-page.component.css'],
})
export class BranchPageComponent implements OnInit {
  branch!: Branch;
  branchId: any;
  errorMessage: any;
  constructor(
    private branchService: BranchService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.branchId = this.activatedRoute.snapshot.paramMap.get('id');
    this.branchService.getBranch(this.branchId).subscribe({
      next: (res) => {
        this.branch = res;
      },
      error: (e) => {
        this.errorMessage = e;
        this.router.navigate(['/500']);
      },
    });
  }
}
