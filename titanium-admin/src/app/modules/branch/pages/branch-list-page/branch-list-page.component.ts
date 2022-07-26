import { Component, OnInit } from '@angular/core';
import { Branch } from '../../models/branch';
import { BranchService } from '../../services/branch.service';

@Component({
  selector: 'app-branch-list-page',
  templateUrl: './branch-list-page.component.html',
  styleUrls: ['./branch-list-page.component.css'],
})
export class BranchListPageComponent implements OnInit {
  branches: Branch[] = [];
  constructor(private branchService: BranchService) {}

  ngOnInit(): void {
    this.branchService.getBranches().subscribe((res) => {
      this.branches = res.branches;
      console.log(this.branches);
    });
  }
}
