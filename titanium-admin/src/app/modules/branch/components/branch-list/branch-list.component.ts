import { Component, Input, OnInit } from '@angular/core';
import { Branch } from '../../models/branch';

@Component({
  selector: 'app-branch-list',
  templateUrl: './branch-list.component.html',
  styleUrls: ['./branch-list.component.css'],
})
export class BranchListComponent implements OnInit {
  constructor() {}
  @Input() isLoading = true;
  @Input() branches!: Branch[];
  ngOnInit(): void {}
}
