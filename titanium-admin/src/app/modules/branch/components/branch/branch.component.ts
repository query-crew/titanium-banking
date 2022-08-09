import { Component, Input, OnInit } from '@angular/core';
import { Branch } from '../../models/branch';

@Component({
  selector: 'app-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.css'],
})
export class BranchComponent implements OnInit {
  @Input() branch!: Branch;
  constructor() {}

  ngOnInit(): void {}
}
