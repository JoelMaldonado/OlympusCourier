import { Timestamp } from "@angular/fire/firestore";
import { Cliente } from "./cliente";


export interface Reparto {
    id?:string;
    cliente?:Cliente;
    total?:number;
    fecha?:Timestamp;
    estado?:string;
    distrito?:string;
    direc?:string;
    referencia?:string;
    clave?:string;
    anotacion?:string;
}
