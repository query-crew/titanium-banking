import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'percent'
})
export class PercentPipe implements PipeTransform {

  transform(value: number): string {
    const decVal: number = 0.01 * value;
    return decVal + "%";
  }

}
