import { Component, Input, OnInit } from '@angular/core';
import { Branch } from '../../models/branch';

@Component({
  selector: 'app-branch-list',
  templateUrl: './branch-list.component.html',
  styleUrls: ['./branch-list.component.css'],
})
export class BranchListComponent implements OnInit {
  constructor() {}
  // @Input() branches!: Branch[]

  branches = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  ngOnInit(): void {}
}
