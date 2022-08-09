import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Branch } from '../../models/branch';
import { BranchService } from '../../services/branch.service';

@Component({
  selector: 'app-branch-list-page',
  templateUrl: './branch-list-page.component.html',
  styleUrls: ['./branch-list-page.component.css'],
})
export class BranchListPageComponent implements OnInit {
  isLoading = true;
  branches: Branch[] = [];
  errorMessage: any;
  searchInput = '';
  page = 0;
  totalItems = 0;
  totalPages = 0;
  pageSize = 10;
  prevDisabled = true;
  nextDisabled = true;

  constructor(private branchService: BranchService, private router: Router) {}

  ngOnInit(): void {
    this.getBranches(0);
  }

  getRequestParams(searchInput: string, page: number, pageSize: number): any {
    let params: any = {};
    if (searchInput) {
      params[`branchName`] = searchInput;
    }
    if (page) {
      params[`page`] = page;
    }
    if (pageSize) {
      params[`size`] = pageSize;
    }
    return params;
  }

  getBranches(nextPage: number): void {
    const params = this.getRequestParams(
      this.searchInput,
      this.page + nextPage,
      this.pageSize
    );
    if (this.searchInput.length > 0) {
      params.page = 0;
    }
    this.branchService.getBranches(params).subscribe({
      next: (res) => {
        const { branches, totalItems, totalPages, currentPage } = res;
        this.branches = branches;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.page = currentPage;
        if (currentPage === totalPages - 1) {
          this.nextDisabled = true;
        } else {
          this.nextDisabled = false;
        }
        if (currentPage === 0) {
          this.prevDisabled = true;
        } else {
          this.prevDisabled = false;
        }
        this.isLoading = false;
      },
      error: (e) => {
        this.errorMessage = e;
        this.router.navigate(['/500']);
      },
    });
  }

  pageNext() {
    if (this.page < this.totalPages - 1) {
      this.getBranches(1);
    }
  }

  pagePrev() {
    if (this.page > 0) {
      this.getBranches(-1);
    }
  }
}
