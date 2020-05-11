import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'formatofecha'
})
export class FormatofechaPipe implements PipeTransform {

  transform(value) {
    let result = moment(value).format('DD/MM/YYYY');
    return result;
  }
}
