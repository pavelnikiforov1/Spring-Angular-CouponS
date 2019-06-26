import { ClientType } from './ClientType';

export class LoggedUser {

    constructor(public id:number,public clientType:ClientType,public name:string,public password:String){}
}
