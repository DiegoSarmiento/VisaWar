import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'formatofechaConfiguracion'
})
export class FormatofechaConfiguracionPipe implements PipeTransform {

  transform(value) {
    if(value != null){ 
    let result = moment(value).format('DD/MM/YYYY');
    return result;
    }else{
      return null
    }
  }
}
