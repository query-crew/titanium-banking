import { Component, OnInit } from '@angular/core';
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
  constructor(private branchService: BranchService) {}

  ngOnInit(): void {
    this.branchId = 3;
    this.branchService.getBranch(this.branchId).subscribe((res) => {
      this.branch = res;
    });
  }
}
